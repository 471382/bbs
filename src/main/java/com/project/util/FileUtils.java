package com.project.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {


	// 파일 업로드
	public static String uploadFile(MultipartFile file, String uploadPath) throws Exception {
		String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		File target = new File(uploadPath, filename);
		FileCopyUtils.copy(file.getBytes(), target);

		return filename;
	}
	// 확장자 찾기
	
	public static String getFileExtension(String filename) {
		return filename.substring(filename.lastIndexOf(".") + 1);
	}
}
