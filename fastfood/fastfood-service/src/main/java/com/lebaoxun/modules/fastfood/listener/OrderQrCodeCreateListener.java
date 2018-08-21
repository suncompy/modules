package com.lebaoxun.modules.fastfood.listener;

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
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;

/**
 * 用户日志 收集
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = "order.qrcode.create.queue")
public class OrderQrCodeCreateListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	@Bean
    public Queue queueQrcodeCreate() {
        return new Queue("order.qrcode.create.queue",true);
    }

    @Bean
    Binding bindingDirectExchangeAccountLog(Queue queueQrcodeCreate, DirectExchange directExchange) {
        return BindingBuilder.bind(queueQrcodeCreate).to(directExchange).with("order.qrcode.create.queue");
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
			Long orderNo = message.getLong("orderNo");
			
		}  catch (Exception e) {
			logger.error("error|body={}",body);
			e.printStackTrace();
		}
    }
}
