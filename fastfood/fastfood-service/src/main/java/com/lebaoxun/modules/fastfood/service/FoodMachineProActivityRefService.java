package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineProActivityRefEntity;

import java.util.Map;

/**
 * 
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-08-17 15:32:24
 */
public interface FoodMachineProActivityRefService extends IService<FoodMachineProActivityRefEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

