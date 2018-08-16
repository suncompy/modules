package com.lebaoxun.modules.account.listener;

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
import com.lebaoxun.modules.account.service.UserService;

/**
 * 充值回调
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = "pay.notify.queue")
public class PayListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserService userService;
	
	@Bean
    public Queue queuePay() {
        return new Queue("pay.notify.queue",true);
    }

    @Bean
    Binding bindingDirectExchangePay(Queue queueBuyCard, DirectExchange directExchange) {
        return BindingBuilder.bind(queueBuyCard).to(directExchange).with("pay.notify.queue");
    }
	
	@RabbitHandler
    public void receive(Object data) throws MessagingException {
		Message m = (Message) data;
		String body = new String(m.getBody());
		logger.debug("body={}",body);
		String text = body.replace("\\\"", "\"");
		JSONObject message = JSONObject.parseObject(text);
		String out_trade_no = message.getString("out_trade_no"),
				trade_no = message.getString("trade_no"),
				mercno = message.getString("merc_no"),
				platform = message.getString("platform"),
				trade_type = message.getString("trade_type"),
				total_fee = message.getString("total_fee"),
				recharge_fee = message.getString("recharge_fee"),
				group = message.getString("group"),
				scene = message.getString("scene");
		
		Long user_id = message.getLong("user_id");
		Long buy_time = message.getLong("buy_time");
		try {
			logger.info(
					"out_trade_no={},buy_time={},platform={},total_fee={},recharge_fee={},trade_no={},mercno={},group={},scene={}",
					out_trade_no, buy_time, platform, trade_type, total_fee,
					recharge_fee, trade_no, mercno, group,scene);
			if("recharge".equals(scene)){
				userService.recharge(user_id, out_trade_no, buy_time, recharge_fee);
			}
		}  catch (Exception e) {
			logger.error("error|body={}",body);
			e.printStackTrace();
		}
    }
}
