package com.lebaoxun.wechat.service.hystrix;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.wechat.service.IWechatAppService;

@Component
public class WechatAppServiceHystrix implements IWechatAppService {

	@Override
	public Map<String,Object> jscode2session(String kindOf, String js_code) {
		// TODO Auto-generated method stub
		return null;
	}

}
