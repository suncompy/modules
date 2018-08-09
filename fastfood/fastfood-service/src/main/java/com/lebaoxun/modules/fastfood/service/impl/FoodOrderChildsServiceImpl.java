package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodOrderChildsDao;
import com.lebaoxun.modules.fastfood.entity.FoodOrderChildsEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderChildsService;


@Service("foodOrderChildsService")
public class FoodOrderChildsServiceImpl extends ServiceImpl<FoodOrderChildsDao, FoodOrderChildsEntity> implements FoodOrderChildsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodOrderChildsEntity> page = this.selectPage(
                new Query<FoodOrderChildsEntity>(params).getPage(),
                new EntityWrapper<FoodOrderChildsEntity>()
        );

        return new PageUtils(page);
    }

}
