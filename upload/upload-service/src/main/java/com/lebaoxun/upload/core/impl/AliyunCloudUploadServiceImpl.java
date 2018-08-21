package com.lebaoxun.upload.core.impl;

import java.io.File;
import java.io.FileNotFoundException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.aliyun.oss.OSSClient;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.upload.core.IUploadService;

@Service("aliyunCloudUploadService")
public class AliyunCloudUploadServiceImpl implements IUploadService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name="ossClient")
	private OSSClient ossClient;
	
	@Value("${application.aliyuncloud.bucketName}")
	private String bucketName;
	
	@Value("${application.aliyuncloud.endPoint}")
	private String endPoint;
	@Value("${application.aliyuncloud.acessKeyId}")
	private String acessKeyId;
	@Value("${application.aliyuncloud.accessKeySecret}")
	private String accessKeySecret;
	
	@Value("${application.aliyuncloud.domain}")
	private String domain;
	
	@Bean("ossClient")
	OSSClient ossClient(){
		return new OSSClient(endPoint, acessKeyId,
        		accessKeySecret);
	}
	
	@Override
	public byte[] readFileByBytes(String filePath) {
		/*// TODO Auto-generated method stub
		String uri = filePath;
		if(filePath.startsWith("http://")){
			uri = filePath.substring(filePath.indexOf("/","http://".length()),filePath.length());
		}
		logger.debug("readFileByBytes|uri={}",uri);
		File file = new File(baseDir+uri);
		if (!file.exists()) {
			throw new FileNotFoundException(filePath);
		} else {
			
		}*/
		return null;
	}

	@Override
	public String uploadImg(String namespace, String imgStr, String fileType) {
		// TODO Auto-generated method stub
		try {
			ossClient.putObject(bucketName, namespace, inputStream);
        } catch (Exception e){
            throw new I18nMessageException("-1","上传文件失败，请检查配置信息", e);
        }
        return domain + "/" + namespace;
	}

	@Override
	public boolean deleteFile(String file) {
		// TODO Auto-generated method stub
		return false;
	}

}
