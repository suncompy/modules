package com.lebaoxun.upload.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.upload.service.hystrix.QrcodeUploadServiceServiceHystrix;


@FeignClient(value="upload-service",fallback=QrcodeUploadServiceServiceHystrix.class)
public interface IQrcodeUploadService {
	
	@RequestMapping(value="/qrcode/create",method=RequestMethod.POST)
	Map<String,String> createAndUpload(@RequestParam("mode") String mode, 
			@RequestParam("namespace") String namespace,
			@RequestParam("content") String content);
}
