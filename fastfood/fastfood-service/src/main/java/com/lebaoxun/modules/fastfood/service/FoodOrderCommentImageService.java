package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodOrderCommentImageEntity;

import java.util.Map;

/**
 * 评价图片表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-07 17:58:09
 */
public interface FoodOrderCommentImageService extends IService<FoodOrderCommentImageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

