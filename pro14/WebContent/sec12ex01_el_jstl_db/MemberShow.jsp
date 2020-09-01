<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록된 멤버</title>
</head>
<body>


  <table>
  	<thead>
  	  <tr>
  	    <th>아이디</th>
  	    <th>비밀번호</th>
  	    <th>이름</th>
  	    <th>이메일</th>
  	    <th>등록일</th>
  	  </tr>
  	</thead>
  	<tbody>
  	  <c:set var="memberList" value="${memberList}"></c:set>
  	  
  	  <c:if test="${memberList==null }">
  		<tr>
  		  <td colspan="5">
  		  	등록된 회원이 없습니다.
  		  </td>
  		</tr>
      </c:if>
      
      <c:if test="${memberList!=null }">
      	<c:forEach var="member" items="${memberList }">
      	  <tr>
      	    <td>${member.id}</td>
      	    <td>${member.pwd}</td>
      	    <td>${member.name}</td>
      	    <td>${member.email}</td>
      	    <td>${member.joinDate}</td>
      	  </tr>
      	</c:forEach>
      
      </c:if>
  	</tbody>
  </table>
</body>
</html>