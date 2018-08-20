package com.lebaoxun.modules.operate.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.operate.entity.OperateCouponRecordEntity;

/**
 * 优惠券领取记录
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:32
 */
public interface OperateCouponRecordService extends IService<OperateCouponRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    /**
	 * 查询用户未过期，未使用的优惠券
	 * @param userId
	 * @return
	 */
	List<OperateCouponRecordEntity> findByUserId(Long userId,
			Integer use,Integer flag,
			Integer size,Integer offset);
	
	OperateCouponRecordEntity getCouponByCouponId(Long userId,Integer macId,Integer couponId);
}

