<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
   
<%
	request.setCharacterEncoding("utf-8");
	session.setAttribute("address", "수원시 팔달구");
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
  	  	<td>${id }</td>
  	  	<td>${pwd }</td>
  	  	<td>${name }</td>
  	  	<td>${email }</td>
  	  	<td>${address }</td>
  	  </tr>
  	</tbody>
  </table>
</body>
</html>