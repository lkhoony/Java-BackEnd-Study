<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	request.setAttribute("id","hong");
	request.setAttribute("pwd","1234");
	session.setAttribute("name","홍길동");
	application.setAttribute("email","hong@test.com");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <jsp:forward page="Member.jsp"></jsp:forward>
</body>
</html>