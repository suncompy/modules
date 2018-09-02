package com.lebaoxun.upload.core.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.upload.core.IUploadService;

@Service("aliyunCloudUploadService")
public class AliyunCloudUploadServiceImpl implements IUploadService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
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
	
	@Value("${application.web.baseDir:/www/upload}")
	private String baseDir;
	
	@Bean("ossClient")
	OSSClient ossClient(){
		logger.debug("endPoint={},acessKeyId={},accessKeySecret={},domain={},baseDir={}",endPoint,acessKeyId,accessKeySecret,domain,baseDir);
		return new OSSClient(endPoint, acessKeyId,
        		accessKeySecret);
	}
	
	@Override
	public byte[] readFileByBytes(String filePath) {
		// TODO Auto-generated method stub
		OSSClient ossClient = ossClient();
		try{
			InputStream in = null;
			ByteArrayOutputStream bos = null;
			try {
				ObjectMetadata metadata = ossClient.getObjectMetadata(bucketName, filePath);
				OSSObject obj = ossClient.getObject(new GetObjectRequest(bucketName, filePath));
				in = obj.getObjectContent();
				bos = new ByteArrayOutputStream((int)metadata.getContentLength());
				short bufSize = 1024;
				byte[] buffer = new byte[bufSize];
				int len1;
				while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
					bos.write(buffer, 0, len1);
				}
				byte[] var7 = bos.toByteArray();
				return var7;
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (IOException var14) {
					var14.printStackTrace();
				}
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new I18nMessageException("500");
		} 
	}

	@Override
	public String uploadImg(String namespace, String imgStr, String fileType) {
		OSSClient ossClient = ossClient();
		String imgFileTempPath = "";
		File file2 = null;
		try {
			String base64String = imgStr.substring(imgStr.indexOf(",")+1);
			// Base64解码
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(base64String);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			File file = new File(baseDir);
			if(!file.isDirectory()){
				file.mkdirs();
			}
			
			file2 = new File(baseDir+ "/" + namespace + "/");
			if(!file2.isDirectory()){
				file2.mkdirs();
			}
			
			// 生成jpeg图片
			String fileName = System.currentTimeMillis() + "." + fileType;
			imgFileTempPath = namespace +"/"+ fileName;//新生成的图片
			
			InputStream in = new FileInputStream(file2);
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(in.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(fileType));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			// 上传文件
			PutObjectResult putResult = ossClient.putObject(bucketName, imgFileTempPath, in, objectMetadata);
			logger.debug("eTag={}",putResult.getETag());
		} catch (IOException e) {
			e.printStackTrace();
			throw new I18nMessageException("-1","上传文件失败，请检查配置信息", e);
		}finally{
			if(file2 != null)
				file2.delete();
		}
        return domain +  "/" + imgFileTempPath;
	}

	@Override
	public boolean deleteFile(String file) {
		// TODO Auto-generated method stub
		OSSClient ossClient = ossClient();
		List<String> keys = new ArrayList<String>();
		keys.add(file);
		DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(
                new DeleteObjectsRequest(bucketName).withKeys(keys));
		return deleteObjectsResult.getDeletedObjects().get(0).equals(file);
	}

	@Override
	public List<String> upload(String namespace, MultipartFile[] files) {
		OSSClient ossClient = ossClient();
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		if(files != null){
			// 取得上传文件
			for(MultipartFile file : files){
                if (file != null) {
                	File newFile = null;
                	try{
                		// 取得当前上传文件的文件名称
                		String fileName = file.getOriginalFilename();
                		String fileType = fileName.substring(fileName.lastIndexOf("."));
                		// 如果名称不为空,说明该文件存在，否则说明该文件不存在
                		if (fileName.trim() != "") {
                			newFile = new File(fileName);
                			FileOutputStream outStream = new FileOutputStream(newFile); // 文件输出流用于将数据写入文件
                			outStream.write(file.getBytes());
                			outStream.close(); // 关闭文件输出流
                			file.transferTo(newFile);
                			// 生成文件
                			String uri = namespace +"/"+ System.currentTimeMillis() + fileType;
                			// 上传到阿里云
                			
                			InputStream in = new FileInputStream(newFile);
                			ObjectMetadata objectMetadata = new ObjectMetadata();
                			objectMetadata.setContentLength(in.available());
                			objectMetadata.setCacheControl("no-cache");
                			objectMetadata.setHeader("Pragma", "no-cache");
                			objectMetadata.setContentType(getcontentType(fileType));
                			objectMetadata.setContentDisposition("inline;filename=" + fileName);
                			// 上传文件
                			PutObjectResult putResult = ossClient.putObject(bucketName, uri, in, objectMetadata);
                			logger.debug("eTag={}",putResult.getETag());
                			list.add(domain + "/" + uri);
                		}
                	} catch (IOException e) {
            			e.printStackTrace();
            			throw new I18nMessageException("-1","上传文件失败，请检查配置信息", e);
            		}finally{
            			if(newFile != null)
            				newFile.delete();
            		}
                }

			}
		}
		return list;
	}
	
	/**
	 * Description: 判断OSS服务文件上传时文件的contentType
	 *
	 * @param FilenameExtension
	 *            文件后缀
	 * @return String
	 */
	public static String getcontentType(String filenameExtension) {
		if (filenameExtension.equalsIgnoreCase("bmp")) {
			return "image/bmp";
		}
		if (filenameExtension.equalsIgnoreCase("gif")) {
			return "image/gif";
		}
		if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
				|| filenameExtension.equalsIgnoreCase("png")) {
			return "image/jpeg";
		}
		if (filenameExtension.equalsIgnoreCase("html")) {
			return "text/html";
		}
		if (filenameExtension.equalsIgnoreCase("txt")) {
			return "text/plain";
		}
		if (filenameExtension.equalsIgnoreCase("vsd")) {
			return "application/vnd.visio";
		}
		if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
			return "application/msword";
		}
		if (filenameExtension.equalsIgnoreCase("xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}
	
	public static void main(String[] args) {
		String endPoint = "http://oss-cn-beijing.aliyuncs.com",acessKeyId="LTAIqYlxwrBmuOgm",
				accessKeySecret = "miE4TbBA5s3x7Y0s5Vt4RXXQMkJQcr",
						bucketName = "dechenginfo",
						domain = "http://oss.dechengpingtai.com";
		OSSClient ossClient = new OSSClient(endPoint, acessKeyId,
        		accessKeySecret);
		String fileName = "/Users/mac/Documents/workspace/config-repo/game-dev.properties";
		File file = null;
    	try{
    		// 取得当前上传文件的文件名称
    		// 如果名称不为空,说明该文件存在，否则说明该文件不存在
    			file = new File(fileName);
    			String fileType = fileName.substring(fileName.lastIndexOf("."));
    			// 生成文件
    			String uri = System.currentTimeMillis() + fileType;
    			// 上传到阿里云
    			
    			InputStream in = new FileInputStream(file);
    			ObjectMetadata objectMetadata = new ObjectMetadata();
    			objectMetadata.setContentLength(in.available());
    			objectMetadata.setCacheControl("no-cache");
    			objectMetadata.setHeader("Pragma", "no-cache");
    			objectMetadata.setContentType(getcontentType(fileType));
    			objectMetadata.setContentDisposition("inline;filename=" + fileName);
    			// 上传文件
    			PutObjectResult putResult = ossClient.putObject(bucketName, uri, in, objectMetadata);
    			System.out.println("eTag="+putResult.getETag());
    	} catch (IOException e) {
			e.printStackTrace();
			throw new I18nMessageException("-1","上传文件失败，请检查配置信息", e);
		}finally{
		}
	}

}
