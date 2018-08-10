package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAdvanceTimeEntity;

import java.util.Map;

/**
 * 取餐机预定时间配置
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodMachineAdvanceTimeService extends IService<FoodMachineAdvanceTimeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

