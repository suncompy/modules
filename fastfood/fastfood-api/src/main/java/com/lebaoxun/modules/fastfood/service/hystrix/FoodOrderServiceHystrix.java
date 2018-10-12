package com.lebaoxun.modules.fastfood.service.hystrix;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.service.IFoodOrderService;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
	public ResponseMessage getSweeptCodeOrderInfo(String orderId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage pushOrder(Long orderId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage takeFoodCallback(String orderId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage updateTakeNum(String orderId,String macId,String productId){
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage wxAppPayForOrder(Long userId, BigDecimal dis,
			String spbill_create_ip, String payGroup, String openid,
			String orderNo,Integer couponId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public ResponseMessage balancePayForOrder(Long userId, BigDecimal dis,
			String orderNo,Integer couponId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage createOrderByShoppingCart(Integer macId, Long userId,
			BigDecimal dis, List<FoodShoppingCartEntity> carts) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public ResponseMessage prizeExchangeForOrder(Long userId, Integer prizeLogId) {
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
	public ResponseMessage getOrderNoByCode(Long macId,Integer takeFoodCode) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	@Override
	public ResponseMessage getOrderStatus(Long orderId,String orderNo){
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage createOrderByMac(@RequestParam(value = "spbill_create_ip") String spbill_create_ip, @RequestParam(value = "payGroup") String payGroup, @RequestBody FoodOrderEntity order) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findOrderInfoByMacIMEI(@RequestParam(value = "imei") String imei) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public ResponseMessage findOrderInfoByUser(Long userId, String orderNo) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public ResponseMessage findOrderConfig() {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public ResponseMessage modifyOrderConfig(Integer timeOut, Integer nopayLimit) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage calCheckTotalFeeByOrderNo(Long userId,
			String orderNo, BigDecimal dis) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public ResponseMessage modifyWarmFlag(Long userId, String orderNo,
			Integer warmFlag) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public ResponseMessage addInvoice(Long userId, String orderNo,
			Integer invoiceFlag, String invoiceIRD, String invoiceEamil,
			String invoiceTitle) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public ResponseMessage cancelOrder(Long userId, String orderNo) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
}

