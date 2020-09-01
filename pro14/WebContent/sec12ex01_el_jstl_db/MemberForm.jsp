<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("utf-8");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 창</title>
</head>
<body>
  <h1>회원가입창</h1>
  <form action="MemberAction.jsp">
    <table>
      <tr>
        <td>아이디</td>
      	<td><input type="text" name="id"></td>
      </tr>
      <tr>
        <td>비밀번호</td>
      	<td><input type="password" name="pwd"></td>
      </tr>
      <tr>
        <td>이름</td>
      	<td><input type="text" name="name"></td>
      </tr>
      <tr>
        <td>이메일</td>
      	<td><input type="text" name="email"></td>
      </tr>
      <tr>
        <td><input type="submit" value="회원가입"></td>
      	<td><input type="reset" value="다시입력"></td>
      </tr>
    </table>
  
  
  
  </form>
</body>
</html>