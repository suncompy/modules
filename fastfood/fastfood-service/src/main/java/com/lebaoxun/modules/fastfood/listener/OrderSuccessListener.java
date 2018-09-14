package com.lebaoxun.modules.fastfood.listener;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lebaoxun.commons.utils.MD5;
import com.lebaoxun.modules.account.entity.UserEntity;
import com.lebaoxun.modules.account.service.IUserService;
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;
import com.lebaoxun.upload.service.IQrcodeUploadService;

/**
 * 用户日志 收集
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = "order.pay.success.queue")
public class OrderSuccessListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private FoodOrderService foodOrderService;
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	@Bean
    public Queue queuePaySuccess() {
        return new Queue("order.pay.success.queue",true);
    }

    @Bean
    Binding bindingDirectExchangeAccountLog(Queue queuePaySuccess, DirectExchange directExchange) {
        return BindingBuilder.bind(queuePaySuccess).to(directExchange).with("order.pay.success.queue");
    }
	
	@RabbitHandler
    public void receive(Object data) throws MessagingException {
		Message m = (Message) data;
		String body = new String(m.getBody());
		logger.debug("body={}",body);
		String text = body.replace("\\\"", "\"");
		JSONObject message = JSONObject.parseObject(text);
		
		logger.info("rabbit|sendContractDirect|message={}",message);
		try {
			String orderNo = message.getString("orderNo");
			String buyTime = message.getString("buyTime");
			
			//1.修改订单状态1=支付成功，2.设置取餐码 3.设置取餐码
			FoodOrderEntity order = foodOrderService.payFoodOrder(orderNo, buyTime);
			
			if(order.getUserId() != null){
				String logType = "PAY_FOOD_ORDER";
				Map<String,String> pmessage = new HashMap<String,String>();
				pmessage.put("userId", order.getUserId()+"");
				pmessage.put("timestamp", buyTime);
				pmessage.put("logType", logType);
				pmessage.put("platform", null);
				pmessage.put("tradeMoney", order.getPayAmount().toString());
				pmessage.put("money", null);
				pmessage.put("descr", "支付餐品订单");
				pmessage.put("adjunctInfo", orderNo);
				pmessage.put("token", MD5.md5(logType+"_"+orderNo));
				
				rabbitmqSender.sendContractDirect("account.log.queue",
    					new Gson().toJson(pmessage));
				
				UserEntity user = userService.findByUserId(order.getUserId());
				if(user.getInviter() != null){
					String logTypeInviter = "PAY_FOOD_ORDER_INVITER";
					Map<String,String> pmessageInviter = new HashMap<String,String>();
					pmessageInviter.put("userId", user.getInviter()+"");
					pmessageInviter.put("timestamp", buyTime);
					pmessageInviter.put("logType", logTypeInviter);
					pmessageInviter.put("platform", null);
					pmessageInviter.put("descr", "邀请人支付餐品订单");
					pmessageInviter.put("adjunctInfo", orderNo);
					pmessageInviter.put("token", MD5.md5(logTypeInviter+"_"+orderNo));
					
					rabbitmqSender.sendContractDirect("account.log.queue",
	    					new Gson().toJson(pmessageInviter));
				}
			}
		}  catch (Exception e) {
			logger.error("error|body={}",body);
			e.printStackTrace();
		}
    }
}
