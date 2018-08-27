package com.lebaoxun.modules.fastfood.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;

/**
 * 订单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
public interface FoodOrderService extends IService<FoodOrderEntity> {

	PageUtils queryPage(Map<String, Object> params);

	/**
	 * 微信小程序支付订单
	 * 
	 * @param userId
	 * @param isFirstOrder
	 * @param dis
	 * @param orderNo
	 * @return
	 */
	ResponseMessage wxAppPayForOrder(Long userId,BigDecimal dis, String spbill_create_ip, String payGroup, 
			String openid, String orderNo);
	
	/**
	 * 机器创建扫码支付
	 * 
	 * @param orders
	 * @return
	 */
	ResponseMessage createOrderByMac(String spbill_create_ip, String payGroup,FoodOrderEntity order);

	/**
	 * 根据机器码返回所有未取餐订单
	 * @param imei
	 * @return
	 */
	List<Map<String, Object>> findOrderInfoByMacIMEI(String  imei);
	/**
	 * 购物车下单
	 * 
	 * @param cartIds
	 */
	FoodOrderEntity createOrderByShoppingCart(Long userId, BigDecimal dis, List<FoodShoppingCartEntity> carts);

	/**
	 * 普通下单
	 * 
	 * @param orders
	 * @return
	 */
	FoodOrderEntity createOrder(Long userId, BigDecimal dis, FoodOrderEntity order);

	/**
	 * 计算订单金额，并验证产品是否有效
	 * 
	 * @param order
	 * @return
	 */
	FoodOrderEntity calCheckTotalFee(Long userId, BigDecimal dis,
			FoodOrderEntity order);
	
	List<FoodOrderEntity> findOrderByUser(Long userId,Integer status, Integer size,Integer offset);

	void modifyQrCodeByOrderNo(String orderNo, String qrCode);

	Map<String, Object> getSweeptCodeOrderInfo(String orderId);

	ResponseMessage takeFoodCallback(String orderId);

	ResponseMessage pushOrder(Long orderId);

	/**
	 * 根据取餐码获取订单
	 * @param macId
	 * @param takeFoodCode
	 * @return
	 */
	ResponseMessage getOrderNoByCode(Long macId, Integer takeFoodCode);

	/**
	 * 生成取餐码
	 * @param macId
	 * @param orderNo
	 * @return
	 */
	ResponseMessage createTakeFoodCode(Long macId, String orderNo);

}
