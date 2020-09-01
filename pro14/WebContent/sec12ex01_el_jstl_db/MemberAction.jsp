<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="java.util.* , sec12.ex01_el_jstl_db.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="member" class="sec12.ex01_el_jstl_db.MemberBean"></jsp:useBean>
<!-- useBean 이용하기 위해서는 해당 class에 기본생성자가 존재해야함!!! -->
<jsp:useBean id="memberDao" class="sec12.ex01_el_jstl_db.MemberDAO"></jsp:useBean>
<jsp:setProperty property="*" name="member"/>
<%
	// MemberBean member = new MemberBean(request.getParameter("id"),request.getParameter("pwd"),request.getParameter("name"),request.getParameter("email"));
	request.setCharacterEncoding("utf-8");
	memberDao.addMember(member);
	List<MemberBean> list = memberDao.memberList();
	request.setAttribute("memberList", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <jsp:forward page="MemberShow.jsp"></jsp:forward>
</body>
</html>