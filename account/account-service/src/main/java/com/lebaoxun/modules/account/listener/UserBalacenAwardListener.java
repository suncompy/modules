package com.lebaoxun.modules.account.listener;

import java.math.BigDecimal;
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
import com.lebaoxun.modules.account.entity.UserEntity;
import com.lebaoxun.modules.account.service.UserService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;

/**
 * 充值回调
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = "account.balance.queue.award")
public class UserBalacenAwardListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserService userService;
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	@Bean
    public Queue queueBalanceAward() {
        return new Queue("account.balance.queue.award",true);
    }

    @Bean
    Binding bindingTopicExchangePayRecharge(Queue queueBalanceAward, DirectExchange directExchange) {
        return BindingBuilder.bind(queueBalanceAward).to(directExchange).with("account.balance.queue.award");
    }
	
	@RabbitHandler
    public void receive(Object data) throws MessagingException {
		Message m = (Message) data;
		String body = new String(m.getBody());
		logger.debug("body={}",body);
		String text = body.replace("\\\"", "\"");
		JSONObject message = JSONObject.parseObject(text);
		try {
			String recharge_fee = message.getString("recharge_fee"),
						logType = message.getString("log_type"),
							descr = message.getString("descr"),
								token = message.getString("token"),
									adjunctInfo = message.getString("adjunctInfo");
			
			Long user_id = message.getLong("user_id");
			Long buy_time = message.getLong("buy_time");
			UserEntity user = userService.modifyBalance(user_id, new BigDecimal(recharge_fee), descr, null);
			if(user != null){
				Map<String,String> msg = new HashMap<String,String>();
				String timestamp = buy_time+"";
				msg.put("userId", user_id+"");
				msg.put("timestamp", timestamp);
				msg.put("logType", logType);
				msg.put("platform", null);
				msg.put("tradeMoney", recharge_fee);
				msg.put("money", user.getBalance().toString());
				msg.put("descr", descr);
				msg.put("adjunctInfo", adjunctInfo);
				msg.put("token", token);
				
				rabbitmqSender.sendContractDirect("account.log.queue",
						new Gson().toJson(msg));
			}
		}  catch (Exception e) {
			logger.error("error|body={}",body);
			e.printStackTrace();
		}
    }
}
