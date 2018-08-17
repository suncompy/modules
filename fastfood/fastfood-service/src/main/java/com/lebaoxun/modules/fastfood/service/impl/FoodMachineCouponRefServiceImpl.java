package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineCouponRefDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCouponRefEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineCouponRefService;


@Service("foodMachineCouponRefService")
public class FoodMachineCouponRefServiceImpl extends ServiceImpl<FoodMachineCouponRefDao, FoodMachineCouponRefEntity> implements FoodMachineCouponRefService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineCouponRefEntity> page = this.selectPage(
                new Query<FoodMachineCouponRefEntity>(params).getPage(),
                new EntityWrapper<FoodMachineCouponRefEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<FoodMachineCouponRefEntity> findMacCouponListByMacId(Integer macId) {
        return this.baseMapper.findMacCouponListByMacId(macId);
    }

}
