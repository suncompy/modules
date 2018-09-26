package com.lebaoxun.modules.manager.listener;

import java.util.Date;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lebaoxun.manager.sys.entity.SysUserEntity;
import com.lebaoxun.manager.sys.service.SysUserLogService;
import com.lebaoxun.manager.sys.service.SysUserService;
import com.lebaoxun.modules.manager.entity.SysUserLogEntity;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;

/**
 * 管理员日志 收集
 * @author caiqianyi
 *
 */
@Component
@RabbitListener(queues = "admin.log.queue")
public class AdminLogListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
    private SysUserLogService sysUserLogService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Resource
	private IRabbitmqSender rabbitmqSender;
	
	@Bean
    public Queue queueAdminLog() {
        return new Queue("admin.log.queue",true);
    }

    @Bean
    Binding bindingDirectExchangeAdminLog(Queue queueAdminLog, DirectExchange directExchange) {
        return BindingBuilder.bind(queueAdminLog).to(directExchange).with("admin.log.queue");
    }
	
	@RabbitHandler
    public void receive(Object data) throws MessagingException {
		Message m = (Message) data;
		String body = new String(m.getBody());
		logger.debug("body={}",body);
		String text = body.replace("\\\"", "\"");
		
		try {
			JSONObject message = JSONObject.parseObject(text);
			logger.debug("rabbit|sendContractDirect|message={}",message);
			Long userId = message.getLong("userId"),
					timestamp = message.getLong("timestamp");
			
			String logType = message.getString("logType"),
					descr = message.getString("descr"),
					token = message.getString("token"),
					adjunctInfo = message.getString("adjunctInfo"),
						refId = message.getString("refId");
			
			SysUserEntity admin = sysUserService.selectById(userId);
			if(admin == null){
				logger.error("id '{}' no found admin",userId);
				return;
			}
			SysUserLogEntity log = new SysUserLogEntity();
			log.setAdminId(userId);
			log.setMobile(admin.getMobile());
			log.setLogTime(new Date(timestamp));;
			log.setCreateTime(new Date());
			log.setLogType(logType);
			log.setDescr(descr);
			log.setAdjunctInfo(adjunctInfo);
			log.setToken(token);
			log.setRefId(refId);
			logger.debug("add log={}",new Gson().toJson(log));
			sysUserLogService.insert(log);
		}  catch (Exception e) {
			logger.error("error|body={}",body);
			e.printStackTrace();
		}
    }
}
