package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineProActivityRefDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineProActivityRefEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineProActivityRefService;


@Service("foodMachineProActivityRefService")
public class FoodMachineProActivityRefServiceImpl extends ServiceImpl<FoodMachineProActivityRefDao, FoodMachineProActivityRefEntity> implements FoodMachineProActivityRefService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineProActivityRefEntity> page = this.selectPage(
                new Query<FoodMachineProActivityRefEntity>(params).getPage(),
                new EntityWrapper<FoodMachineProActivityRefEntity>()
        );

        return new PageUtils(page);
    }

}
