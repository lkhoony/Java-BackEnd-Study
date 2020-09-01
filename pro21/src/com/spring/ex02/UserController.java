package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController{
	
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userId = "";
		String userPw = "";
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");
		userId = request.getParameter("userId");
		userPw = request.getParameter("userPw");
		mav.addObject("userId", userId);
		mav.addObject("userPw", userPw);
//		mav.setViewName("result");
		mav.setViewName(viewName);
		return mav;
	}
	
	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{

		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		mav.addObject("id", id);
		mav.addObject("pwd", pwd);
		mav.addObject("name", name);
		mav.addObject("email", email);
		
		mav.setViewName("memberInfo");
		
		return mav;
	}
	
	public String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		System.out.println("contextPath : " + contextPath);
		
		String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
		System.out.println("uri : "  + uri);
		
		if(uri==null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}
		System.out.println("uri : "  + uri);
		
		int begin = 0;
		if(!((contextPath==null)||("".equals(contextPath)))) {
			begin = contextPath.length();
		}
		
		int end;
		if(uri.indexOf(";")!=-1) {
			end = uri.indexOf(";");
		}else if(uri.indexOf("?")!=-1) {
			end = uri.indexOf("?");
		}else{
			end = uri.length();
		}
		
		String fileName = uri.substring(begin,end);
		System.out.println("fileName : " + fileName);
		
		if(fileName.indexOf(".")!=-1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if(fileName.lastIndexOf("/")!=-1) {
			fileName = fileName.substring(fileName.lastIndexOf("/")+1, fileName.length());
		}
		System.out.println("fileName : " + fileName);
		
		return fileName;
	}
}
