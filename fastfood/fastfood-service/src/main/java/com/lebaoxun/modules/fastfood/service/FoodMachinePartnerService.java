package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachinePartnerEntity;

import java.util.Map;

/**
 * 合作公司
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 12:17:47
 */
public interface FoodMachinePartnerService extends IService<FoodMachinePartnerEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

