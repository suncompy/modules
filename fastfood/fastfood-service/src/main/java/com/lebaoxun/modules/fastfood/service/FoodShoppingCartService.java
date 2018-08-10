package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;

import java.util.Map;

/**
 * 购物车表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodShoppingCartService extends IService<FoodShoppingCartEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

