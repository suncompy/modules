package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodProductDao;
import com.lebaoxun.modules.fastfood.entity.FoodProductEntity;
import com.lebaoxun.modules.fastfood.service.FoodProductService;


@Service("foodProductService")
public class FoodProductServiceImpl extends ServiceImpl<FoodProductDao, FoodProductEntity> implements FoodProductService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodProductEntity> page = this.selectPage(
                new Query<FoodProductEntity>(params).getPage(),
                new EntityWrapper<FoodProductEntity>()
        );

        return new PageUtils(page);
    }

}
