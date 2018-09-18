package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachinePartnerRefEntity;

import java.util.Map;

/**
 * 机器合作商关联表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 12:17:47
 */
public interface FoodMachinePartnerRefService extends IService<FoodMachinePartnerRefEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

