package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineCatDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCatEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineCatService;


@Service("foodMachineCatService")
public class FoodMachineCatServiceImpl extends ServiceImpl<FoodMachineCatDao, FoodMachineCatEntity> implements FoodMachineCatService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineCatEntity> page = this.selectPage(
                new Query<FoodMachineCatEntity>(params).getPage(),
                new EntityWrapper<FoodMachineCatEntity>()
        );

        return new PageUtils(page);
    }

}
