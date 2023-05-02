package com.project.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DownloadController {
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void downloadFile(HttpServletRequest request, HttpServletResponse response,
			 @RequestParam("fileName") String fileName) throws IOException {

		// 파일 경로와 파일명을 이용하여 File 객체 생성
		File file = new File(uploadPath, fileName);

		// 다운로드할 파일이 존재하지 않으면 404 에러 반환
		if (!file.exists()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 다운로드할 파일의 MIME 타입 지정
		response.setContentType("application/octet-stream");

		// 다운로드할 파일의 크기 지정
		response.setContentLength((int) file.length());

		// 다운로드할 파일의 이름을 Content-Disposition 헤더를 이용하여 설정
		String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
		response.setHeader("Content-Disposition", headerValue);

		// 다운로드할 파일을 읽어와서 응답으로 보내줌
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		OutputStream outputStream = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outputStream.flush();
		outputStream.close();
	}

}
