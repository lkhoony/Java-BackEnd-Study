<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="java.util.*,sec03.ex03_mvc_member_modify_delete.*"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>

<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>회원정보</h1>
  <table>
  	<tr>
  	  <th>ID</th>
  	  <th>PWD</th>
  	  <th>NAME</th>
  	  <th>EMAIL</th>
  	  <th>JOINDATE</th>
  	</tr>
  	
  	<c:choose>
  	  <c:when test="${memberList==null }">
  	  	<tr>
  	  	  <td colspan="5"></td>
  	  	</tr>
  	  </c:when>
  	  
  	  <c:when test="${memberList!=null }">
  	  	<c:forEach var="mem" items="${memberList }">
  	  	  <tr>
  	  	    <td>${mem.id }</td>
  	  	    <td>${mem.pwd }</td>
  	  	    <td>${mem.name }</td>
  	  	    <td>${mem.email }</td>
  	  	    <td>${mem.joinDate }</td>
  	  	    <td><a href="${contextPath}/member/modMemberForm.do?id=${mem.id}">수정</a></td>
  	  	    <td><a href="${contextPath}/member/delMember.do?id=${mem.id}">삭제</a></td>
  	  	  </tr>
  	  	</c:forEach>
  	  </c:when>
  	</c:choose>

  </table>
  <a href="${contextPath}/member/memberForm.do">회원 가입하기</a>

</body>
</html>