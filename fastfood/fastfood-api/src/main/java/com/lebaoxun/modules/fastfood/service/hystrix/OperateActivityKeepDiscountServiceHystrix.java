package com.lebaoxun.modules.fastfood.service.hystrix;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityKeepDiscountEntity;
import com.lebaoxun.modules.fastfood.service.IOperateActivityKeepDiscountService;

/**
 * 连续折扣
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:29
 */

@Component
public class OperateActivityKeepDiscountServiceHystrix implements IOperateActivityKeepDiscountService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info() {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,OperateActivityKeepDiscountEntity operateActivityKeepDiscount) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,OperateActivityKeepDiscountEntity operateActivityKeepDiscount) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Integer[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public OperateActivityKeepDiscountEntity findUnderwayActivity() {
		return null;
	}
    
}

