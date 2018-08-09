package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodShoppingCartDao;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.service.FoodShoppingCartService;


@Service("foodShoppingCartService")
public class FoodShoppingCartServiceImpl extends ServiceImpl<FoodShoppingCartDao, FoodShoppingCartEntity> implements FoodShoppingCartService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodShoppingCartEntity> page = this.selectPage(
                new Query<FoodShoppingCartEntity>(params).getPage(),
                new EntityWrapper<FoodShoppingCartEntity>()
        );

        return new PageUtils(page);
    }

}
