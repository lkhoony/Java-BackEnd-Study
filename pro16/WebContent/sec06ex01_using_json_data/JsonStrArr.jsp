<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
  $(function() {
	$("#output_btn").on("click",function(){
	  var jsonStr = '{"name" : ["홍길동","이순신","임꺽정"]}';
	  var jsonInfo = JSON.parse(jsonStr);
	  var output = "회원 이름<br>";
	  output+="==========<br>";
	  for(var i of jsonInfo.name){
		  output += (i + "<br>");
		  console.log(output);
	  }
	  $("#json_data").html(output);
	});
  });
</script>
<meta charset="UTF-8">
<title>JSON String Array</title>
</head>
<body>
  <button id="output_btn">JSON 출력</button>
  <div id="json_data"></div>
</body>
</html>