<%@page import="java.util.List"%>
<%@page import="sec03.ex01_java_bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="m" class="sec03.ex01_java_bean.MemberBean" scope="page"></jsp:useBean>


<!--# ------------------------------------------------------------------------------------- -->
<!--## ( setProperty이용, request에서  parameter를 받아서)-->
<!-- MemberBean에 setter 메소드가 존재해야함 -->
<%--
<jsp:setProperty property="id" name="m" value='<%=request.getParameter("id") %>'/>
<jsp:setProperty property="pwd" name="m" value='<%=request.getParameter("pwd") %>'/>
<jsp:setProperty property="name" name="m" value='<%=request.getParameter("name") %>'/>
<jsp:setProperty property="email" name="m" value='<%=request.getParameter("email") %>'/>
--%>

<%-- 
<!--# ------------------------------------------------------------------------------------- -->
<!--## ( setProperty이용, param 설정)-->
<jsp:setProperty property="id" name="m" param="id"/>
<jsp:setProperty property="pwd" name="m" param="pwd"/>
<jsp:setProperty property="name" name="m" param="name"/>
<jsp:setProperty property="email" name="m" param="email"/>
--%>

<%-- 
<!--# ------------------------------------------------------------------------------------- -->
<!--## ( setProperty이용, property에 해당하는 useBean 속성을 자동으로 찾아 설정)-->
<jsp:setProperty property="id" name="m" />
<jsp:setProperty property="pwd" name="m" />
<jsp:setProperty property="name" name="m" />
<jsp:setProperty property="email" name="m" />
--%>


<!--# ------------------------------------------------------------------------------------- -->
<!--## ( setProperty이용, 전송된 매개변수 이름과 useBean 속성을 비교한 후 동일한 빈값에 자동으로 설정)-->
<jsp:setProperty property="*" name="m" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <%
  MemberDAO memberDao = new MemberDAO();
  memberDao.addMember(m);
  List<MemberBean> memberList = memberDao.listMember();
  
  %>
 
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
		      
	  <%--       
	  <tr>
	  	<td><%=memberList.get(i).getId() %></td>
	  	<td><%=memberList.get(i).getPwd() %></td>
	  	<td><%=memberList.get(i).getName() %></td>
	  	<td><%=memberList.get(i).getEmail() %></td>
	  	<td><%=memberList.get(i).getJoinDate() %></td>
	  </tr>
	  --%>
	   
	   <tr>
	  	<td><jsp:getProperty property="id" name="m"/></td>
	  	<td><jsp:getProperty property="pwd" name="m"/></td>
	  	<td><jsp:getProperty property="name" name="m"/></td>
	  	<td><jsp:getProperty property="email" name="m"/></td>
	  	<td><jsp:getProperty property="joinDate" name="m"/></td>
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