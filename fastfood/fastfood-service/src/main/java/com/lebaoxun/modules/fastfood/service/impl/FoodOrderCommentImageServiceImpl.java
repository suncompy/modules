package com.lebaoxun.modules.fastfood.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodOrderCommentImageDao;
import com.lebaoxun.modules.fastfood.entity.FoodOrderCommentImageEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderCommentImageService;


@Service("foodOrderCommentImageService")
public class FoodOrderCommentImageServiceImpl extends ServiceImpl<FoodOrderCommentImageDao, FoodOrderCommentImageEntity> implements FoodOrderCommentImageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodOrderCommentImageEntity> page = this.selectPage(
                new Query<FoodOrderCommentImageEntity>(params).getPage(),
                new EntityWrapper<FoodOrderCommentImageEntity>()
        );

        return new PageUtils(page);
    }

}
