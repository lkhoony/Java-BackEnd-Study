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
        <td>${param.id }</td>
        <td>${param.pwd }</td>
        <td>${param.name }</td>
        <td>${param.email }</td>
      </tr> 
    </tbody>
  </table>
</body>
</html>