package com.lebaoxun.modules.fastfood.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMaterialEntity;
import com.lebaoxun.modules.fastfood.entity.FoodProductCatEntity;

/**
 * 餐品分类
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodProductCatService extends IService<FoodProductCatEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    List<FoodMaterialEntity> queryFoodMaterialById(Integer id);
}

