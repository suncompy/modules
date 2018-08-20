package com.lebaoxun.modules.fastfood.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;

/**
 * 订单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodOrderService extends IService<FoodOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    /**
     * 购物车下单
     * @param cartIds
     */
    List<FoodOrderEntity> createOrderByShoppingCart(Long userId,List<FoodShoppingCartEntity> carts);
    
    /**
     * 普通下单
     * @param orders
     * @return
     */
    List<FoodOrderEntity> createOrder(Long userId,List<FoodOrderEntity> orders);
}
