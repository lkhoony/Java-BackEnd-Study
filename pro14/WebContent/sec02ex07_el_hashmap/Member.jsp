<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="sec02.ex07_el_hashmap.*, java.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="m1" class="sec02.ex06_el_collection.MemberBean"></jsp:useBean> 
<jsp:setProperty property="*" name="m1"/>
<jsp:useBean id="memberList" class="java.util.ArrayList"></jsp:useBean>
<jsp:useBean id="memberMap" class="java.util.HashMap"></jsp:useBean>
<%
	memberMap.put("id", "park2");
	memberMap.put("pwd", "4321");
	memberMap.put("name", "박지성");
	memberMap.put("email", "park2@test.com");

	MemberBean m2 = new MemberBean("son","1234","손흥민","son@test.com");
	memberList.add(m1);
	memberList.add(m2);
	memberMap.put("memberList", memberList);
	
%>
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
  	    <td>${memberMap.id}</td>
  	    <td>${memberMap.pwd }</td>
  	    <td>${memberMap.name }</td>
  	    <td>${memberMap.email }</td>
  	  </tr>
  	  <tr>
  	    <td>${memberMap.memberList[0].id }</td>
  	    <td>${memberMap.memberList[0].pwd }</td>
  	    <td>${memberMap.memberList[0].name }</td>
  	    <td>${memberMap.memberList[0].email }</td>
  	  </tr>
  	  <tr>
  	    <td>${memberMap.memberList[1].id }</td>
  	    <td>${memberMap.memberList[1].pwd }</td>
  	    <td>${memberMap.memberList[1].name }</td>
  	    <td>${memberMap.memberList[1].email }</td>
  	  </tr>

  	</tbody>
  </table>
</body>
</html>