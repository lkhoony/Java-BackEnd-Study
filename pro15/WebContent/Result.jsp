<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<c:set var="file" value="${param.file }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 파일 출력하기</title>
</head>
<body>
  <h1>매개변수 : ${file }</h1>
  <c:if test="${not empty file}">
  	<img alt="image" src="${contextPath}/download.do?fileName=${file}">
  </c:if>
  <h1>파일 내려받기</h1>
  <a href="${contextPath}/download.do?fileName=${file}">파일 내려받기</a>
</body>
</html>