package com.lebaoxun.modules.pay.service.hystrix;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.pay.pojo.AlipayConfig;
import com.lebaoxun.modules.pay.service.IAlipayService;

@Component
public class AlipayServiceHystrix implements IAlipayService {

	@Override
	public List<AlipayConfig> configList() {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage configInfo(String account) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage configSave(AlipayConfig config) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage configDelete(String account) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage payment(String group, String outTradeNo,
			String subject, String totalAmount, String body,
			String spbill_create_ip, Long userId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public String notify(Map<String, String> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

}
