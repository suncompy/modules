package com.lebaoxun.modules.fastfood.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodOrderServiceHystrix;

/**
 * 订单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodOrderServiceHystrix.class)
public interface IFoodOrderService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodorder/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderEntity foodOrder);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodorder/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderEntity foodOrder);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodorder/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] ids);

    /**
     * 扫码查询订单信息
     * @param orderId
     * @return
     */
    @RequestMapping("/fastfood/getSweeptCodeOrderInfo")
    ResponseMessage getSweeptCodeOrderInfo(@RequestParam("orderId") String orderId);

    /**
     * 取餐订单入队
     * @param orderId
     * @return
     */
    @RequestMapping("/take_food/push_order")
    ResponseMessage pushOrder(@RequestParam("orderId") Long orderId);

    /**
     * 取餐回调接口
     * @param orderId
     * @return
     */
    @RequestMapping("/take_food/takeFoodCallback")
    ResponseMessage takeFoodCallback(
            @RequestParam("orderId") String orderId);

	/**
	 * 取餐回调更改已取餐商品数
	 * @param orderId
	 * @param productId
	 * @return
	 */
	@RequestMapping("/fastfood/foodorder/updateTakeNum")
	ResponseMessage updateTakeNum(
			@RequestParam("orderId") String orderId,
			@RequestParam("macId") String macId,
			@RequestParam("productId") String productId);
    
    /**
	 * 微信小程序支付订单
	 * 
	 * @param userId
	 * @param isFirstOrder
	 * @param dis
	 * @param orderNo
	 * @return
	 */
    @RequestMapping("/fastfood/foodorder/wxAppPayForOrder")
	ResponseMessage wxAppPayForOrder(
			@RequestParam("userId") Long userId, 
			@RequestParam(value="dis",required=false)BigDecimal dis,
			@RequestParam("spbill_create_ip")String spbill_create_ip,
			@RequestParam("payGroup")String payGroup, 
			@RequestParam("openid")String openid, 
			@RequestParam("orderNo")String orderNo,
			@RequestParam(value="couponId",required=false)Integer couponId);
    
    @RequestMapping("/fastfood/foodorder/calCheckTotalFeeByOrderNo")
	ResponseMessage calCheckTotalFeeByOrderNo(
			@RequestParam("userId") Long userId, 
			@RequestParam("orderNo")String orderNo,
			@RequestParam(value="dis",required=false)BigDecimal dis);
    
    /**
	 * 余额支付
	 * @param userId
	 * @param dis
	 * @param orderNo
	 * @return
	 */
    @RequestMapping("/fastfood/foodorder/balancePayForOrder")
	ResponseMessage balancePayForOrder(
			@RequestParam("userId") Long userId, 
			@RequestParam("dis") BigDecimal dis,
			@RequestParam("orderNo") String orderNo,
			@RequestParam(value="couponId",required=false)Integer couponId);
	
    /**
	 * 购物车下单
	 * 
	 * @param cartIds
	 */
    @RequestMapping("/fastfood/foodorder/createOrderByShoppingCart")
    ResponseMessage createOrderByShoppingCart(@RequestParam("macId") Integer macId,
    		@RequestParam("userId") Long userId, 
    		@RequestParam(value="dis",required=false)BigDecimal dis,
    		@RequestBody List<FoodShoppingCartEntity> carts);

	/**
	 * 普通下单
	 * 
	 * @param orders
	 * @return
	 */
    @RequestMapping("/fastfood/foodorder/createOrder")
    ResponseMessage createOrder(
    		@RequestParam("macId") Integer macId,
    		@RequestParam("userId") Long userId,
    		@RequestParam(value="dis",required=false)BigDecimal dis,
    		@RequestBody FoodOrderEntity order);
    
    @RequestMapping("/fastfood/foodorder/cancelOrder")
    ResponseMessage cancelOrder(@RequestParam("userId") Long userId,@RequestParam("orderNo") String orderNo);
    
    /**
	 * 抽奖兑换
	 * @param userId
	 * @param dis
	 * @param orderNo
	 * @return
	 */
    @RequestMapping("/fastfood/foodorder/prizeExchangeForOrder")
    ResponseMessage prizeExchangeForOrder(@RequestParam("userId") Long userId, 
			@RequestParam("prizeLogId") Integer prizeLogId);
	
    
    /**
     * 计算订单金额，并验证产品是否有效
     * @param order
     * @return
     */
    @RequestMapping("/fastfood/foodorder/calCheckTotalFee")
    ResponseMessage calCheckTotalFee(@RequestParam("userId") Long userId,
    		@RequestParam(value="dis",required=false)BigDecimal dis,
    		@RequestBody FoodOrderEntity order);
    
    /**
     * 设置订单是否加热
     * @param orderId
     * @param productId
     * @return
     */
    @RequestMapping("/fastfood/foodorder/modifyWarmFlag")
    ResponseMessage modifyWarmFlag(@RequestParam("userId") Long userId,
            @RequestParam("orderNo") String orderNo,
            @RequestParam("warmFlag") Integer warmFlag);
    
    @RequestMapping("/fastfood/foodorder/findOrderByUser")
    ResponseMessage findOrderByUser(
    		@RequestParam("userId")Long userId,
    		@RequestParam(value="status",required=false)Integer status, 
    		@RequestParam(value="size",required=false)Integer size,
    		@RequestParam(value="offset",required=false)Integer offset);
    
    
    @RequestMapping("/fastfood/foodorder/findOrderInfoByUser")
    ResponseMessage findOrderInfoByUser(@RequestParam(value="userId",required=false) Long userId,
    		@RequestParam("orderNo") String orderNo);

	/**
	 * 根据取餐码获取订单接口
	 * @param macId
	 * @return
	 */
	@RequestMapping("/fastfood/foodorder/getOrderNoByCode")
	ResponseMessage getOrderNoByCode(@RequestParam("macId") Long macId,
						@RequestParam("takeFoodCode") Integer takeFoodCode);

	/**
	 * 获取订单状态
	 * @return
	 */
	@RequestMapping("/take_food/get_order_status")
	ResponseMessage getOrderStatus(@RequestParam(value = "orderId",required = false) Long orderId,
						  @RequestParam(value = "orderNo",required = false) String orderNo);

	/**
	 * 机器创建扫码支付
	 *
	 * @param spbill_create_ip
	 * @param payGroup 支付码
	 * @return
	 */
	@RequestMapping("/take_food/createOrderByMac")
	ResponseMessage createOrderByMac(@RequestParam(value = "spbill_create_ip")String spbill_create_ip,
									 @RequestParam(value = "payGroup") String payGroup,
									 @RequestBody FoodOrderEntity order);

	/**
	 * 根据机器码返回所有未取餐订单
	 * @param imei 机器编号
	 * @return
	 */
	@RequestMapping("/take_food/findOrderInfoByMacIMEI")
	ResponseMessage findOrderInfoByMacIMEI(@RequestParam(value = "imei")String  imei);
	
	
	/**
	 * 订单设置
	 * @param timeOut
	 * @param nopayLimit
	 * @return
	 */
    @RequestMapping("/fastfood/foodorder/modifyOrderConfig")
    ResponseMessage modifyOrderConfig(@RequestParam(value = "timeOut") Integer timeOut,
    		@RequestParam(value = "nopayLimit")Integer nopayLimit);
	
	/**
	 * 查询订单配置
	 * @return
	 */
    @RequestMapping("/fastfood/foodorder/findOrderConfig")
    ResponseMessage findOrderConfig();
    
    @RequestMapping("/fastfood/foodorder/addInvoice")
    ResponseMessage addInvoice(@RequestParam("userId") Long userId,
    		@RequestParam("orderNo") String orderNo,
    		@RequestParam("invoiceFlag")Integer invoiceFlag,
    		@RequestParam(value="invoiceIRD",required=false)String invoiceIRD,
    		@RequestParam("invoiceEmail")String invoiceEmail,
    		@RequestParam("invoiceTitle")String invoiceTitle);
}

