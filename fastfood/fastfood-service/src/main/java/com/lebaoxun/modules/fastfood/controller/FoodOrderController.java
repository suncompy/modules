package com.lebaoxun.modules.fastfood.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lebaoxun.commons.utils.DateUtil;
import com.lebaoxun.commons.utils.StringUtils;
import com.lebaoxun.modules.fastfood.entity.FoodOrderChildsEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.gson.Gson;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.MD5;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderChildsService;
import com.lebaoxun.modules.fastfood.service.FoodOrderService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 订单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodOrderController {
    @Autowired
    private FoodOrderService foodOrderService;

    @Autowired
    private FoodOrderChildsService foodOrderChildsService;
    
    @Resource
	private IRabbitmqSender rabbitmqSender;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodOrderService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id){
		FoodOrderEntity foodOrder = foodOrderService.selectById(id);
        return ResponseMessage.ok().put("foodOrder", foodOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodorder/save")
    @RedisLock(value="fastfood:foodorder:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderEntity foodOrder){
		foodOrderService.insert(foodOrder);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodorder/update")
    @RedisLock(value="fastfood:foodorder:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderEntity foodOrder){
        foodOrderService.updateById(foodOrder);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodorder/delete")
    @RedisLock(value="fastfood:foodorder:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] ids){
		foodOrderService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }
    
    
    /**
	 * 微信小程序支付订单
	 * 
	 * @param userId
	 * @param dis
	 * @param orderNo
	 * @return
	 */
    @RequestMapping("/fastfood/foodorder/wxAppPayForOrder")
    @RedisLock(value="fastfood:foodorder:wxAppPayForOrder:lock:#arg0")
	ResponseMessage wxAppPayForOrder(
			@RequestParam("userId") Long userId, 
			@RequestParam(value="dis",required=false)BigDecimal dis,
			@RequestParam("spbill_create_ip")String spbill_create_ip,
			@RequestParam("payGroup")String payGroup, 
			@RequestParam("openid")String openid, 
			@RequestParam("orderNo")String orderNo,
			@RequestParam(value="couponId",required=false)Integer couponId){
		return foodOrderService.wxAppPayForOrder(userId, dis, spbill_create_ip, payGroup, openid, orderNo, couponId);
	}
    
    @RequestMapping("/fastfood/foodorder/calCheckTotalFeeByOrderNo")
	ResponseMessage calCheckTotalFeeByOrderNo(
			@RequestParam("userId") Long userId, 
			@RequestParam("orderNo")String orderNo,
			@RequestParam(value="dis",required=false)BigDecimal dis){
    	return ResponseMessage.ok(foodOrderService.calCheckTotalFee(userId, orderNo, dis));
    }
	
    /**
	 * 余额支付
	 * @param userId
	 * @param dis
	 * @param orderNo
	 * @return
	 */
    @RequestMapping("/fastfood/foodorder/balancePayForOrder")
    @RedisLock(value="fastfood:foodorder:balancePayForOrder:lock:#arg0")
	ResponseMessage balancePayForOrder(
			@RequestParam("userId") Long userId, 
			@RequestParam("dis") BigDecimal dis,
			@RequestParam("orderNo") String orderNo,
			@RequestParam(value="couponId",required=false)Integer couponId){
    	ResponseMessage rm = foodOrderService.balancePayForOrder(userId, dis, orderNo, couponId);
    	if("0".equals(rm.getErrcode())){
    		Map<String,String> message = new HashMap<String,String>();
    		message.put("orderNo", orderNo);
    		message.put("buyTime", new Date().getTime()+"");
    		rabbitmqSender.sendContractDirect("order.pay.success.queue",
    				new Gson().toJson(message));
    	}
		return rm;
    }
    
    /**
	 * 购物车下单
	 * 
	 * @param macId
	 */
    @RequestMapping("/fastfood/foodorder/createOrderByShoppingCart")
    @RedisLock(value="fastfood:foodorder:createOrderByShoppingCart:lock:#arg0")
    ResponseMessage createOrderByShoppingCart(@RequestParam("macId") Integer macId,
    		@RequestParam("userId") Long userId, 
    		@RequestParam(value="dis",required=false)BigDecimal dis,
    		@RequestBody List<FoodShoppingCartEntity> carts){
    	return ResponseMessage.ok(foodOrderService.createOrderByShoppingCart(userId, dis, carts));
    }

	/**
	 * 普通下单
	 * 
	 * @param macId
	 * @return
	 */
    @RequestMapping("/fastfood/foodorder/createOrder")
    @RedisLock(value="fastfood:foodorder:createOrder:lock:#arg0")
    ResponseMessage createOrder(
    		@RequestParam("macId") Integer macId,
    		@RequestParam("userId") Long userId,
    		@RequestParam(value="dis",required=false)BigDecimal dis,
    		@RequestBody FoodOrderEntity order){
    	return ResponseMessage.ok(foodOrderService.createOrder(userId,dis, order));
    }
    
    @RequestMapping("/fastfood/foodorder/cancelOrder")
    @RedisLock(value="fastfood:foodorder:cancelOrder:lock:#arg0")
    ResponseMessage cancelOrder(@RequestParam("userId") Long userId,@RequestParam("orderNo") String orderNo){
    	return ResponseMessage.ok(foodOrderService.cancelOrder(userId, orderNo));
    }
    
    /**
	 * 抽奖兑换
	 * @param userId
	 * @param prizeLogId
	 * @return
	 */
    @RequestMapping("/fastfood/foodorder/prizeExchangeForOrder")
    @RedisLock(value="fastfood:foodorder:prizeExchangeForOrder:lock:#arg0")
    ResponseMessage prizeExchangeForOrder(@RequestParam("userId") Long userId, 
			@RequestParam("prizeLogId") Integer prizeLogId){
    	FoodOrderEntity order = foodOrderService.prizeExchangeForOrder(userId, prizeLogId);
    	if(order != null && order.getId() != null){
    		Map<String,String> message = new HashMap<String,String>();
    		message.put("orderNo", order.getOrderNo());
    		rabbitmqSender.sendContractDirect("order.pay.success.queue",
    				new Gson().toJson(message));
    		
    		String adjunctInfo = "prizeLogId="+prizeLogId+"|orderNo="+order.getOrderNo();
    		String logType = "PRIZE_EXCHANGE_FOR_ORDER";
        	Date now = new Date();
        	message = new HashMap<String,String>();
    		String timestamp = now.getTime()+"";
    		message.put("userId", userId+"");
    		message.put("timestamp", timestamp);
    		message.put("logType", logType);
    		message.put("platform", null);
    		message.put("tradeMoney", null);
    		message.put("money", null);
    		message.put("descr", "");
    		message.put("adjunctInfo", adjunctInfo);
    		message.put("token", MD5.md5(logType+"_"+adjunctInfo));
    		rabbitmqSender.sendContractDirect("account.log.queue",
    				new Gson().toJson(message));
    	}
    	return ResponseMessage.ok(order);
	}
	
    
    /**
     * 计算订单金额，并验证产品是否有效
     * @param order
     * @return
     */
    @RequestMapping("/fastfood/foodorder/calCheckTotalFee")
    ResponseMessage calCheckTotalFee(@RequestParam("userId") Long userId,
    		@RequestParam(value="dis",required=false)BigDecimal dis,
    		@RequestBody FoodOrderEntity order){
    	return ResponseMessage.ok(foodOrderService.calCheckTotalFee(userId,dis,order,true,true));
    }
    
    /**
     * 设置订单是否加热
     * @param orderId
     * @param productId
     * @return
     */
    @RequestMapping("/fastfood/foodorder/modifyWarmFlag")
    ResponseMessage modifyWarmFlag(@RequestParam("userId") Long userId,
            @RequestParam("orderNo") String orderNo,
            @RequestParam("warmFlag") Integer warmFlag){
    	FoodOrderEntity order = foodOrderService.selectOne(new EntityWrapper<FoodOrderEntity>().eq("user_id", userId).eq("order_no", orderNo));
    	if(order == null){
    		throw new I18nMessageException("60007","订单不存在");
    	}
    	if(order.getOrderStatus() != 1){
    		throw new I18nMessageException("60020","此订单不能设置是否加热");
    	}
		order.setWarmFlag(warmFlag);
        foodOrderService.updateById(order);
        return ResponseMessage.ok();

    }
    
    @RequestMapping("/fastfood/foodorder/addInvoice")
    @RedisLock(value="fastfood:foodorder:addInvoice:lock:#arg0")
    ResponseMessage addInvoice(@RequestParam("userId") Long userId,
    		@RequestParam("orderNo") String orderNo,
    		@RequestParam("invoiceFlag")Integer invoiceFlag,
    		@RequestParam(value="invoiceIRD",required=false)String invoiceIRD,
    		@RequestParam("invoiceEmail")String invoiceEmail,
    		@RequestParam("invoiceTitle")String invoiceTitle){
    	FoodOrderEntity order = foodOrderService.selectOne(new EntityWrapper<FoodOrderEntity>().eq("user_id", userId).eq("order_no", orderNo));
    	if(order == null){
    		throw new I18nMessageException("60007");
    	}
    	order.setInvoiceEmail(invoiceEmail);
    	order.setInvoiceFlag(invoiceFlag);
    	order.setInvoiceIrd(invoiceIRD);
    	order.setInvoiceTitle(invoiceTitle);
    	foodOrderService.updateById(order);
    	return ResponseMessage.ok();
    }

    /**
     * 扫码查询订单信息
     * @param orderId
     * @return
     */
    @RequestMapping("/fastfood/getSweeptCodeOrderInfo")
    ResponseMessage getSweeptCodeOrderInfo(@RequestParam("orderId") String orderId) {
        Map<String,Object> orderMap=foodOrderService.getSweeptCodeOrderInfo(orderId);
        if (orderMap==null||orderMap.size()==0)
            return ResponseMessage.error("600002","订单不存！");
        //校验订单，加时间判断是否有效(只能取当天的)
        //只要接收到扫码请求，就将该订单状态改为(扫码中)
        String orderTime=(String) orderMap.get("orderTime");
        ResponseMessage ret=foodOrderService.takeFoodCheckOrder(Long.parseLong(orderId),
                null,orderTime.substring(0,10));
        if (ret!=null)
            return ret;
        List<Map<String,Object>> orderChildList=foodOrderChildsService.getSweeptCodeOrderChildsInfo(orderId);
        orderMap.put("orderLines",orderChildList);
        return ResponseMessage.ok(orderMap);
    }

    /**
     * 取餐订单入队
     * @param orderId
     * @return
     */
    @RequestMapping("/take_food/push_order")
    ResponseMessage pushOrder(@RequestParam("orderId") Long orderId){
        return foodOrderService.pushOrder(orderId);
    }

    /**
     * 取餐回调接口
     * @param orderId
     * @return
     */
    @RequestMapping("/take_food/takeFoodCallback")
    ResponseMessage takeFoodCallback(
            @RequestParam("orderId") String orderId){
        return foodOrderService.takeFoodCallback(orderId);
    }

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
            @RequestParam("productId") String productId){
        long ret=foodOrderChildsService.updateTakeNum(orderId, macId, productId);
        if (ret>0) {
            FoodOrderEntity foodOrderEntity=foodOrderService.selectById(orderId);
            //取餐成功发送MQ日志
            Date now = new Date();
            Map<String,String> message = new HashMap<String,String>();
            String timestamp = now.getTime()+"";
            String logType = "PAY_FOOD_ORDER_COMPLETE";
            message.put("userId", foodOrderEntity.getUserId()+"");
    		message.put("timestamp", timestamp);
    		message.put("logType", logType);
    		message.put("descr", "取餐成功");
    		message.put("adjunctInfo", orderId);
    		message.put("token", MD5.md5(logType+"_"+orderId));
    		
            rabbitmqSender.sendContractDirect("account.log.queue",
                    new Gson().toJson(message));
            return ResponseMessage.ok();
        }
        else {
            return ResponseMessage.error("60001", "更新失败");
        }

    }
    
    @RequestMapping("/fastfood/foodorder/findOrderByUser")
    ResponseMessage findOrderByUser(
    		@RequestParam("userId")Long userId,
    		@RequestParam(value="status",required=false)Integer status, 
    		@RequestParam(value="size",required=false)Integer size,
    		@RequestParam(value="offset",required=false)Integer offset){
    	return ResponseMessage.ok(foodOrderService.findOrderByUser(userId, status, size, offset));
    }
    
    @RequestMapping("/fastfood/foodorder/findOrderInfoByUser")
    ResponseMessage findOrderInfoByUser(@RequestParam(value="userId",required=false) Long userId,
    		@RequestParam("orderNo") String orderNo){
    	return ResponseMessage.ok(foodOrderService.findOrderInfoByUser(userId, orderNo));
    }
    /**
     * 根据取餐码获取订单接口
     * @param macId
     * @return
     */
    @RequestMapping("/fastfood/foodorder/getOrderNoByCode")
    ResponseMessage getOrderNoByCode(@RequestParam("macId") Long macId,
                        @RequestParam("takeFoodCode") Integer takeFoodCode){
        return foodOrderService.getOrderNoByCode(macId,takeFoodCode);
    }

    /**
     * 根据取餐码获取订单接口
     * @param macId
     * @return
     */
    @RequestMapping("/fastfood/foodorder/createTakeFoodCode")
    ResponseMessage createTakeFoodCode(@RequestParam("macId") Integer macId,
                                     @RequestParam("orderNo") String orderNo){
        return ResponseMessage.ok(foodOrderService.createTakeFoodCode(macId,orderNo));
    }

    /**
     * 获取订单状态
     * @return
     */
    @RequestMapping("/take_food/get_order_status")
    ResponseMessage getOrderStatus(@RequestParam(value = "orderId",required = false) Long orderId,
                          @RequestParam(value = "orderNo",required = false) String orderNo){
        if ((orderId==null||orderId==0)&& StringUtils.isEmpty(orderNo))
            return ResponseMessage.error("600001","orderId,orderNo至少有一个不为空!");
        return foodOrderService.getOrderStatus(orderId,orderNo);
    }

    /**
     * 机器创建扫码支付
     *
     * @param spbill_create_ip
     * @param payGroup 支付码
     * @return
     */
    @RequestMapping("/take_food/createOrderByMac")
    @RedisLock(value="fastfood:foodorder:createOrderByMac:lock:#arg0")
    ResponseMessage createOrderByMac(@RequestParam(value = "spbill_create_ip")String spbill_create_ip,
                                     @RequestParam(value = "payGroup") String payGroup,
                                     @RequestBody FoodOrderEntity order){
        return foodOrderService.createOrderByMac(spbill_create_ip,payGroup,order);
    }

    /**
     * 根据机器码返回所有未取餐订单
     * @param imei 机器编号
     * @return
     */
    @RequestMapping("/take_food/findOrderInfoByMacIMEI")
    ResponseMessage findOrderInfoByMacIMEI(@RequestParam(value = "imei")String  imei){
        return ResponseMessage.ok(foodOrderService.findOrderInfoByMacIMEI(imei));
    }
    
    /**
	 * 订单设置
	 * @param timeOut
	 * @param nopayLimit
	 * @return
	 */
    @RequestMapping("/fastfood/foodorder/modifyOrderConfig")
    @RedisLock(value="fastfood:foodorder:modifyOrderConfig:lock:")
    ResponseMessage modifyOrderConfig(@RequestParam(value = "timeOut") Integer timeOut,
    		@RequestParam(value = "nopayLimit")Integer nopayLimit){
    	return ResponseMessage.ok(foodOrderService.modifyOrderConfig(timeOut, nopayLimit));
    }
	
	/**
	 * 查询订单配置
	 * @return
	 */
    @RequestMapping("/fastfood/foodorder/findOrderConfig")
    ResponseMessage findOrderConfig(){
    	return ResponseMessage.ok(foodOrderService.findOrderConfig());
	}
}
