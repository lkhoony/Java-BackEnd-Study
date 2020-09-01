<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	$(function(){
		$("#checkJson").on("click",function(){
			var member={ articleNO : "114", 
				writer : "박지성",
				title : "안녕하세요",
				content : "상품 소개 글입니다."};
			$.ajax({
				type : "post",
				url : "${contextPath}/boards",
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
</head>
<body>
	<input type="button" id="checkJson" value="새글 쓰기"><br>
	<div id="output"></div>
</body>
</html>