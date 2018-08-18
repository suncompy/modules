package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCouponRefEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRefAisleEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-08-17 15:32:23
 */
public interface FoodMachineCouponRefService extends IService<FoodMachineCouponRefEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<FoodMachineCouponRefEntity> findMacCouponListByMacId(Integer macId);
}

