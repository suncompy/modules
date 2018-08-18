package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineActivityRefDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineActivityRefEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineActivityRefService;


@Service("foodMachineActivityRefService")
public class FoodMachineActivityRefServiceImpl extends ServiceImpl<FoodMachineActivityRefDao, FoodMachineActivityRefEntity> implements FoodMachineActivityRefService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineActivityRefEntity> page = this.selectPage(
                new Query<FoodMachineActivityRefEntity>(params).getPage(),
                new EntityWrapper<FoodMachineActivityRefEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<FoodMachineActivityRefEntity> findMacActivityListByMacId(Integer macId) {
        return this.baseMapper.findMacActivityListByMacId(macId);
    }

}
