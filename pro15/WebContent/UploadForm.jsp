<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload File</title>
</head>
<body>
  <form action="${contextPath}/upload.do" method="post" enctype="multipart/form-data">
  	<table>
  	  <tr>
  	    <td>파일 1 : </td>
  	    <td><input type="file" name="file1"></td>
  	  </tr>
  	  <tr>
  	    <td>파일 2 : </td>
  	    <td><input type="file" name="file2"></td>
  	  </tr>
  	  <tr>
  	    <td>매개변수 1 : </td>
  	    <td><input type="text" name="parm1"></td>
  	  </tr>
  	  <tr>
  	    <td>매개변수 2 : </td>
  	    <td><input type="text" name="parm2"></td>
  	  </tr>
  	  <tr>
  	    <td>매개변수 3 : </td>
  	    <td><input type="text" name="parm3"></td>
  	  </tr>
  	  <tr>
  	    <td><input type="submit" value="upload"></td>
  	  </tr>
  	</table>
  </form>
</body>
</html>