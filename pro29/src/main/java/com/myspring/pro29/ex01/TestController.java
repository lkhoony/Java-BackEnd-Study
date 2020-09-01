package com.myspring.pro29.ex01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// # RestController 어노테이션을 사용하여 브라우저로 데이터 전송
// 
@RestController
@RequestMapping("/test/*")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/hello") 
	public String hello() {
		return "Hello REST!";
		// String return : 브라우저로 문자열 전송
		// Response Content Type : text/html
	}
	
	@RequestMapping("/member")
	public MemberVO member() {
		
		MemberVO vo = new MemberVO();
		vo.setId("hong");
		vo.setPwd("1234");
		vo.setName("홍길동");
		vo.setEmail("hong@test.com");
		
		return vo;
		// return vo객체 : 브라우저로 json데이터 전송
		// response content type : application/json;charset=UTF-8
	}
	
	@RequestMapping("/membersList")
	public List<MemberVO> listMember() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		for(int i=0;i<10;i++) {
			MemberVO vo = new MemberVO();
			vo.setId("hong"+i);
			vo.setPwd("123"+i);
			vo.setName("홍길동"+i);
			vo.setEmail("hong"+i+"@test.com");
			list.add(vo);
		}
		
		// return ArrayList객체 : Object의 배열로 전송
		return list;
	}
	
	@RequestMapping("/membersMap")
	public Map<Integer, MemberVO> membersMap() {
		Map<Integer, MemberVO> map = new HashMap<Integer, MemberVO>();
		for(int i=0;i<10;i++) {
			MemberVO vo = new MemberVO();
			vo.setId("hong"+i);
			vo.setPwd("123"+i);
			vo.setName("홍길동"+i);
			vo.setEmail("hong"+i+"@test.com");
			map.put(i,vo);
		}
		
		// return HashMap객체 : key를 JSON객체의 속성으로 전달
		return map;
	}
	
	// {num}부분의 값이 @PathVariable로 지정됨
	@RequestMapping(value="/notice/{num}", method=RequestMethod.GET)
	public int notice(@PathVariable("num") int num) throws Exception {
		return num;
	}
	
	@RequestMapping(value="/info", method=RequestMethod.POST)
	public void modify(@RequestBody MemberVO vo){
		logger.info(vo.toString());
	}
}
	
