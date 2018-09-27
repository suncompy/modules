package com.lebaoxun.modules.fastfood.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineProActivityRefEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAddStockOrderService;
import com.lebaoxun.modules.fastfood.service.FoodMachineAisleService;
import com.lebaoxun.modules.fastfood.service.FoodMachineProActivityRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineAddStockHeadDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockHeadEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAddStockHeadService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("foodMachineAddStockHeadService")
public class FoodMachineAddStockHeadServiceImpl extends ServiceImpl<FoodMachineAddStockHeadDao, FoodMachineAddStockHeadEntity> implements FoodMachineAddStockHeadService {
    @Autowired
    private FoodMachineAddStockOrderService foodMachineAddStockOrderService;
    @Autowired
    private FoodMachineAisleService foodMachineAisleService;
    @Autowired
    private FoodMachineProActivityRefService foodMachineProActivityRefService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineAddStockHeadEntity> page = this.selectPage(
                new Query<FoodMachineAddStockHeadEntity>(params).getPage(),
                new EntityWrapper<FoodMachineAddStockHeadEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor=Exception.class,propagation = Propagation.REQUIRES_NEW)
    public boolean acceptPickingOrder(Long userId, FoodMachineAddStockHeadEntity foodMachineAddStockHead) {
        foodMachineAddStockHead.setUpdateBy(userId);
        boolean ret=this.updateById(foodMachineAddStockHead);
        //配货员确认配货后
        //需要更新货道库存
        //需要核实机器货道产品有没有发生变化，如有则需要删除货道关联的产品活动
        if (ret){
            EntityWrapper<FoodMachineAddStockOrderEntity> entityWrapper=new EntityWrapper<FoodMachineAddStockOrderEntity>();
            entityWrapper.eq("head_id",foodMachineAddStockHead.getId());
            List<FoodMachineAddStockOrderEntity> stockOrderEntities=foodMachineAddStockOrderService.selectList(entityWrapper);
            if(stockOrderEntities==null||stockOrderEntities.get(0).getId()==null)
                return false;
            Integer macId=stockOrderEntities.get(0).getMacId();
            //查询机器货道产品明细
            EntityWrapper<FoodMachineAisleEntity> aisleEntityWrapper=new EntityWrapper<FoodMachineAisleEntity>();
            aisleEntityWrapper.eq("mac_id",macId);
            List<FoodMachineAisleEntity> aisleList=foodMachineAisleService.selectList(aisleEntityWrapper);
            Map<Integer,FoodMachineAisleEntity> aisleMap= Maps.newHashMap();
            aisleList.forEach(e->{
                aisleMap.put(e.getId(),e);
            });
            List<FoodMachineAisleEntity> dbAisleList= Lists.newArrayList();
            for (FoodMachineAddStockOrderEntity stockOrder:stockOrderEntities){
                FoodMachineAisleEntity cAisle=aisleMap.get(stockOrder.getAisleId());
                //如有则需要删除货道关联的产品活动、更新货道关联产品
                if (cAisle.getProductId()!=stockOrder.getProductId()){
                    EntityWrapper<FoodMachineProActivityRefEntity> proActWrapper=new EntityWrapper<FoodMachineProActivityRefEntity>();
                    proActWrapper.eq("mac_id",macId);
                    proActWrapper.eq("aisle_id",stockOrder.getAisleId());
                    foodMachineProActivityRefService.delete(proActWrapper);

                    //更新货道产品
                    FoodMachineAisleEntity aisleEntity=new FoodMachineAisleEntity();
                    aisleEntity.setId(cAisle.getId());
                    aisleEntity.setPrice(stockOrder.getPrice());
                    aisleEntity.setProductId(stockOrder.getProductId());
                    aisleEntity.setProductCatId(stockOrder.getLineCatId());
                    aisleEntity.setStock(stockOrder.getAdd());
                    dbAisleList.add(aisleEntity);
                }else {
                    FoodMachineAisleEntity aisleEntity=new FoodMachineAisleEntity();
                    aisleEntity.setId(cAisle.getId());
                    aisleEntity.setPrice(stockOrder.getPrice());
                    aisleEntity.setProductCatId(stockOrder.getLineCatId());
                    aisleEntity.setStock(cAisle.getStock()+stockOrder.getAdd());
                    dbAisleList.add(aisleEntity);
                }
            }
            foodMachineAisleService.updateBatchById(dbAisleList);
            return true;
        }
        return false;
    }

}
