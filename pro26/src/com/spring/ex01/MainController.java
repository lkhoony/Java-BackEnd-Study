package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("mainController")
// @Controller 애너테이션을 이용해 MainController클래스를 빈으로 자동변환
// 빈 id는 mainController

@RequestMapping("test")
// RequestMapping을 이용해 첫 번째 단계의 URL요청이 /test이면 mainController빈을 요청

public class MainController {

	@RequestMapping(value = "/main1.do",method = RequestMethod.GET)	
	public ModelAndView main1(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "main1");
		mav.setViewName("main");
		return mav;
	
	}

	@RequestMapping(value = "/main2.do",method = RequestMethod.GET)	
	public ModelAndView main2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "main2");
		mav.setViewName("main");
		return mav;
	
	}
}
