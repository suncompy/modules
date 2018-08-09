package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMaterialDao;
import com.lebaoxun.modules.fastfood.entity.FoodMaterialEntity;
import com.lebaoxun.modules.fastfood.service.FoodMaterialService;


@Service("foodMaterialService")
public class FoodMaterialServiceImpl extends ServiceImpl<FoodMaterialDao, FoodMaterialEntity> implements FoodMaterialService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMaterialEntity> page = this.selectPage(
                new Query<FoodMaterialEntity>(params).getPage(),
                new EntityWrapper<FoodMaterialEntity>()
        );

        return new PageUtils(page);
    }

}
