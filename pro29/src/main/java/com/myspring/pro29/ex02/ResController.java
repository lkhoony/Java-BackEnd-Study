package com.myspring.pro29.ex02;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// Controller 어노테이션 사용 >> view를 전송해야함
@Controller
public class ResController {
	
	// 하지만 ResponseBody 어노테이션 사용하여 데이터로 전송
	@RequestMapping(value="/res1")
	@ResponseBody
	public Map<String, Object> res1() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id","hong");
		map.put("name","홍길동");
		return map;
	}
	
	// ResponseBody 어노테이션이 없기때문에 view 전송
	@RequestMapping(value="/res2")
	public ModelAndView res2() {
		return new ModelAndView("home");
	}
}
