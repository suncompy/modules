package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodProductCatDao;
import com.lebaoxun.modules.fastfood.entity.FoodProductCatEntity;
import com.lebaoxun.modules.fastfood.service.FoodProductCatService;


@Service("foodProductCatService")
public class FoodProductCatServiceImpl extends ServiceImpl<FoodProductCatDao, FoodProductCatEntity> implements FoodProductCatService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodProductCatEntity> page = this.selectPage(
                new Query<FoodProductCatEntity>(params).getPage(),
                new EntityWrapper<FoodProductCatEntity>()
        );

        return new PageUtils(page);
    }

}
