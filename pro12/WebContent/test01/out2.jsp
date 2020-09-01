<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String name = request.getParameter("name");
	String password = request.getParameter("password");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%
  if(name!=null && name.length()!=0){
  %>
  	<h1><%=name %>,<%=password %></h1>
  <%
  }
  %>
  
  <%
  if(name!=null && name.length()!=0){
  %>
  	<h1><%out.print(name+","+password); %></h1>
  <%
  }
  %>
  
  
</body>
</html>