package com.lebaoxun.modules.fastfood.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineProductCatEntity;

/**
 * 取餐机餐品分类表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodMachineProductCatService extends IService<FoodMachineProductCatEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    List<FoodMachineProductCatEntity> findCatByMacId(Integer macId);
}

