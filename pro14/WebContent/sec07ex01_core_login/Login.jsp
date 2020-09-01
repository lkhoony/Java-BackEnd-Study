<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인창</title>
</head>
<body>
  <form action="Result.jsp" method="post" enctype="utf-8">
  	<table>
  	  <tr>
  	  	<td>ID</td>
  	  	<td><input type="text" name="id"></td>
  	  </tr>
  	  <tr>
  	  	<td>PWD</td>
  	  	<td><input type="password" name="pwd"></td>
  	  </tr>
  	  <tr>
  	  	<td>이름</td>
  	  	<td><input type="text" name="id"></td>
  	  </tr>
  	  <tr>
  	  	<td>이메일</td>
  	  	<td><input type="text" name="id"></td>
  	  </tr>
  	  <tr>
  	  	<td><input type="submit" value="로그인"></td>
  	  	<td><input type="reset" value="다시입력"></td>
  	  </tr>
  	</table>
  </form>
</body>
</html>