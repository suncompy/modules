package com.lebaoxun.upload.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.upload.service.hystrix.AliyunCloudUploadServiceServiceHystrix;

@FeignClient(value = "upload-service", fallback = AliyunCloudUploadServiceServiceHystrix.class)
public interface IAliyunCloudUploadService {

	@RequestMapping(value = "/upload/aliyuncloud/img", method = RequestMethod.POST)
	ResponseMessage uploadImg(@RequestParam("namespace") String namespace,
			@RequestParam("fileType") String fileType,
			@RequestParam(value = "check", required = false) Boolean check,
			@RequestBody String imgStr);

	@RequestMapping(value = "/upload/aliyuncloud/file", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseMessage upload(@RequestParam("namespace") String namespace,
			@RequestPart("file") MultipartFile multipartFile);

	@RequestMapping(value = "/upload/aliyuncloud/file/delete", method = RequestMethod.GET)
	ResponseMessage deleteFile(@RequestParam("file") String file);
}
