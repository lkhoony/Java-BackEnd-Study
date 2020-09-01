<%@page import="sec03.ex01_java_bean.* , java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	MemberDAO memberDao = new MemberDAO();
	memberDao.addMember(new MemberBean(id,pwd,name,email));
	List<MemberBean> memberList = memberDao.listMember();
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
  	    <th>가입일</th>
  	  </tr>
  	</thead>
  	<tbody>
  	  <%
  	  if(memberList.size()==0){
  	  %>
  	  <tr>
  	    <td colspan="5">등록된 회원이 없습니다.</td>
  	  </tr>
  	  <%
  	  }else{
  		  int len = memberList.size();
  		  for(int i=0;i<len;i++){
  	  %>
		      
		      
	  <tr>
	  	<td><%=memberList.get(i).getId() %></td>
	  	<td><%=memberList.get(i).getPwd() %></td>
	  	<td><%=memberList.get(i).getName() %></td>
	  	<td><%=memberList.get(i).getEmail() %></td>
	  	<td><%=memberList.get(i).getJoinDate() %></td>
	  </tr>
  	  <% 	  
  		  }
  	  %>
  	  	
  	  <% 
  	  }
	  %>
  	</tbody>
  
  </table>
</body>
</html>