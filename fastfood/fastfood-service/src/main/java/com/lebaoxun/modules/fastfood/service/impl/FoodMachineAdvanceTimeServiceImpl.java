package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodMachineAdvanceTimeDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAdvanceTimeEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAdvanceTimeService;


@Service("foodMachineAdvanceTimeService")
public class FoodMachineAdvanceTimeServiceImpl extends ServiceImpl<FoodMachineAdvanceTimeDao, FoodMachineAdvanceTimeEntity> implements FoodMachineAdvanceTimeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineAdvanceTimeEntity> page = this.selectPage(
                new Query<FoodMachineAdvanceTimeEntity>(params).getPage(),
                new EntityWrapper<FoodMachineAdvanceTimeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<FoodMachineAdvanceTimeEntity> findAdvanceTimeByMacId(
    		Integer macId) {
    	return this.baseMapper.findAdvanceTimeByMacId(macId);
    }
}
