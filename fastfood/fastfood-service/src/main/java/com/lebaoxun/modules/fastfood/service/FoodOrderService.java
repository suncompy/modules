package com.lebaoxun.modules.fastfood.service;

import java.math.BigDecimal;
import java.util.Date;
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
			String openid, String orderNo, Integer couponId);
	
	/**
	 * 机器创建扫码支付
	 * 
	 * @param orders
	 * @return
	 */
	ResponseMessage createOrderByMac(String spbill_create_ip, String payGroup,FoodOrderEntity order);

	/**
	 * 根据机器码返回所有未取餐订单
	 * @param imei 机器编号
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
	 * 订单设置
	 * @param timeOut
	 * @param nopayLimit
	 * @return
	 */
	Map<String,Integer> modifyOrderConfig(Integer timeOut,Integer nopayLimit);
	
	/**
	 * 查询订单配置
	 * @return
	 */
	Map<String,Integer> findOrderConfig();

	/**
	 * 计算订单金额，并验证产品是否有效
	 * @param userId
	 * @param dis
	 * @param order
	 * @param isCheckStock 是否校验库存
	 * @param isCheckActivity 是否校验活动
	 * @return
	 */
	FoodOrderEntity calCheckTotalFee(Long userId, BigDecimal dis,
			FoodOrderEntity order,boolean isCheckStock,boolean isCheckActivity);
	
	/**
	 * 计算订单金额，并验证产品是否有效
	 * @param userId
	 * @param dis
	 * @param order
	 * @param isCheckStock 是否校验库存
	 * @param isCheckActivity 是否校验活动
	 * @return
	 */
	FoodOrderEntity calCheckTotalFee(Long userId, String orderNo,BigDecimal dis);
	
	/**
	 * 余额支付
	 * @param userId
	 * @param dis
	 * @param orderNo
	 * @return
	 */
	ResponseMessage balancePayForOrder(Long userId, BigDecimal dis,
			String orderNo,Integer couponId);
	
	/**
	 * 抽奖兑换
	 * @param userId
	 * @param dis
	 * @param orderNo
	 * @return
	 */
	FoodOrderEntity prizeExchangeForOrder(Long userId, Integer prizeLogId);
	
	List<FoodOrderEntity> findOrderByUser(Long userId,Integer status, Integer size,Integer offset);
	
	void closeOrderByNopayAndTimeout();
	
	FoodOrderEntity findOrderInfoByUser(Long userId,String orderNo);

	/**
	 * 支付订单成功接口
	 * @param orderNo
	 * @param qrCode
	 * @param takeFoodCode
	 * @return
	 */
	FoodOrderEntity payFoodOrder(String orderNo, String buyTime);

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
	Integer createTakeFoodCode(Integer macId, String orderNo);

	ResponseMessage getOrderStatus(Long orderId,String orderNo);

	/**
	 * 扫码、取餐码取餐，校验订单
	 * 校验订单，加时间判断是否有效(只能取当天的)
	 * 只要接收到扫码请求，就将该订单状态改为(扫码中)
	 * @param orderId
	 * @param orderTime
	 * @return
	 */
	public ResponseMessage takeFoodCheckOrder(Long orderId, Date orderTime, String orderTimeStr);

}
