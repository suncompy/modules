package com.lebaoxun.upload.service.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.upload.service.IAliyunCloudUploadService;

@Component
public class AliyunCloudUploadServiceServiceHystrix implements IAliyunCloudUploadService {
	
	@Override
	public ResponseMessage uploadImg(String namespace, String fileType,
			Boolean check, String imgStr) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage upload(String namespace, MultipartFile[] files) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage deleteFile(String file) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
}
