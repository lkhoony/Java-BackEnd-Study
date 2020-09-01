<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>

<jsp:useBean id="m" class="sec02.ex05_el_use_bean.MemberBean"></jsp:useBean> 
<jsp:setProperty property="*" name="m"/>
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
  	    <td>${m.id }</td>
  	    <td>${m.pwd }</td>
  	    <td>${m.name }</td>
  	    <td>${m.email }</td>
  	  </tr>
  	</tbody>
  </table>
</body>
</html>