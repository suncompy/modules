package com.lebaoxun.modules.fastfood.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodWeekMenuEntity;

import java.util.Map;

/**
 * 每周菜谱
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
public interface FoodWeekMenuService extends IService<FoodWeekMenuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

