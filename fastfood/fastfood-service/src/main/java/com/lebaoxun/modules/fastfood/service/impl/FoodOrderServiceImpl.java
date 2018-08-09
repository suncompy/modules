package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodOrderDao;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderService;


@Service("foodOrderService")
public class FoodOrderServiceImpl extends ServiceImpl<FoodOrderDao, FoodOrderEntity> implements FoodOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodOrderEntity> page = this.selectPage(
                new Query<FoodOrderEntity>(params).getPage(),
                new EntityWrapper<FoodOrderEntity>()
        );

        return new PageUtils(page);
    }

}
