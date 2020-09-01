<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Integer score = Integer.parseInt(request.getParameter("score"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시험 점수 학점 변환 결과</title>
</head>
<body>
  <h1>시험점수 <%=score %></h1>
  <%
  if(score>=90){
  %>
  <h1>A학점입니다.</h1>
  <%
  }else if(score>=80&&score<90){
  %>
  <h1>B학점입니다.</h1>
  <%
  }else if(score>=70&&score<80){
  %>
  <h1>C학점입니다.</h1>
  <%
  }else if(score>=60&&score<70){
  %>
  <h1>D학점입니다.</h1>
  <%
  }else {
  %>
  <h1>F학점입니다.</h1>
  <%
  }
  %>
  
  <a href='/pro12/scoreTest.html'>시험점수입력</a>
</body>
</html>