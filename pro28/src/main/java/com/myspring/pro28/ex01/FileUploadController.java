package com.myspring.pro28.ex01;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	
	private static String CURR_IMAGE_REPO_PATH = "c:\\spring\\image_repo";
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@RequestMapping(value="/form")
	public String form() {
		return "uploadForm";
	}
	
	@RequestMapping(value="/upload")
	public ModelAndView upload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception{
		
		multipartRequest.setCharacterEncoding("utf-8");
		Map map = new HashMap();
		Enumeration enu = multipartRequest.getParameterNames();
		
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = multipartRequest.getParameter(name);
			map.put(name, value);
			// file은 getFileNames로 처리해줘야함
			logger.info("name : " + name + " value : " + value);
		}
		
		List fileList = fileProcess(multipartRequest);
		map.put("fileList", fileList);
		ModelAndView mav = new ModelAndView();
		mav.addObject("map",map);
		mav.setViewName("result");
		return mav;
		
	}
	
	private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception{
		
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		while(fileNames.hasNext()) {
			
//			# 파일의 내용이 다르지만 이름이 같을 경우 데이터 손실의 위험이 있음
//			# uuid 객체를 이용하여 originalFileName앞의 랜덤 String을 붙여줌
			
			UUID uuid = UUID.randomUUID();
			
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			
//			# fileName과 originalFileName의 차이
//			# fileName : form에서 전송될 때 지정된 name의 이름 (예 : input type="file" name="fileName" )
//			# originalFileName : 실제 파일의 이름 ( 예 : test.jpg )
			
			String savedName = uuid.toString() + "_" + originalFileName;
			
			fileList.add(savedName);
			
			File file = new File(CURR_IMAGE_REPO_PATH + "\\" + savedName);
			

			if(mFile.getSize()!=0) {
//				# file의 부모 디렉토리가 존재하지 않으면 생성함
				if(!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				mFile.transferTo(file);
			}
			
			
//			# 책 내용
			
//			File file = new File(CURR_IMAGE_REPO_PATH + "\\" + fileName);
//			File file = new File(CURR_IMAGE_REPO_PATH + "\\" + originalFileName);	
//			if(mFile.getSize()!=0) {
//				
//				//file이 존재하지 않으면 실행
//				if(! file.exists()) {
//					if(file.getParentFile().mkdirs()) {
//						file.createNewFile();
//					}
//				}
//				
//				
//				# 파일 이름 중복되지 않게 하려면 파일 이름 앞에 랜덤변수를 입력하는 방법이 있음
//				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH+"\\"+originalFileName));
//				logger.info("fileName : " + fileName);
//				logger.info("originalFileName : " + originalFileName);
//				
//			}
		}
		
		return fileList;
	}
}
