<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  *{
  	margin : 0;
  	padding : 0;
  }
  ul{
  	list-style: none;
  }
  li{
  	border-bottom: 1px solid #0000002b;
  }
  
  a{
  	vertical-align: top;
  	text-decoration: none;
  	color: inherit;
  }
  
  img{
  	vertical-align: top;
  }
  #wrap{
  	width: 900px;
  	margin : 0 auto;
  }
  
  li{
  	padding:10px 0;
  }
  li:after{
  	display : block;
  	clear : both;
  	content : "";
  	
  }
  
  li span{
  	display : block;
  	float : left;
  	width : 300px;
  	height : 100px;
  	text-align: center;
  	line-height: 100px;
  }
  
  
</style>
</head>
<body>
  <div id="wrap">
  	<ul id="wrap_list">
  	  <li>
  	  	<span>이미지</span>
  	  	<span>이미지 이름</span>
  	  	<span>선택하기</span>
  	  </li>
  	  
  	  <%
  	  	for(int i=0;i<10;i++){
  	  %>
  	  <li>
  	  	<span>
  	  	  <a href="">
  	  	  	<img alt="img" src="http://placehold.it/100X100">
  	  	  </a>
  	  	</span>
  	  	<span>
  	  	  <a href="">이미지 이름 : img</a>
  	  	</span>
  	  	<span><input type="checkbox" name="check"></span>
  	  </li>
  	  <%
  	  }
  	  %>
  	</ul>
  </div>
</body>
</html>