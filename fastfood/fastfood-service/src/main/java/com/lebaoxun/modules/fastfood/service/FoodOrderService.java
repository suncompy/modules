package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;

import java.util.Map;

/**
 * 订单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 18:47:10
 */
public interface FoodOrderService extends IService<FoodOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

