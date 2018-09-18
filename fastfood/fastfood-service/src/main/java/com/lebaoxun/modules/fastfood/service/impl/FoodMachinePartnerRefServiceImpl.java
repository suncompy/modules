package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachinePartnerRefDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachinePartnerRefEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachinePartnerRefService;


@Service("foodMachinePartnerRefService")
public class FoodMachinePartnerRefServiceImpl extends ServiceImpl<FoodMachinePartnerRefDao, FoodMachinePartnerRefEntity> implements FoodMachinePartnerRefService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachinePartnerRefEntity> page = this.selectPage(
                new Query<FoodMachinePartnerRefEntity>(params).getPage(),
                new EntityWrapper<FoodMachinePartnerRefEntity>()
        );

        return new PageUtils(page);
    }

}
