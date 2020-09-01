<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <form action="${contextPath}/sec06ex04_core_choose/MemberEx05.jsp">
  	아이디 : <input type="text" name="id"><br>
  	비밀번호 : <input type="password" name="pwd"><br>
  	이름 : <input type="text" name="name"><br>
  	나이 : <input type="text" name="age"><br>
  	키 : <input type="text" name="height"><br>
  	<input type="submit" value="로그인"><input type="reset" value="다시입력">
  </form>
</body>
</html>