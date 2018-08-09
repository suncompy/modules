package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockOrderEntity;

import java.util.Map;

/**
 * 取餐机进货派单
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
public interface FoodMachineAddStockOrderService extends IService<FoodMachineAddStockOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

