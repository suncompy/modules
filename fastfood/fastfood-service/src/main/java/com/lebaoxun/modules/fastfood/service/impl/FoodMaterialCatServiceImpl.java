package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMaterialCatDao;
import com.lebaoxun.modules.fastfood.entity.FoodMaterialCatEntity;
import com.lebaoxun.modules.fastfood.service.FoodMaterialCatService;


@Service("foodMaterialCatService")
public class FoodMaterialCatServiceImpl extends ServiceImpl<FoodMaterialCatDao, FoodMaterialCatEntity> implements FoodMaterialCatService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMaterialCatEntity> page = this.selectPage(
                new Query<FoodMaterialCatEntity>(params).getPage(),
                new EntityWrapper<FoodMaterialCatEntity>()
        );

        return new PageUtils(page);
    }

}
