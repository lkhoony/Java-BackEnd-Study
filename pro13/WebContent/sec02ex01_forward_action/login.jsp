<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%
  if(msg!=null){
  %>
  <h1>아이디를 입력하지 않으셨습니다. 아이디를 입력하세요</h1>
  <%
  }
  %>
  
  <form action="result.jsp" method="post" enctype="utf-8">
    id : <input type="text" name="id">
    pw : <input type="password" name="pw">
    <input type="submit" value="로그인">
    <input type="reset" value="다시입력">
  </form>
  
</body>
</html>