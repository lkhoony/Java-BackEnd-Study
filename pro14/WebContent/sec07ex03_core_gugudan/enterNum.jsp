<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
  <h1>구구단 단수를 입력해 주세요</h1>
  <form method="get" action="gugudan.jsp">
  	단수 : <input type="text" name="dan"> <br>
  	<input type="submit" value="출력하기">
  </form>
</body>
</html>