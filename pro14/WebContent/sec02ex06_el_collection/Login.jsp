<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <form action="Result.jsp">
  	아이디 : <input type="text"><br>
  	비밀번호 : <input type="password"><br>
  	<input type="submit" value="로그인"><input type="reset" value="다시입력">
  </form>
  <a href='${pageContext.request.contextPath}/sec02ex06_el_collection/MemberForm.jsp'>회원가입하기</a>
</body>
</html>