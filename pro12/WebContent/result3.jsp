<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	request.setCharacterEncoding("utf-8");
	String user_id = request.getParameter("user_id");
	String user_pw = request.getParameter("user_pw");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <% 
  if(user_id.equals("admin")){
  %>
    <div>관리자로 로그인 했습니다.</div>
    <form action="">
	  <input type="button" value="회원정보 삭제하기">
	  <input type="button" value="회원정보 수정하기">    
    </form>
  <% 
  }else if(user_id==null || user_id.length()==0){
  %>
	<div>아이디를 입력하세요.</div>
	<a href='/pro12/login.html'>로그인하기</a>
  <% 
  }else{
  %>
	  <h1>환영합니다 <%=user_id %>님!</h1>
  <%
  }
  %>

</body>
</html>