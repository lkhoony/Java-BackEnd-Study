<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <c:if test="${empty param.id}">
    <p>아이디를 입력하세요.</p>
    <a href='Login.jsp'>로그인하러가기</a>
  </c:if>
  
  <c:if test="${not empty param.id }">
  
  	<c:if test="${param.id == 'admin' }">
  	  <h1>관리자로 로그인 했습니다.</h1>
  	  <input type="button" value="회원정보 삭제하기">
  	  <input type="button" value="회원정보 수정하기">
  	</c:if>
  	
  	<c:if test="${param.id != 'admin' }">
  	  <h1>${param.id }님 반갑습니다!</h1>
  	</c:if> 
  </c:if>
</body>
</html>