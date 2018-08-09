package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCatEntity;

import java.util.Map;

/**
 * 取餐机分类
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:11
 */
public interface FoodMachineCatService extends IService<FoodMachineCatEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

