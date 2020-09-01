<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Integer dan = Integer.parseInt(request.getParameter("dan"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <table>
  	<tr>
      <th colspan="2"><%=dan %>단 출력</th>
  	</tr>
  
  <%
  for(int i=1;i<=9;i++){
  %>
  	<tr>
      <td><%=dan%> X <%=i %> = </td>
      <td><%=dan*i %></td>
    </tr>
  <%
  }
  %>
  </table>
  <a href='/pro12/gugudan.html'>구구단 입력</a>
</body>
</html>