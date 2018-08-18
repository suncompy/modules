package com.lebaoxun.modules.account.listener;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import com.lebaoxun.modules.account.entity.UserLogEntity;
import com.lebaoxun.modules.account.service.UserService;

/**
 * 用户日志 收集
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = "account.log.queue")
public class UserLogListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserService userService;
	
	@Bean
    public Queue queuePay() {
        return new Queue("account.log.queue",true);
    }

    @Bean
    Binding bindingDirectExchangePay(Queue queueBuyCard, DirectExchange directExchange) {
        return BindingBuilder.bind(queueBuyCard).to(directExchange).with("account.log.queue");
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
			Long userId = message.getLong("userId"),
					timestamp = message.getLong("timestamp");
			
			String logType = message.getString("logType"),
					platform = message.getString("platform"),
					tradeMoney = message.getString("tradeMoney"),
					money = message.getString("money"),
					descr = message.getString("descr"),
					token = message.getString("token"),
					adjunctInfo = message.getString("adjunctInfo");
			
			Integer score = message.getInteger("score"),
					tradeScore = message.getInteger("tradeScore");
			
			UserLogEntity log = new UserLogEntity();
			log.setUserId(userId);
			log.setLogTime(new Date(timestamp));;
			log.setCreateTime(new Date());
			log.setLogType(logType);
			log.setPlatform(platform);
			if(StringUtils.isNotBlank(tradeMoney))
				log.setTradeMoney(new BigDecimal(tradeMoney));
			if(StringUtils.isNotBlank(money))
				log.setMoney(new BigDecimal(money));
			if(score != null)
				log.setScore(score);
			if(tradeScore != null)
				log.setTradeScore(tradeScore);
			log.setDescr(descr);
			log.setAdjunctInfo(adjunctInfo);
			log.setToken(token);
			
			logger.info("add log={}",new Gson().toJson(log));
			boolean resule = userService.insertLog(log);
			if(resule){
				//通知任务系统
			}
		}  catch (Exception e) {
			logger.error("error|body={}",body);
			e.printStackTrace();
		}
    }
}
