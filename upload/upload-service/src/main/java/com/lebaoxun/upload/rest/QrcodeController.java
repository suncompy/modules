package com.lebaoxun.upload.rest;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.upload.core.IUploadService;
import com.lebaoxun.upload.utils.EncodeUtil;
import com.lebaoxun.upload.web.LocalMultipartFile;

@RestController
public class QrcodeController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name="localUploadService")
	private IUploadService localUploadService;
	
	@Resource(name="aliyunCloudUploadService")
	private IUploadService uploadService;
	
	@RequestMapping(value="/qrcode/create")
	Map<String,String> createAndUpload(@RequestParam("mode") String mode, 
			@RequestParam("namespace") String namespace,
			@RequestParam("content") String content){
		String imgFileTempPath = "",
				uri = "";
		logger.debug("mode={},namespace={},content={}",mode,namespace,content);
		File file = null;
		try{
			//1、生成二维码到temp文件，拿到绝对路径
			imgFileTempPath = EncodeUtil.YUAN_ERCODE(content);
			file = new File(imgFileTempPath);
			FileInputStream inputStream = new FileInputStream(file);
			
			MultipartFile mf = new LocalMultipartFile(file.getName(), inputStream);
	        MultipartFile[] multipartFiles = new MultipartFile[]{mf};
	        logger.debug("file.getName={},mf.getOriginalFilename={}",file.getName(),mf.getOriginalFilename());
			if("local".equals(mode)){
				uri = uploadService.upload(namespace, multipartFiles).get(0);
			}
			if("aliyunCloud".equals(mode)){
				uri = uploadService.upload(namespace, multipartFiles).get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new I18nMessageException("-1","二维码生成失败！");
		}finally{
			//最后删除临时文件
			if(file != null){
				if(file.exists() && file.isFile()){
					logger.debug("file.delete={}",file.delete());
				}
			}
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("uri", uri);
		return map;
	}
	
}
