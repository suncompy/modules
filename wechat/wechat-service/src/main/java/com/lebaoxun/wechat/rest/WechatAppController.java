package com.lebaoxun.wechat.rest;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.soa.core.redis.IRedisHash;
import com.lebaoxun.wechat.service.IWechatApiService;
import com.lebaoxun.wechat.vo.AccessToken;
import com.lebaoxun.wechat.vo.WechatOAConfig;

@RestController
public class WechatAppController {

	private Logger logger = LoggerFactory.getLogger(WechatAppController.class);
	
	@Resource
	private Environment env;

	@Resource
	private IWechatApiService wechatApiService;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping(value = "/wechat/app/jscode2session", method = RequestMethod.GET)
	String jscode2session(@RequestParam("kindOf") String kindOf,
			@RequestParam("js_code") String js_code){
		String app_id = env.getProperty(kindOf + ".appid"), secret = env
				.getProperty(kindOf + ".secret");
		String uri = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";
		String json = restTemplate.getForObject(uri, String.class, app_id, secret, js_code);
		logger.debug("json={}",json);
		JSONObject result = (JSONObject) JSONObject.parse(json);
		if (result.containsKey("errcode")) {
			throw new I18nMessageException("40001","error|"+result.getString("errorcode")+"="+result.getString("errmsg"));
		}
		return result.getString("openid");
	}
	
}
