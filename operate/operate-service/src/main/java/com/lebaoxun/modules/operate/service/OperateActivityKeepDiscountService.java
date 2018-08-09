package com.lebaoxun.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.operate.entity.OperateActivityKeepDiscountEntity;

import java.util.Map;

/**
 * 连续折扣
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */
public interface OperateActivityKeepDiscountService extends IService<OperateActivityKeepDiscountEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

