package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineRepairOrderDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRepairOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineRepairOrderService;


@Service("foodMachineRepairOrderService")
public class FoodMachineRepairOrderServiceImpl extends ServiceImpl<FoodMachineRepairOrderDao, FoodMachineRepairOrderEntity> implements FoodMachineRepairOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineRepairOrderEntity> page = this.selectPage(
                new Query<FoodMachineRepairOrderEntity>(params).getPage(),
                new EntityWrapper<FoodMachineRepairOrderEntity>()
        );

        return new PageUtils(page);
    }

}
