package com.project.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
//	private static Map<String, MediaType> mediaMap;
//	static {
//		mediaMap = new HashMap<String, MediaType>();
//		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
//		mediaMap.put("GIF", MediaType.IMAGE_GIF);
//		mediaMap.put("PNG", MediaType.IMAGE_PNG);
//	}
//	
//	public static MediaType getMediaType(String ext) {
//		return mediaMap.get(ext.toUpperCase());
//	}
	
	public static String uploadFile(MultipartFile file,String uploadPath) throws Exception {
		String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		String dirname = getCurrentUploadPath(uploadPath);
		File target = new File(dirname, filename);
		FileCopyUtils.copy(file.getBytes(), target);
		
		
//		mkThumbnail(dirname,filename);
		
		return filename;
	}
	
//	private static void mkThumbnail(String dirname, String filename) throws IOException {
//		BufferedImage srcImg = ImageIO.read(new File(dirname, filename));
//		BufferedImage destImg = Scalr.resize(srcImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
//		String thumbnailName = dirname + File.separator + "s_" + filename;
//		File newFile = new File(thumbnailName);
//		String ext = getFileExtension(filename);
//		ImageIO.write(destImg, ext.toUpperCase(), newFile);
//	}

	public static String getFileExtension(String filename) {
		return filename.substring(filename.lastIndexOf(".")+1);
	}

	public static String getCurrentUploadPath(String uploadRootPath) {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) +1 ;
		int d = cal.get(Calendar.DATE);
		
		return makeDir(uploadRootPath,"" + y , len2(m), len2(d));
	}
	
	private static String makeDir(String uploadRootPath, String... paths) {
		for(String path:paths) {
			uploadRootPath += File.separator + path;
			File tmpFile = new File(path);
			if(tmpFile.exists())
				continue;
			else
				tmpFile.mkdir();
		}
		
		return uploadRootPath;
	}

	private static String len2(int n) {
		return new DecimalFormat("00").format(n).toString();
	}
}
