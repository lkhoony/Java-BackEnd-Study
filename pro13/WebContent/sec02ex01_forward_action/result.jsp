<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%
  if(id.length()==0){
  %>
  	<jsp:forward page="login.jsp">
  	  <jsp:param value="아이디를 입력하시지 않았습니다. 아이디를 입력해주세요" name="msg"/>
  	</jsp:forward>  
  <%
  }
  %>
  
  <h1>어서오세요 <%=id %>님!!</h1>
</body>
</html>