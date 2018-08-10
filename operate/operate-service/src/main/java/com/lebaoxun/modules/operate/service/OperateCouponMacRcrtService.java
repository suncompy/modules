package com.lebaoxun.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.operate.entity.OperateCouponMacRcrtEntity;

import java.util.Map;

/**
 * 取餐机关联表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:32
 */
public interface OperateCouponMacRcrtService extends IService<OperateCouponMacRcrtEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

