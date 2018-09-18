package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachinePartnerDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachinePartnerEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachinePartnerService;


@Service("foodMachinePartnerService")
public class FoodMachinePartnerServiceImpl extends ServiceImpl<FoodMachinePartnerDao, FoodMachinePartnerEntity> implements FoodMachinePartnerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachinePartnerEntity> page = this.selectPage(
                new Query<FoodMachinePartnerEntity>(params).getPage(),
                new EntityWrapper<FoodMachinePartnerEntity>()
        );

        return new PageUtils(page);
    }

}
