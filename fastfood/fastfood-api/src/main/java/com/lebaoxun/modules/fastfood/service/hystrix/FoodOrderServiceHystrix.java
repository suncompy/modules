package com.lebaoxun.modules.fastfood.service.hystrix;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.service.IFoodOrderService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 订单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@Component
public class FoodOrderServiceHystrix implements IFoodOrderService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Long id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,FoodOrderEntity foodOrder) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,FoodOrderEntity foodOrder) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Long[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage getSweeptCodeOrderInfo(@RequestParam("orderId") String orderId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage pushOrder(@RequestParam("orderId") Long orderId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage takeFoodCallback(@RequestParam("orderId") String orderId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage wxAppPayForOrder(Long userId, BigDecimal dis,
			String spbill_create_ip, String payGroup, String openid,
			String orderNo) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage createOrderByShoppingCart(Integer macId, Long userId,
			BigDecimal dis, List<FoodShoppingCartEntity> carts) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage createOrder(Integer macId, Long userId, BigDecimal dis,
			FoodOrderEntity order) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage calCheckTotalFee(Long userId, BigDecimal dis,
			FoodOrderEntity order) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findOrderByUser(Long userId, Integer status,
			Integer size, Integer offset) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage takeFoodCode(@RequestParam("macId") Long macId, @RequestParam("takeFoodCode") Integer takeFoodCode) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

}

