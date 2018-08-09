package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodOrderBackDao;
import com.lebaoxun.modules.fastfood.entity.FoodOrderBackEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderBackService;


@Service("foodOrderBackService")
public class FoodOrderBackServiceImpl extends ServiceImpl<FoodOrderBackDao, FoodOrderBackEntity> implements FoodOrderBackService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodOrderBackEntity> page = this.selectPage(
                new Query<FoodOrderBackEntity>(params).getPage(),
                new EntityWrapper<FoodOrderBackEntity>()
        );

        return new PageUtils(page);
    }

}
