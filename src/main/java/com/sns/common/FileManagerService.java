package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	
	// TODO : FileManagerService 완료 및 MVC 모델 설정
	public static final String FILE_UPLOAD_PATH = "D:\\KimGeonHo\\6_springProject\\sns\\SNS_workspace\\images/";
	
	public String uploadFile(MultipartFile file, String loginId) {
		String directoryName = loginId + "_" + System.currentTimeMillis();
		String filePath = FILE_UPLOAD_PATH + directoryName + "/";
		
		File directiory = new File(filePath);
		if(directiory.mkdir() == false) {
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return "/images/" + directoryName + "/" + file.getOriginalFilename();
	}
}
