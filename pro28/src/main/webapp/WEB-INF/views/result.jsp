<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>    
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>업로드가 완료되었습니다.</h1>
	<label>아이디 : </label><input type="text" value="${map.id}" readonly="readonly">
	<label>이름 : </label><input type="text" value="${map.name}" readonly="readonly">
	
	<div class="result-images">
		<c:forEach var="imageFileName" items="${map.fileList }">
			<img alt="${imageFileName}" src="${contextPath }/download?imageFileName=${imageFileName }">
			<br><br>
		</c:forEach>
	</div>
	<a href='${contextPath}/form'>다시 업로드 하기</a>
</body>
</html>