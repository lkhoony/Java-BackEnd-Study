<%@page import="sec03.ex01_inner_attribute.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
   
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
  <table>
  	<thead>
  	  <tr>
  	  	<th>아이디</th>
  	  	<th>비밀번호</th>
  	  	<th>이름</th>
  	  	<th>이메일</th>
  	  </tr>
  	</thead>
  	<tbody>
  	  <tr>
  	  	<td>${member.id}</td>
  	  	<td>${member.pwd }</td>
  	  	<td>${member.name }</td>
  	  	<td>${member.email }</td>
  	  </tr>
  	</tbody>
  </table>
</body>
</html>