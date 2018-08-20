package com.lebaoxun.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.operate.entity.OperateActivityFirstOrderEntity;

import java.util.Map;

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

