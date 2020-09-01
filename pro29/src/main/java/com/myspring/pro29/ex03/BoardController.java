package com.myspring.pro29.ex03;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// RestController 어노테이션을 사용하여 브라우저에 데이터를 보낼 수 있게 함
@RestController
@RequestMapping("/boards")
public class BoardController {
	
	static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// # GET HTTP method : 데이터를 조회 
	// 전체 글 목록을 조회
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<ArticleVO>> listArticles(){
		
		logger.info("listArticles 메서드 호출");
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		for(int i=0; i<10;i++) {
			ArticleVO vo = new ArticleVO();
			vo.setArticleNO(i);
			vo.setContent("이순신"+i);
			vo.setTitle("안녕하세요"+i);
			vo.setWriter("새 상품을 소개합니다."+i);
			list.add(vo);
		}
		// ArticleVO의 List를 전달, HTTP상태 코드 설정
		return new ResponseEntity<List<ArticleVO>>(list, HttpStatus.OK);
		
	}
	
	// # GET HTTP method : 데이터를 조회 ( articleNO로 전달되는 매개변수를 가져와서 조회) 
	@RequestMapping(value="/{articleNO}", method = RequestMethod.GET)
	public ResponseEntity<ArticleVO> findArticle(
			@PathVariable("articleNO") Integer articleNO) {
		logger.info("findArticle 호출");
		ArticleVO vo = new ArticleVO();
		vo.setArticleNO(articleNO);
		vo.setWriter("홍길동");
		vo.setTitle("안녕하세요");
		vo.setContent("홍길동 글입니다.");
		return new ResponseEntity<ArticleVO>(vo, HttpStatus.OK);
	}
	
	// # POST HTTP method : 데이터를 추가 >> 요청 메세지에서 데이터를 받아야 함( @RequestBody를 이용하여 전달된 JSON객체를 articleVO에 저장)
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<String> addArticle(@RequestBody ArticleVO articleVO) {
		ResponseEntity<String> resEntity = null;
		try {
			logger.info("addArticle 메소드 호출");
			logger.info(articleVO.toString());
			resEntity = new ResponseEntity<String>("ADD_SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
		
		return resEntity;
	}
	
	// # PUT HTTP method : 데이터를 수정(articleNO로 전달되는 글을 수정)
	@RequestMapping(value="/{articleNO}", method = RequestMethod.PUT)
	public ResponseEntity<String> modArticle(@PathVariable("articleNO") Integer articleNO, 
			@RequestBody ArticleVO articleVO) {
		
		ResponseEntity<String> resEntity = null;
		try {
			logger.info("modArticle 메소드 호출");
			logger.info(articleVO.toString());
			resEntity = new ResponseEntity<String>("MOD_SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
		
		return resEntity;
	}
	
	// # DELETE HTTP method : 데이터를 삭제(articleNO로 전달되는 글을 삭제)
	@RequestMapping(value="/{articleNO}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removerArticle(@PathVariable("articleNO") Integer articleNO) {
		
		ResponseEntity<String> resEntity = null;
		try {
			logger.info("removeArticle 메소드 호출");
			logger.info(Integer.toString(articleNO));
			resEntity = new ResponseEntity<String>("REMOVE_SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
		
		return resEntity;
	}
	
	
	
	
}
