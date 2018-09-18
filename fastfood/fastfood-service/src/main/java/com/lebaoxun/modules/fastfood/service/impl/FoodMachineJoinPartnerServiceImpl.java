package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineJoinPartnerDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineJoinPartnerEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineJoinPartnerService;


@Service("foodMachineJoinPartnerService")
public class FoodMachineJoinPartnerServiceImpl extends ServiceImpl<FoodMachineJoinPartnerDao, FoodMachineJoinPartnerEntity> implements FoodMachineJoinPartnerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineJoinPartnerEntity> page = this.selectPage(
                new Query<FoodMachineJoinPartnerEntity>(params).getPage(),
                new EntityWrapper<FoodMachineJoinPartnerEntity>()
        );

        return new PageUtils(page);
    }

}
