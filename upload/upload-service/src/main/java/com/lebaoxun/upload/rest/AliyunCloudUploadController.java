package com.lebaoxun.upload.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.StringUtils;
import com.lebaoxun.upload.core.IAuditImgService;
import com.lebaoxun.upload.core.IUploadService;
import com.lebaoxun.upload.utils.Base64Util;

@RestController
public class AliyunCloudUploadController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name="aliyunCloudUploadService")
	private IUploadService uploadService;
	
	@Resource
	private IAuditImgService baiduAuditImgService;
	
	@RequestMapping(value="/upload/aliyuncloud/img",method=RequestMethod.POST)
	ResponseMessage uploadImg(@RequestParam("namespace") String namespace, 
			@RequestParam("fileType") String fileType,
			@RequestParam(value="check",required=false) Boolean check,
			@RequestBody String imgStr){
		String file = null;
		try{
			file = uploadService.uploadImg(namespace, imgStr, fileType);
			
			byte[] bdtmp = uploadService.readFileByBytes(file);
			String imgStrForBaidu = Base64Util.encode(bdtmp);
			boolean audit = true;
			if(check != null && check){
				audit = baiduAuditImgService.userDefinedImage(imgStrForBaidu);
			}
			logger.debug("uploadImg|audit={}",audit);
			if(audit){
				Map<String,String> request = new HashMap<String,String>();
				request.put("uri", file);
				return new ResponseMessage(request);
			}
		}catch(Exception e){
			logger.error("uploadImg|error={}",e.fillInStackTrace());
			if(file != null){
				uploadService.deleteFile(file);
            }
			if(e instanceof I18nMessageException){
				throw (I18nMessageException)e;
			}
		}
		throw new I18nMessageException("-1","图片上传失败！");
	}
	
	@RequestMapping(value="/upload/aliyuncloud/file",method=RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseMessage upload(@RequestParam("namespace") String namespace,
			HttpServletRequest request){
		List<String> file = null;
		try{
			Map<String, MultipartFile> multipartFiles = ((MultipartHttpServletRequest) request).getFileMap();
			
			file = uploadService.upload(namespace, multipartFiles.values().toArray(new MultipartFile[]{}));
			Map<String,String> repsonse = new HashMap<String,String>();
			repsonse.put("uri", file.get(0));
			return new ResponseMessage(repsonse);
		}catch(Exception e){
			logger.error("uploadImg|error={}",e.fillInStackTrace());
			if(file != null){
				for(String f : file)
				uploadService.deleteFile(f);
            }
			if(e instanceof I18nMessageException){
				throw (I18nMessageException)e;
			}
		}
		throw new I18nMessageException("-1","图片上传失败！");
	}
	
	@RequestMapping(value="/upload/aliyuncloud/file/delete",method=RequestMethod.GET)
	ResponseMessage deleteFile(@RequestParam("file") String file){
		if(StringUtils.isNotBlank(file)){
			logger.debug("delete|uri={}",file);
			return new ResponseMessage(uploadService.deleteFile(file));
		}
		throw new I18nMessageException("-1","文件删除失败！");
	}
}
