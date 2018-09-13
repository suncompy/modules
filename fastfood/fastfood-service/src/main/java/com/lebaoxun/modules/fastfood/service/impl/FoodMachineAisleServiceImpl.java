package com.lebaoxun.modules.fastfood.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lebaoxun.modules.fastfood.entity.FoodMachineCatAisleEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineCatAisleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodMachineAisleDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRefAisleEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAisleService;

import javax.annotation.Resource;


@Service("foodMachineAisleService")
public class FoodMachineAisleServiceImpl extends ServiceImpl<FoodMachineAisleDao, FoodMachineAisleEntity> implements FoodMachineAisleService {
    @Resource
    private FoodMachineCatAisleService foodMachineCatAisleService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineAisleEntity> page = this.selectPage(
                new Query<FoodMachineAisleEntity>(params).getPage(),
                new EntityWrapper<FoodMachineAisleEntity>()
        );

        return new PageUtils(page);
    }
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void refProductAndType(Long adminId, FoodMachineRefAisleEntity foodMachineAisle){
        List<String> _ids=foodMachineAisle.getAisleIds();
        List<String> catAisleIds=foodMachineAisle.getCatAisleIds();
        if (catAisleIds==null||catAisleIds.size()==0)
            throw new I18nMessageException("100002", "机器分类货道ID不能为空!");
        //遍历批量集，更新产品、分类id到关联表中
        for (int i=0;i<catAisleIds.size();i++){
            FoodMachineCatAisleEntity foodMachineCatAisleEntity=foodMachineCatAisleService.selectById(catAisleIds.get(i));
            FoodMachineAisleEntity _aisleObj=new FoodMachineAisleEntity();
            _aisleObj.setUpdateBy(adminId);
            _aisleObj.setUpdateTime(new Date());
            _aisleObj.setPrice(foodMachineAisle.getPrice());
            _aisleObj.setProductId(foodMachineAisle.getProductId());
            _aisleObj.setProductCatId(foodMachineAisle.getProductCatId());
            if(_ids==null||_ids.size()==0||_ids.get(i)==null||_ids.get(i).equals("0")){
                _aisleObj.setX(foodMachineCatAisleEntity.getX());
                _aisleObj.setY(foodMachineCatAisleEntity.getY());
                _aisleObj.setY(foodMachineCatAisleEntity.getY());
                _aisleObj.setMacId(foodMachineAisle.getMacId());
                _aisleObj.setSize(foodMachineCatAisleEntity.getSize());
                _aisleObj.setCreateTime(new Date());
                //一个货道只能关联一个产品
                EntityWrapper<FoodMachineAisleEntity> entityWrapper=new EntityWrapper<>();
                entityWrapper.eq("mac_id",foodMachineAisle.getMacId());
                entityWrapper.eq("x",foodMachineCatAisleEntity.getX());
                entityWrapper.eq("y",foodMachineCatAisleEntity.getY());
                FoodMachineAisleEntity old=this.selectOne(entityWrapper);
                if (old!=null&&old.getId()>0)
                    continue;
                insert(_aisleObj);
            }else{
                _aisleObj.setId(Integer.parseInt(_ids.get(i)));
                updateById(_aisleObj);
            }
        }
    }

    @Override
    public List<FoodMachineRefAisleEntity> findMachineAisleListByMacId(Integer macId) {
        return this.baseMapper.findMachineAisleListByMacId(macId);
    }
    
    @Override
    public List<Map<String, Object>> findProductByMacIdAndProductCatId(
    		Integer macId, Integer productCatId) {
    	// TODO Auto-generated method stub
    	return this.baseMapper.findProductByMacIdAndProductCatId(macId, productCatId);
    }
    
    @Override
    public List<Map<String, Object>> findProductByMacIdAndWeek(Integer macId,
    		Integer week) {
    	// TODO Auto-generated method stub
    	return this.baseMapper.findProductByMacIdAndWeek(macId, week);
    }
	@Override
	public List<Map<String, Object>> findProductByMacIdAndAdTime(Integer macId,
			String time, Integer productCatId) {
		// TODO Auto-generated method stub
		return this.baseMapper.findProductByMacIdAndAdTime(macId, time, productCatId);
	}
}
