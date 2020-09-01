<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
  $(function() {
	$("#output_btn").on("click",function(){
		
	  var jsonStr = 
		  '{"members" : [{"name" : "박지성", "age" : 25, "gender" : "남자" , "nickname" : "옹엉앙"} ,'
		  + '{"name" : "손흥민", "age" : 20, "gender" : "남자" , "nickname" : "ㅋㅋㅋ"}] }';
		  
	  var jsonInfo = JSON.parse(jsonStr);
	  var output = "회원 정보<br>";
	  output+="==========<br>";
	  
	  for(var member of jsonInfo.members){
	  	var memberKey = Object.keys(member);
	  	for(var key of memberKey){
	  		output+=(key + " : " + member[key] + "<br>");
	  	}
	    output+="<br>";
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