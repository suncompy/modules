package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRepairOrderEntity;

import java.util.Map;

/**
 * 维修派单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
public interface FoodMachineRepairOrderService extends IService<FoodMachineRepairOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

