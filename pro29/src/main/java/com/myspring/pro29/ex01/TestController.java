package com.myspring.pro29.ex01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	// 브라우저에서 보낸 데이터를 MemberVO로 받아서 출력
	@RequestMapping(value="/info", method=RequestMethod.POST)
	public void modify(@RequestBody MemberVO vo){
		logger.info(vo.toString());
	}
	
	// ResponseEntity 객체 : 예외에 대한 세밀한 제어가 필요할 때 사용
	// ResponseEntity 클래스에 HTTP 상태코드를 설정하여 전송
	// MemberVO의 List를 Response body에 담아 전달하고 상태코드로 응답
	@RequestMapping("/membersList2")
	public ResponseEntity<List<MemberVO>> listMembers2() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		for(int i=0;i<10;i++) {
			MemberVO vo = new MemberVO();
			vo.setId("lee"+i);
			vo.setPwd("123"+i);
			vo.setName("이순신"+i);
			vo.setEmail("lee"+i+"test.com");
			list.add(vo);
		}
		return new ResponseEntity<List<MemberVO>>(list,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value="/res3")
	public ResponseEntity res3() {
		// HttpHeaders 객체에 Content-Type(전송할 데이터 종류), 인코딩을 설정
		// 이 객체를 ResponseEntity에 담아 전송
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		String message = "<script>"
				+ "alert('새 회원을 등록합니다.');"
				+ "location.href='/pro29/test/membersList2';"
				+ "</script>";
		return new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
	}
	
}
	
