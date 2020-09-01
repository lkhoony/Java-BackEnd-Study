<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! 
	String name = "이순신";
	public String getName(){
		return name;
	}
%>
<% String age = request.getParameter("age"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>안녕하세요 <%= name %>님!</h1>
  <h1>나이는 <%= age %>입니다!</h1>
  <h1>나이 +10은 <%= Integer.parseInt(age)+10 %></h1>
  <!-- 표현식 안에는 세미콜론이 있으면 안됨! -->
</body>
</html>