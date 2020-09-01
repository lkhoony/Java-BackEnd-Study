<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포맷팅 태그 라이브러리</title>
</head>
<body>
  <h2>fmt의 number 태그를 이용한 숫자 포맷팅 예제</h2>
  <c:set var="price" value="1000000000"></c:set>
  <fmt:formatNumber value="${price }" type="number" var="priceNumber"></fmt:formatNumber>
  통화로 표현 시 : <fmt:formatNumber value="${price }" type="currency" currencySymbol="￦" groupingUsed="true"></fmt:formatNumber><br>
  퍼센트로 표현 시 : <fmt:formatNumber value="${price }" type="percent" currencySymbol="￦" groupingUsed="false"></fmt:formatNumber><br>
  일반 숫자로 표현 시 : ${priceNumber}<br>
  
  <h2>formatDate 예제</h2>
  <c:set var="now" value="<%=new Date() %>"></c:set>
  <fmt:formatDate value="${now }" type="date" dateStyle="full"/><br>
  <fmt:formatDate value="${now }" type="date" dateStyle="short"/><br>
  <fmt:formatDate value="${now }" type="time"/><br>
  <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/><br>
  <fmt:formatDate value="${now }" pattern="YYYY-MM-dd hh:mm:ss"/><br>
  
  한국 현재 시간 : <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/><br>
  <fmt:timeZone value="America/New York">
    뉴욕 현재 시간 : <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/><br>
  </fmt:timeZone>
</body>
</html>