package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineAddStockOrderDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAddStockOrderService;


@Service("foodMachineAddStockOrderService")
public class FoodMachineAddStockOrderServiceImpl extends ServiceImpl<FoodMachineAddStockOrderDao, FoodMachineAddStockOrderEntity> implements FoodMachineAddStockOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineAddStockOrderEntity> page = this.selectPage(
                new Query<FoodMachineAddStockOrderEntity>(params).getPage(),
                new EntityWrapper<FoodMachineAddStockOrderEntity>()
        );

        return new PageUtils(page);
    }

}
