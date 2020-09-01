<%@page import="sec06.ex05_core_foreach.MemberBean"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	List membersList = new ArrayList();
	
	MemberBean m1 = new MemberBean("son","1234","손흥민","son@test.com");
	MemberBean m2 = new MemberBean("ki","1234","기성용","ki@test.com");
	MemberBean m3 = new MemberBean("park","1234","박지성","park@test.com");
	membersList.add(m1);
	membersList.add(m2);
	membersList.add(m3);
%>

<c:set var="membersList" value="<%=membersList%>"></c:set>

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
  	<c:forEach var="i" begin="0" end="2" step="1">
	  <tr>
	  	<td>${membersList[i].id }</td>
	  	<td>${membersList[i].pwd }</td>
	  	<td>${membersList[i].name }</td>
	  	<td>${membersList[i].email }</td>
	  </tr>
	</c:forEach>
	
	<c:forEach var="member" items="${membersList}">
	  <tr>
	  	<td>${member.id }</td>
	  	<td>${member.pwd }</td>
	  	<td>${member.name }</td>
	  	<td>${member.email }</td>
	  </tr>
	</c:forEach>
	
  	</tbody>
  </table>
</body>
</html>