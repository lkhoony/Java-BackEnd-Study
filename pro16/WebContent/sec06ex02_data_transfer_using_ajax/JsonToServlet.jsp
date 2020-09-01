<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#transfer_btn").on("click",function(){
			var _jsonInfo = '{"name" : "박지성" , "age" : 25, "gender" : "남자", "nickname" : "날센돌이"}';
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/JsonToServlet",
				data : {jsonInfo : _jsonInfo},
				success : function(data,textStatus){
				},
				error : function(data,textStatus){
					alert("에러가 발생했습니다.");
				},
				complete : function(data,textStatus) {
				}
			});
		});
	});
</script>
<title>Data Transfer Using Ajax</title>
</head>
<body>
  <button id="transfer_btn">데이터 전송</button>
  <div id="data"></div>
</body>
</html>