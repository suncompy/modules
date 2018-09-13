package com.lebaoxun.modules.fastfood.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;

/**
 * 购物车表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodShoppingCartService extends IService<FoodShoppingCartEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    FoodShoppingCartEntity add(Long userId,Integer aisleId,Integer macId,Integer productId,Integer checkStatus,Integer buyNumber);
    
    FoodShoppingCartEntity set(Long userId,Long cartId,Integer productId, Integer checkStatus,Integer buyNumber);
    
    void remove(Long userId,Long cartId);
    
    void clear(Long userId);
    
    List<Map<String,Object>> findByUser(Long userId);
}

