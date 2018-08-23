package com.lebaoxun.modules.fastfood.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityFirstOrderEntity;

/**
 * 首单活动表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:31
 */
public interface OperateActivityFirstOrderService extends IService<OperateActivityFirstOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    OperateActivityFirstOrderEntity findUnderwayActivity();
}

