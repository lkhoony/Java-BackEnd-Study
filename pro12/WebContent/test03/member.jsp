<%@page import="java.util.List"%>
<%@page import="sec10.ex01_db_scriptlet.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	String enteredName = request.getParameter("enteredName");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버조회</title>
<style type="text/css">

	table, td, th{
	  border-collapse: collapse;
	}

	table{
	  width: 900px;
	  table-layout: fixed;
	}
	
	th{
	  font-weight: normal;
	}
	
	th,td{
	  border-bottom: 1px solid #000;
	  text-align: center;
	}
	
	thead th{
	  padding: 5px 0;
	  border-bottom: 2px solid #000;
	}
	
	tbody td{
	  height: 40px;
	}

</style>
</head>
<body>
  <%
  MemberVO memberVo = new MemberVO();
  memberVo.setName(enteredName);
  MemberDAO memberDao = new MemberDAO();
  List memberList = memberDao.listMembers(memberVo);
  %>
  <table>
  	<thead>
	 <tr>
	  	<th>ID</th>
	  	<th>PW</th>
	  	<th>NAME</th>
	  	<th>EMAIL</th>
	  	<th>JOINDATE</th>
	 </tr>
  	</thead>
	<%
	  int listLen = memberList.size();
	
	  for(int i=0;i<listLen;i++){
		  String id = ((MemberVO)memberList.get(i)).getId();
		  String pwd = ((MemberVO)memberList.get(i)).getPwd();
		  String name = ((MemberVO)memberList.get(i)).getName();
		  String email = ((MemberVO)memberList.get(i)).getEmail();
		  Date joinDate = ((MemberVO)memberList.get(i)).getJoinDate();
	%>
	<tr>
  	  <td><%= id %></td>
  	  <td><%= pwd%></td>
  	  <td><%= name%></td>
  	  <td><%= email%></td>
  	  <td><%= joinDate%></td>
  	</tr>
	<%
	
	  }
	 
	%>
	
  </table>
  
</body>
</html>