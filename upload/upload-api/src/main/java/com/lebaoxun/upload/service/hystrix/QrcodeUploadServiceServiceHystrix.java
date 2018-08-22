package com.lebaoxun.upload.service.hystrix;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.upload.service.IQrcodeUploadService;

@Component
public class QrcodeUploadServiceServiceHystrix implements IQrcodeUploadService {

	@Override
	public Map<String, String> createAndUpload(String mode, String namespace,
			String content) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
}
