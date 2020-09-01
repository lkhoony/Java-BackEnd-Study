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
<title>Insert title here</title>
</head>
<body>
    <table>
  	<thead>
  	  <tr>
  	  	<th>아이디</th>
  	  	<th>비밀번호</th>
  	  	<th>이름</th>
  	  	<th>나이</th>
  	  	<th>키</th>
  	  </tr>
  	</thead>
  	<tbody>
  	  <c:choose>
  	  	<c:when test="${empty param.name}">
  	  	  <tr>
  	        <td colspan="5">이름을 입력하세요.</td>
  	      </tr>
  	  	</c:when>
  	  	<c:otherwise>
	  	  <tr>
	  	    <td>${param.id }</td>
	  	    <td>${param.pwd }</td>
	  	    <td>${param.name }</td>
	  	    <td>${param.age }</td>
	  	    <td>${param.heigth }</td>
	  	  </tr>
  	  	</c:otherwise>
  	  </c:choose>
  	</tbody>
  </table>
</body>
</html>