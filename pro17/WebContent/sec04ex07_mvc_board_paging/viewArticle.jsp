<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<head>
   <meta charset="UTF-8">
   <title>글보기</title>
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script type="text/javascript">
   
       function backToList(obj){
	       obj.action="${contextPath}/board/listArticles.do";
	       obj.submit();
	   }
      
	   function fn_enable(){
		   
    	   $("#i_title").attr("disabled",false);
    	   $("#i_content").attr("disabled",false);
    	   $("#i_imageFileName").attr("disabled",false);
    	   $("#tr_btn_modify").css("display","block");
    	   $("#tr_btn").css("display","none");
       }
	   
       function fn_modify_article(frmArticle){
    	   frmArticle.action="${contextPath}/board/modArticle.do";
    	   frmArticle.submit();
       }
       
       function readURL(input) {
    	   if (input.files && input.files[0]) {
    	   // input.files : file 객체 (file의 정보가 들어있는 객체)배열로 반환됨
    	       var reader = new FileReader();
    		   reader.onload = function (e) {
    		       $('#preview').attr('src', e.target.result);
    	       }
    	       reader.readAsDataURL(input.files[0]); // 내용을 다 읽은후 read.onload 콜백함수를 실행
    	   }
       }
       
       function fn_remove_article(url,articleNO){
    	   
    	  var form = document.createElement("form");
    	  form.setAttribute("method","post");
    	  form.setAttribute("action",url);
    	  var articleNOInput = document.createElement("input");
    	  articleNOInput.setAttribute("type","hidden");
    	  articleNOInput.setAttribute("name","articleNO");
    	  articleNOInput.setAttribute("value",articleNO);
    	  
    	  form.appendChild(articleNOInput);
    	  document.body.appendChild(form);
    	  form.submit();
    	  
       }
       
       function fn_reply_form(url,parentNO){
    	   
     	  var form = document.createElement("form");
     	  form.setAttribute("method","post");
     	  form.setAttribute("action",url);
     	  var parentNOInput = document.createElement("input");
     	  parentNOInput.setAttribute("type","hidden");
     	  parentNOInput.setAttribute("name","parentNO");
     	  parentNOInput.setAttribute("value",parentNO);
     	  
     	  form.appendChild(parentNOInput);
     	  document.body.appendChild(form);
     	  form.submit();
     	  
        }
   </script>
</head>
<body>
  <form name="frmArticle" method="post"   enctype="multipart/form-data">
  <table  border="0" align="center" >
    <tr>
      <td width="150" align="center" bgcolor="#FF9933">글번호</td>
      <td>
       <input type="text"  value="${article.articleNO }"  disabled="" />
       <input type="hidden" name="articleNO" value="${article.articleNO}"  />
      </td>
    </tr>
    <tr>
      <td width="150" align="center" bgcolor="#FF9933">작성자 아이디</td>
      <td>
        <input type="text" value="${article.id }" name="id"  disabled="" />
      </td>
    </tr>
    <tr>
      <td width="150" align="center" bgcolor="#FF9933">제목</td>
      <td>
        <input type="text" value="${article.title }"  name="title"  id="i_title" disabled="" />
      </td>   
    </tr>
    <tr>
      <td width="150" align="center" bgcolor="#FF9933">내용</td>
      <td>
        <textarea rows="20" cols="60"  name="content"  id="i_content"  disabled="" />${article.content }</textarea>
      </td>  
    </tr>
 
    <c:if test="${not empty article.imageFileName && article.imageFileName!='null' }">  
      <tr>
        <td width="20%" align="center" bgcolor="#FF9933"  rowspan="2">이미지</td>
        <td>
          <input  type= "hidden" name="originalFileName" value="${article.imageFileName }" />
          <img src="${contextPath}/download.do?imageFileName=${article.imageFileName}&articleNO=${article.articleNO }" id="preview" /><br>
       
        </td>   
      </tr>  
      <tr>
        <td>
          <input  type="file"  name="imageFileName " id="i_imageFileName" disabled="" onchange="readURL(this);" />
        </td>
      </tr>
    </c:if>
      <tr>
	    <td width="20%" align="center" bgcolor="#FF9933">등록일자</td>
	    <td>
	      <input type=text value="<fmt:formatDate value="${article.writeDate}" />" disabled="" />
	    </td>   
      </tr>
      <tr id="tr_btn_modify"  style="display : none;">
	    <td colspan="2" align="center" >
	      <input type=button value="수정 반영하기" onClick="fn_modify_article(frmArticle)" > 
          <input type=button value="취소"  onClick="backToList(frmArticle)">
	    </td>   
      </tr>
    
      <tr id="tr_btn" >
        <td colspan=2 align="center">
	      <input type=button value="수정하기" onClick="fn_enable()">
	  	  <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
	  	  <input type=button value="리스트로 돌아가기" onClick="backToList(frmArticle)">
	      <input type=button value="답글쓰기" onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.articleNO})">
        </td>
      </tr>
    </table>
  </form>
</body>
</html>