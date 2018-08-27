package com.lebaoxun.modules.fastfood.listener;

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
import com.lebaoxun.modules.fastfood.service.FoodOrderService;
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
	private IQrcodeUploadService qrcodeUploadService;
	
	@Resource
	private FoodOrderService foodOrderService;
	
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
			Map<String,String> map = qrcodeUploadService.createAndUpload("aliyunCloud", "", orderNo);
			String qrCode = map.get("uri");
			foodOrderService.modifyQrCodeByOrderNo(orderNo, qrCode);
		}  catch (Exception e) {
			logger.error("error|body={}",body);
			e.printStackTrace();
		}
    }
}
