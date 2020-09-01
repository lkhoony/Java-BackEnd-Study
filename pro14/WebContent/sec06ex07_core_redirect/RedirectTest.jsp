<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Url Test</title>
</head>
<body>
  <c:redirect url="ShowMember.jsp">
  	<c:param name="id" value="hong"></c:param>
	<c:param name="pwd" value="1234"></c:param>
	<c:param name="name" value="홍길동"></c:param>
	<c:param name="email" value="hong@test.com"></c:param>
  </c:redirect>
</body>
</html>