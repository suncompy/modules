package com.lebaoxun.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.operate.entity.OperateCouponRecordEntity;

import java.util.Map;

/**
 * 优惠券领取记录
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:32
 */
public interface OperateCouponRecordService extends IService<OperateCouponRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

