package com.lebaoxun.modules.fastfood.service.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineErrorLogDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineErrorLogEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineErrorLogService;


@Service("foodMachineErrorLogService")
public class FoodMachineErrorLogServiceImpl extends ServiceImpl<FoodMachineErrorLogDao, FoodMachineErrorLogEntity> implements FoodMachineErrorLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineErrorLogEntity> page = this.selectPage(
                new Query<FoodMachineErrorLogEntity>(params).getPage(),
                new EntityWrapper<FoodMachineErrorLogEntity>().orderDesc(Arrays.asList("create_time"))
        );

        return new PageUtils(page);
    }

}
