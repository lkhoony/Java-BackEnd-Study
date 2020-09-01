<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%
	String name = (String)request.getAttribute("name");
	String address = (String)request.getAttribute("address");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request 내장객체 바인딩</title>
</head>
<body>
  <div>이름 : <%=name %></div>  
  <div>주소 : <%=address %></div>
</body>
</html>