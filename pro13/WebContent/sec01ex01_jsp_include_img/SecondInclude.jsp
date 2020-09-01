<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>두 번째 include 페이지 입니다.</h1>
  <jsp:include page="image.jsp">
    <jsp:param value="test2" name="name"/>
    <jsp:param value="test2.jpg" name="imgName"/>
  </jsp:include>
</body>
</html>