<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="dan" value="${param.dan }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <table>
  	<tr>
      <th colspan="2"><c:out value="${dan }"></c:out>단 출력</th>
  	</tr>
  	
  	<c:forEach var="i" begin="1" end="9" step="1">
  	  <tr>
        <td>${dan } X ${i }</td>
        <td>${dan*i }</td>
      </tr>
  	</c:forEach>
  </table>
  <a href='/pro12/gugudan.html'>구구단 입력</a>
</body>
</html>