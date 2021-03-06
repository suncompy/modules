package com.lebaoxun.modules.fastfood.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.lebaoxun.commons.utils.StringUtils;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.utils.AddressParse;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodMachineDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCatAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAisleService;
import com.lebaoxun.modules.fastfood.service.FoodMachineCatAisleService;
import com.lebaoxun.modules.fastfood.service.FoodMachineService;

import org.springframework.web.bind.annotation.RequestParam;


@Service("foodMachineService")
public class FoodMachineServiceImpl extends ServiceImpl<FoodMachineDao, FoodMachineEntity> implements FoodMachineService {
    @Autowired
    private FoodMachineAisleService foodMachineAisleService;
    @Autowired
    private FoodMachineCatAisleService foodMachineCatAisleService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<FoodMachineEntity> entityEntityWrapper=new EntityWrapper<FoodMachineEntity>();
        if (params!=null&&params.containsKey("condition")){
            entityEntityWrapper.like("name",params.get("condition").toString());
            entityEntityWrapper.or();
            entityEntityWrapper.like("imei",params.get("condition").toString());
        }
        if (params!=null&&params.containsKey("areaCode")){
            entityEntityWrapper.eq("area_code",params.get("areaCode").toString());
        }
        if (params!=null&&params.containsKey("address")){
            entityEntityWrapper.like("address",params.get("address").toString());
        }
        Page<FoodMachineEntity> page = this.selectPage(
                new Query<FoodMachineEntity>(params).getPage(),
                entityEntityWrapper
        );

        return new PageUtils(page);
    }
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void save(Long adminId,FoodMachineEntity foodMachine){
        checkFormData(foodMachine);
        foodMachine.setCreateBy(adminId);
        foodMachine.setUpdateBy(adminId);
        Date date=new Date();
        foodMachine.setCreateTime(date);
        foodMachine.setUpdateTime(date);
        this.baseMapper.save(foodMachine);
        //根据前端选择的机器分类，将分类信息同时同步到机器-产品-货道关联表中
        this.batchSycMachineCatAisle(adminId,foodMachine);
    }
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void updateById(Long adminId,FoodMachineEntity foodMachine){
        checkFormData(foodMachine);
        foodMachine.setUpdateBy(adminId);
        foodMachine.setUpdateTime(new Date());
        this.updateById(foodMachine);
        /*FoodMachineEntity dbMacInfo=this.selectById(foodMachine.getId());
        //如果分类有变化，则需要调整关联表，先删除再批量同步
        if(dbMacInfo.getCatId()!=dbMacInfo.getCatId()){
            EntityWrapper<FoodMachineAisleEntity> macCatWrapper=new EntityWrapper<FoodMachineAisleEntity>();
            macCatWrapper.eq("mac_id",foodMachine.getId());
            macCatWrapper.eq("cat_id",foodMachine.getCatId());
            foodMachineAisleService.delete(macCatWrapper);
            //删除后，再同步
            this.batchSycMachineCatAisle(adminId,foodMachine);
        }*/
    }
    public void checkFormData(FoodMachineEntity foodMachine){
        if(foodMachine.getCatId()==null||foodMachine.getCatId()==0)
            throw new I18nMessageException("100004", "机器分类ID为空!");
        //通过地址调用地图接口获取经纬度
        String xy = AddressParse.geocode(foodMachine.getJoinAddress());
        if(StringUtils.isEmpty(xy)){
            throw new I18nMessageException("100005", "获取经纬度失败!");
        }
        String pos[] = xy.split(" ");
        foodMachine.setPos(xy);
        foodMachine.setLat(Double.parseDouble(pos[1]));
        foodMachine.setLng(Double.parseDouble(pos[0]));
    }
    public void batchSycMachineCatAisle(Long adminId,FoodMachineEntity foodMachine){
        EntityWrapper<FoodMachineCatAisleEntity> macCatWrapper=new EntityWrapper<FoodMachineCatAisleEntity>();
        macCatWrapper.eq("cat_id",foodMachine.getCatId());
        List<FoodMachineCatAisleEntity> machineCatAisleEntityList=foodMachineCatAisleService.selectList(macCatWrapper);
        if(machineCatAisleEntityList==null||machineCatAisleEntityList.size()==0){
            throw new I18nMessageException("100006", "机器分类["+foodMachine.getCatId()+"]对应的货道为空!");
        }
        List<FoodMachineAisleEntity> dbEntityList= Lists.newArrayList();
        for(FoodMachineCatAisleEntity machineCatAisleEntity:machineCatAisleEntityList){
            FoodMachineAisleEntity foodMachineAisleEntity=new FoodMachineAisleEntity();
            foodMachineAisleEntity.setMacId(foodMachine.getId());
            foodMachineAisleEntity.setX(machineCatAisleEntity.getX());
            foodMachineAisleEntity.setY(machineCatAisleEntity.getY());
            foodMachineAisleEntity.setZ(machineCatAisleEntity.getZ()==null?0:machineCatAisleEntity.getZ());
            foodMachineAisleEntity.setSize(machineCatAisleEntity.getSize());
            Date date=new Date();
            foodMachineAisleEntity.setCreateTime(date);
            foodMachineAisleEntity.setUpdateTime(date);
            foodMachineAisleEntity.setUpdateBy(adminId);
            dbEntityList.add(foodMachineAisleEntity);
        }
        foodMachineAisleService.insertBatch(dbEntityList);
    }
    
    @Override
    public List<Map<String, Object>> search(String keyword,
 		   Double lat,Double lng) {
    	// TODO Auto-generated method stub
    	List<Map<String, Object>> search = new ArrayList<Map<String, Object>>();
    	search.addAll(this.baseMapper.searchMacByKeyword(keyword,lat,lng));
    	return search;
    }
    
    @Override
    public List<FoodMachineEntity> findByAreaCode(String areaCode,Double lat,Double lng) {
    	// TODO Auto-generated method stub
    	List<FoodMachineEntity> list = this.baseMapper.findByAreaCode(areaCode,lat,lng);
    	if(list == null){
    		list = new ArrayList<FoodMachineEntity>();
    	}
    	if(list.isEmpty()){
    		list.addAll(this.baseMapper.findByRandom(1));
    	}
    	return list;
    }
    
    @Override
    public FoodMachineEntity findByMacId(Integer macId) {
    	// TODO Auto-generated method stub
    	return this.baseMapper.findByMacId(macId);
    }

    @Override
    public FoodMachineEntity findByMacOpenApiById(Integer macId) {
        return this.baseMapper.findByMacOpenApiById(macId);
    }

    @Override
    public List<Map<String, Object>> findByMacRefProductById(Integer pageSize,Integer pageNo,Integer macId,Integer catId) {
        return this.baseMapper.findByMacRefProductById(pageSize,pageNo,macId,catId);
    }
}
