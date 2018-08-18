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
import com.lebaoxun.modules.account.service.UserScoreAchievementAwardService;

/**
 * 用户积分奖励 收集
 * @author thb
 *
 */
@Component
@RabbitListener(queues = "account.score.award.queue")
public class UserScoreAwardListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private UserScoreAchievementAwardService userScoreAchievementAwardService;
	
	@Bean
    public Queue queuePay() {
        return new Queue("account.score.award.queue",true);
    }

    @Bean
    Binding bindingDirectExchangePay(Queue queueBuyCard, DirectExchange directExchange) {
        return BindingBuilder.bind(queueBuyCard).to(directExchange).with("account.score.award.queue");
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
					logId = message.getLong("logId");
			//根据玩家ID + 操作行为，发放玩家任务积分
			userScoreAchievementAwardService.awardUserScore(userId, logId);
		}  catch (Exception e) {
			logger.error("error|body={}",body);
			e.printStackTrace();
		}
    }
}
