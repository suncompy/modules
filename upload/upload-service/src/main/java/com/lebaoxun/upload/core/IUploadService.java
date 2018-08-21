package com.lebaoxun.upload.core;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
	
	byte[] readFileByBytes(String filePath);
	
	String uploadImg(String namespace, String imgStr, String fileType);
	
	List<String> upload(String namespace,MultipartFile[] files);
	
	boolean deleteFile(String file);
	
}
