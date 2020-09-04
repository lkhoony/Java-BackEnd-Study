# Spring REST API 사용하기
## 개요
- 스프링에서도 모바일 기기와 연동하는 경우가 많아지면서 화면 정보(html, css, javascript)도 일일이 전송하지 않고 필요한 데이터만 받아 빠르게 결과를 표시하는 기능을 지원하게 되었고 그 결과 REST라는 방식이 그 대안으로 등장하여 사용되고 있다.
## 1. REST(Representational State Transfer)란?
* 하나의 URI가 고유한 리소스를 처리하는 공통방식
``` 
 /board/112 : 게시글 중 112번째 글을 요청 
```
* 전송 방식을 나타내는 method속성의 값에 따라 리소스에 대한 추가 작업을 요청
	> REST Method의 종류

	메서드 | 설명 | 
	---- | ---- 
	POST | 추가(Create)
	GET | 조회(Select)
	PUT | 수정(Update)
	DELETE | 삭제(Delete)

* REST 방식으로 제공되는 API를 REST API라고 하며 여러 Open API에서 사용되고 있음

## 2. @RestController 이용해 REST기능 구현하기
- 스프링3 버전에서는 @ResponseBody 애너테이션을 지원하면서 REST 방식의 데이터 처리를 지원했지만, 스프링4 버전에서는 @RestController 애너테이션을 이용해 REST방식의 데이터를 처리

	> @RestController는 JSP같은 뷰를 반환하는 컨트롤러가 아니라 JSON, XML같은 데이터를 브라우저로 전송해주는 컨트롤러
	
	### (1) @RestController 이용한 문자열 전달
	> 문자열을 리턴하는 메소드를 사용하면 브라우저에 문자열로 전송됨
	```
	@RestController
	@RequestMapping("/test/*")
	public class TestController {

		@RequestMapping("/hello") 
		public String hello() {

			return "Hello REST!";
		
	}
	```
	### (2) @RestController 이용한 VO객체 전달
	> VO객체를 전달하면 브라우저로 JSON데이터를 전송함
	```
	@RestController
	@RequestMapping("/test/*")
	public class TestController {

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
	}
	```
	### (3) @RestController 이용한 컬렉션 객체 전달
	> VO객체를 저장하는 컬렉션 객체를 리턴하는 메소드를 사용하면 브라우저에 JSON객체의 배열로 전송
	
	```
	@RestController
	@RequestMapping("/test/*")
	public class TestController {

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
			// Map 객체를 return 할 경우 Map 객체에 저장되어 있는 key를 JSON객체의 속성으로 
			return list;
		}
	}
	```

## 3. @PathVariable 사용하여 전달된 매개변수 가져오기
- @PathVariable 어노테이션을 사용하여 요청 URL로 전달된 매개변수를 가져올 수 있음
	```
	// {num}부분의 값이 @PathVariable로 지정됨
	@RequestMapping(value="/notice/{num}", method=RequestMethod.GET)
	public int notice(@PathVariable("num") int num) throws Exception {
		return num;
	}
	```
	
## 4. @RequestBody와 @ResponseBody 사용하기
- @RequestBody 어노테이션을 이용하여 Ajax등을 이용하여 브라우저에서 전송된 JSON데이터를 VO객체로 변환하고 @ResponseBody 어노테이션을 이용하여 @RestController가 아닌 
@Controller로 적용된 컨트롤러에서 JSP가 아닌 JSON으로 결과를 전송

	### (1) @RequestBody
	> 전송된 요청에서 JSON 데이터를 VO객체로 자동 변환
	
	##### 브라우저에서 JSON 데이터 Ajax이용하여 전송
	```
	<script type="text/javascript">
	$(function(){
		$("#checkJson").on("click",function(){
			var member={ id : "park", 
				name : "박지성",
				pwd : "1234",
				email : "park@test.com"};
			$.ajax({
				type : "post",
				url : "${contextPath}/test/info",
				contentType : "application/json",
				data : JSON.stringify(member),
				success : function(data,textStatus) {
					
				},
				error : function(data,textStatus) {
					alert("에러 발생");
				},
				complete : function(data,textStatus) {
					
				}
			});
		});
	})

	</script>
	```
	
	##### @RequestBody 이용하여 MemberVO객체에 저장
	```
	@RequestMapping(value="/info", method=RequestMethod.POST)
	public void modify(@RequestBody MemberVO vo){
		logger.info(vo.toString());
	}
	```
	### (2) @ReponseBody
	> @ResponseBody 어노테이션을 이용하여 @RestController가 아닌 @Controller로 적용된 컨트롤러에서 JSP가 아닌 JSON으로 결과를 전송

## 5. ResponseEntity 클래스 사용해서 응답하기
- @RestController는 별도의 View를 제공하지 않은 채 데이터를 전달하므로 전달 과정에서 예외가 발생할 수 있음
- 예외에 대한 세밀한 제어가 필요한 경우 ResponseEntity 클래스를 사용

	### (1) ResponseEntity의 생성자
	> T body - VO, List, Map등의 객체를 담아서 response의 body로 전달 \
	> HttpStatus status - Http 상태코드 (400, 404, 500 ...)
	```
	ResponseEntity(T body, MultiValueMap<String,String> headers, HttpStatus status)
	ResponseEntity(T body, HttpStatus status)
	```
	
	### (2) return new ResponseEntity
	> URL Mapping된 메소드가 실행되고 반환되는 값을 ResponseEntity로 설정하면 HTTP상태 코드가 같이 전달되어 세부적인 예외 처리 가능
	```
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
	```
	
