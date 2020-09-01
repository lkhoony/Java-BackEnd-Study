<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.stream.Stream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="sec02.ex06_el_collection.*, java.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="m1" class="sec02.ex06_el_collection.MemberBean"></jsp:useBean> 
<jsp:setProperty property="*" name="m1"/>
<jsp:useBean id="memberList" class="java.util.ArrayList"></jsp:useBean>
<jsp:useBean id="memberSet" class="java.util.HashSet"></jsp:useBean>

<%
	MemberBean m2 = new MemberBean("son","1234","손흥민","son@test.com");
	memberList.add(m1);
	memberList.add(m2);
	memberSet.add(m1);
	memberSet.add(m2);
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
  	    <td>${memberList[0].id }</td>
  	    <td>${memberList[0].pwd }</td>
  	    <td>${memberList[0].name }</td>
  	    <td>${memberList[0].email }</td>
  	  </tr>
  	  <tr>
  	    <td>${memberList[1].id }</td>
  	    <td>${memberList[1].pwd }</td>
  	    <td>${memberList[1].name }</td>
  	    <td>${memberList[1].email }</td>
  	  </tr>
  	  <%
  	  	// # stream으로 out.write할 경우 io exception 발생 >> why??
  	  	Iterator iterator = memberSet.iterator();
  	  	while(iterator.hasNext()){
  	  		MemberBean member = (MemberBean)iterator.next();
  	  %>
  	   <tr>
  	    <td><%=member.getId() %></td>
  	    <td><%=member.getPwd() %></td>
  	    <td><%=member.getName() %></td>
  	    <td><%=member.getEmail() %></td>
  	  </tr>
  	  <% 
  	  	}
  	  %>

  	</tbody>
  </table>
</body>
</html>