package com.lebaoxun.modules.fastfood.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodProductEntity;

/**
 * 餐品表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodProductService extends IService<FoodProductEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<FoodProductEntity> findProductInfoByParams(Map<String, Object> params);
    
    List<FoodProductEntity> findAllProductByCat(Integer catId);
    
    void addStock(Integer id,Integer stock);
    
    void deductionStock(Integer id,Integer stock);
}

