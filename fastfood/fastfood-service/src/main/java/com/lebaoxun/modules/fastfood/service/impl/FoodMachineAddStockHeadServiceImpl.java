package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineAddStockHeadDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockHeadEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAddStockHeadService;


@Service("foodMachineAddStockHeadService")
public class FoodMachineAddStockHeadServiceImpl extends ServiceImpl<FoodMachineAddStockHeadDao, FoodMachineAddStockHeadEntity> implements FoodMachineAddStockHeadService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineAddStockHeadEntity> page = this.selectPage(
                new Query<FoodMachineAddStockHeadEntity>(params).getPage(),
                new EntityWrapper<FoodMachineAddStockHeadEntity>()
        );

        return new PageUtils(page);
    }

}
