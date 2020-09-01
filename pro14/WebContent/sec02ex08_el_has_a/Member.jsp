<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="sec02.ex08_el_has_a.*, java.util.*"%>

<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="m" class="sec02.ex08_el_has_a.MemberBean"></jsp:useBean> 
<jsp:setProperty property="*" name="m"/>
<jsp:useBean id="addr" class="sec02.ex08_el_has_a.Address"></jsp:useBean>
<jsp:setProperty property="city" name="addr" value="서울"/>
<jsp:setProperty property="zipcode" name="addr" value="07654"/>

<%
	m.setAddr(addr);
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
  	  	<th>도시</th>
  	  	<th>우편번호</th>
  	  </tr>
  	</thead>
  	<tbody>
  	  <tr>
  	    <td>${m.id }</td>
  	    <td>${m.pwd }</td>
  	    <td>${m.name }</td>
  	    <td>${m.email }</td>
  	    <td><%=m.getAddr().getCity() %></td>
  	    <td><%=m.getAddr().getZipcode() %></td>
  	  </tr>
  	  <tr>
  	    <td>${m.id}</td>
  	    <td>${m.pwd }</td>
  	    <td>${m.name }</td>
  	    <td>${m.email }</td>
  	    <td>${m.addr.city }</td>
  	    <td>${m.addr.zipcode }</td>
  	  </tr>

  	</tbody>
  </table>
</body>
</html>