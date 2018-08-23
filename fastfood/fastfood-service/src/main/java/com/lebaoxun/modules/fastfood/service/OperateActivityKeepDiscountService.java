package com.lebaoxun.modules.fastfood.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityKeepDiscountEntity;

/**
 * 连续折扣
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:29
 */
public interface OperateActivityKeepDiscountService extends IService<OperateActivityKeepDiscountEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    OperateActivityKeepDiscountEntity findUnderwayActivity();
}

