<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Expression Language</title>
</head>
<body>
  <h1>표현 언어에서 사용되는 데이터들</h1>
  <h1>
  	\${100} : ${100} <br>
  	\${"안녕하세요"} : ${"안녕하세요"}<br>
  	\${"10"+1 } : ${"10"+1 }<br>
  	
  	<%--null과 10을 더하면 10이됨 
  	\${null+10} : ${null+10 }<br> 
  	--%>
  	
  	<%--\${"hello" + 11 } : ${"hello"+11 }<br> # 문자열과 숫자는 더할 수 없음	--%>
  	<%--\${"hello" + "world" } : ${"hello"+"world" }<br> # 문자열끼리 더할 수 없음--%>
  </h1>
</body>
</html>