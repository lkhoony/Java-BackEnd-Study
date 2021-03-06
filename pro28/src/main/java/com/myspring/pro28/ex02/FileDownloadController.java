package com.myspring.pro28.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownloadController {
	
	private static String CURR_IMAGE_REPO_PATH= "c:\\spring\\image_repo";
	
	@RequestMapping("/download")
	protected void download(@RequestParam("imageFileName") String imageFileName, HttpServletResponse response) throws Exception	{
		
		OutputStream out = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File image = new File(filePath);
		int lastIndex = imageFileName.lastIndexOf(".");
		String fileName = imageFileName.substring(0,lastIndex);
		
//		---------------------------------------------------------------------------------------
		
//		# 1. thumbnail 이미지를 따로 저장하기
		
//		File thumbnail = new File(CURR_IMAGE_REPO_PATH + "\\thumbnail\\" + fileName +".png" );
//		if(image.exists()) {
//			thumbnail.getParentFile().mkdir();
//			Thumbnails.of(image).size(50, 50).outputFormat("png").toFile(thumbnail);
//		}
//		
//		FileInputStream fis = new FileInputStream(thumbnail);
//		byte[] buffer = new byte[1024*8];
//		
//		while(true) {
//			int count = fis.read(buffer);
//			if(count==-1) break;
//			out.write(buffer,0,count);
//		}
//		
//		
//		fis.close();
		
//		----------------------------------------------------------------------------------------
//		
//		# 2. output stream으로 바로 출력하기
		
		if(image.exists()) {
			Thumbnails.of(image).size(50, 50).outputFormat("png").toOutputStream(out);
		}else {
			return;
		}
		
//		byte[] buffer = new byte[1024*8];
//		out.write(buffer);
		
		out.close();
		
	}
	
}
