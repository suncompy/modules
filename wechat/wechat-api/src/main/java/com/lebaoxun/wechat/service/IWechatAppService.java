package com.lebaoxun.wechat.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.wechat.service.hystrix.WechatAppServiceHystrix;

@FeignClient(value="wechat-service",fallback=WechatAppServiceHystrix.class)
public interface IWechatAppService {
	
	@RequestMapping(value = "/wechat/app/jscode2session", method = RequestMethod.GET)
	String jscode2session(@RequestParam("kindOf") String kindOf,
			@RequestParam("js_code") String js_code);
}
