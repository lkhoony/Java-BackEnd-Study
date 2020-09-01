<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>회원가입창</h1>
  <form action="MemberEx09.jsp">
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
	        <td><c:out value="${param.id }"></c:out></td>
			<td><c:out value="${param.pwd }"></c:out></td>
			<td><c:out value="${param.name }"></c:out></td>
			<td><c:out value="${param.email }"></c:out></td>
	      </tr>      
      </tbody>
    </table>
  </form>
</body>
</html>