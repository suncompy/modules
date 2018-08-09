package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodWeekMenuDao;
import com.lebaoxun.modules.fastfood.entity.FoodWeekMenuEntity;
import com.lebaoxun.modules.fastfood.service.FoodWeekMenuService;


@Service("foodWeekMenuService")
public class FoodWeekMenuServiceImpl extends ServiceImpl<FoodWeekMenuDao, FoodWeekMenuEntity> implements FoodWeekMenuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodWeekMenuEntity> page = this.selectPage(
                new Query<FoodWeekMenuEntity>(params).getPage(),
                new EntityWrapper<FoodWeekMenuEntity>()
        );

        return new PageUtils(page);
    }

}
