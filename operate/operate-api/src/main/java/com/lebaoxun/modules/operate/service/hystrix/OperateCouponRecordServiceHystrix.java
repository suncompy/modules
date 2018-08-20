package com.lebaoxun.modules.operate.service.hystrix;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.modules.operate.entity.OperateCouponRecordEntity;
import com.lebaoxun.modules.operate.service.IOperateCouponRecordService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;

/**
 * 优惠券领取记录
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:32
 */

@Component
public class OperateCouponRecordServiceHystrix implements IOperateCouponRecordService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Integer id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,OperateCouponRecordEntity operateCouponRecord) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,OperateCouponRecordEntity operateCouponRecord) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Integer[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findByUserId(Long userId, Integer use, Integer flag,
			Integer size, Integer offset) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public ResponseMessage getCouponByCouponId(Long userId, Integer macId,
			Integer couponId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
}

