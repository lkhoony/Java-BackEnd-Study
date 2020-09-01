<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sec03.ex01_inner_attribute.MemberBean"%>
<%
	request.setCharacterEncoding("utf-8");
	List<MemberBean> memberList = new ArrayList<MemberBean>();
	memberList.add(new MemberBean("lee","1234","이순신","lee@test.com"));
	memberList.add(new MemberBean("son","1234","손흥민","son@test.com"));
	request.setAttribute("memberList", memberList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <jsp:forward page="MemberArrayList.jsp"></jsp:forward>
</body>
</html>