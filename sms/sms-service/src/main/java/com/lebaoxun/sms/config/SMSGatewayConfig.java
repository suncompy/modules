package com.lebaoxun.sms.config;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.lebaoxun.commons.utils.MD5;
import com.lebaoxun.sms.core.RedisKeyConstant;
import com.lebaoxun.sms.core.SMSGateway;
import com.lebaoxun.sms.core.SMSGatewayClient;
import com.lebaoxun.soa.core.redis.IRedisCache;
import com.lebaoxun.soa.core.redis.IRedisHash;

@Configuration
public class SMSGatewayConfig {
	
	@Resource
	private IRedisHash redisHash;
	
	@Resource
	private IRedisCache redisCache;
	
	@Bean
	public SMSGateway initGateway(){
		
		//畅卓科技
		String passwd = MD5.encodeByMD5ForUpperCase("jzpw78xq1i");
		//畅卓科技
		String gatewayName = "czkj";
		SMSGateway czkj = new SMSGateway();
		czkj.setCharset("utf-8");
		czkj.setGatewayName(gatewayName);
		czkj.setJson(false);
		czkj.setMethod(RequestMethod.POST);
		czkj.setSignature("畅卓科技");
		czkj.setSuccessText("发送成功");
		czkj.setUrl("http://api.chanzor.com/send");
		czkj.setWeight(10);
		czkj.setRequestBody("account=98abec&password="+passwd+"&mobile=%s&content=%s");
		
		//SMSGateway Rlyun = new SMSGateway(RlyunSMSGatewayClient.class);
		
		if(!redisHash.hExists(RedisKeyConstant.HASH_SMS_GATEWAY_CONFIGS, gatewayName)){
			redisHash.hSet(RedisKeyConstant.HASH_SMS_GATEWAY_CONFIGS, gatewayName, JSONObject.toJSON(czkj).toString());
		}
		if(!redisHash.hExists(RedisKeyConstant.HASH_SMS_VFCODE_TEMPLATE_IDS, "f2d5483a0a1544b08e60fed734448392")){
			redisHash.hSet(RedisKeyConstant.HASH_SMS_VFCODE_TEMPLATE_IDS,"f2d5483a0a1544b08e60fed734448392","#vfcode#（动态验证码），10分钟内有效，请尽快操作，进行验证！#signature#");
		}
		if(!redisHash.hExists(RedisKeyConstant.HASH_SMS_SECRET_CSTID, "10086")){
			redisHash.hSet(RedisKeyConstant.HASH_SMS_SECRET_CSTID,"10086","f833605b0361410896d179d84bbe3402");
		}
		String sstac_10086 = String.format(RedisKeyConstant.HASH_SMS_SEND_TIME_ASTRICT_CSTID, "10086");
		if(!redisCache.exists(sstac_10086)){
			redisHash.hSet(sstac_10086,(5 * 60)+"",3);
			redisHash.hSet(sstac_10086,(10 * 60)+"",8);
			redisHash.hSet(sstac_10086,(60 * 60)+"",10);
			redisHash.hSet(sstac_10086,"yyyyMMdd",20);
		}
		
		if(!redisCache.exists(RedisKeyConstant.SMS_GATEWAY_USE_CURRENT)){
			redisCache.set(RedisKeyConstant.SMS_GATEWAY_USE_CURRENT, "RANDOM");
		}
		return null;
	}
	
	public static void main(String[] args) {
		String passwd = MD5.encodeByMD5ForUpperCase("jzpw78xq1i");
		System.out.println(passwd);
		//畅卓科技
		String gatewayName = "czkj";
		SMSGateway czkj = new SMSGateway();
		czkj.setCharset("utf-8");
		czkj.setGatewayName(gatewayName);
		czkj.setJson(false);
		czkj.setMethod(RequestMethod.POST);
		czkj.setSignature("畅卓科技");
		czkj.setSuccessText("发送成功");
		czkj.setUrl("http://api.chanzor.com/send");
		czkj.setWeight(10);
		czkj.setRequestBody("account=98abec&password="+passwd+"&mobile=%s&content=%s");
	    
		SMSGatewayClient client = new SMSGatewayClient();
		client.send(czkj, "15010602819", "12331（动态验证码），10分钟内有效，请尽快操作，进行验证！【畅卓科技】");
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
}
