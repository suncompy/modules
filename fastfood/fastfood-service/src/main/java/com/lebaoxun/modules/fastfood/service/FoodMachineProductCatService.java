package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineProductCatEntity;

import java.util.Map;

/**
 * 取餐机餐品分类表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodMachineProductCatService extends IService<FoodMachineProductCatEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

