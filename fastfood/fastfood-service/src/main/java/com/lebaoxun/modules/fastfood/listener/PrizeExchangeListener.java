package com.lebaoxun.modules.fastfood.listener;

import java.util.Date;
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
import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;

/**
 * 用户日志 收集
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = "order.prize.exchange.queue")
public class PrizeExchangeListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private FoodOrderService foodOrderService;
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	@Bean
    public Queue queuePrizeExchange() {
        return new Queue("order.prize.exchange.queue",true);
    }

    @Bean
    Binding bindingDirectExchangePrizeExchange(Queue queuePrizeExchange, DirectExchange directExchange) {
        return BindingBuilder.bind(queuePrizeExchange).to(directExchange).with("order.prize.exchange.queue");
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
			Long userId = message.getLong("userId");
			Integer prizeLogId = message.getInteger("prizeLogId");
			
			FoodOrderEntity order = foodOrderService.prizeExchangeForOrder(userId, prizeLogId);
	    	if(order != null && order.getId() != null){
	    		Map<String,String> imessage = new HashMap<String,String>();
	    		message.put("orderNo", order.getOrderNo());
	    		rabbitmqSender.sendContractDirect("order.pay.success.queue",
	    				new Gson().toJson(message));
	    		
	    		String adjunctInfo = "prizeLogId="+prizeLogId+"|orderNo="+order.getOrderNo();
	    		String logType = "PRIZE_EXCHANGE_FOR_ORDER";
	        	Date now = new Date();
	        	imessage = new HashMap<String,String>();
	    		String timestamp = now.getTime()+"";
	    		imessage.put("userId", userId+"");
	    		imessage.put("timestamp", timestamp);
	    		imessage.put("logType", logType);
	    		imessage.put("platform", null);
	    		imessage.put("tradeMoney", null);
	    		imessage.put("money", null);
	    		imessage.put("descr", "");
	    		imessage.put("adjunctInfo", adjunctInfo);
	    		imessage.put("token", MD5.md5(logType+"_"+adjunctInfo));
	    		rabbitmqSender.sendContractDirect("account.log.queue",
	    				new Gson().toJson(imessage));
	    	}
		}  catch (Exception e) {
			logger.error("error|body={}",body);
			e.printStackTrace();
		}
    }
}
