package com.lebaoxun.modules.fastfood.listener;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;

/**
 * 充值回调
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = "pay.notify.queue.shopping")
public class PayOrderListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	@Bean
    public Queue queuePayShopping() {
        return new Queue("pay.notify.queue.shopping",true);
    }

    @Bean
    Binding bindingTopicExchangePayShopping(Queue queuePayShopping, TopicExchange topicExchange) {
        return BindingBuilder.bind(queuePayShopping).to(topicExchange).with("pay.notify.queue.#");
    }
	
	@RabbitHandler
    public void receive(Object data) throws MessagingException {
		Message m = (Message) data;
		String body = new String(m.getBody());
		logger.debug("body={}",body);
		String text = body.replace("\\\"", "\"");
		JSONObject message = JSONObject.parseObject(text);
		try {
			String orderNo = message.getString("order_no");
			Long buy_time = message.getLong("buy_time");
			
			Map<String,String> mqmessage = new HashMap<String,String>();
			mqmessage.put("orderNo", orderNo);
			mqmessage.put("buyTime", buy_time+"");
    		rabbitmqSender.sendContractDirect("order.pay.success.queue",
    				new Gson().toJson(message));
		}  catch (Exception e) {
			logger.error("error|body={}",body);
			e.printStackTrace();
		}
    }
}
