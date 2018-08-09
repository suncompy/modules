package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMaterialEntity;

import java.util.Map;

/**
 * 餐品原料表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
public interface FoodMaterialService extends IService<FoodMaterialEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

