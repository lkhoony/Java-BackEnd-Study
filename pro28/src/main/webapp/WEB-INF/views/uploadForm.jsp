<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일 업로드 하기</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">
		var cnt = 1;
		function addFile() {
			$("#d_file").append("<br>" + "<input type='file' name = 'file" + cnt + "' '/>");
			cnt++;
		}
	
	</script>
</head>
<body>
	<h1>파일 업로드하기</h1>
	<form action="${contextPath}/upload" method="post" enctype="multipart/form-data">
		<label>아이디 : </label> <input type="text" name="id"><br>
		<label>이름 : </label> <input type="text" name="name"><br>
		<input type="button" value="파일 추가" onclick="addFile()">
		<div id="d_file">
		</div>
		<input type="submit" value="업로드">
	</form>
</body>
</html>