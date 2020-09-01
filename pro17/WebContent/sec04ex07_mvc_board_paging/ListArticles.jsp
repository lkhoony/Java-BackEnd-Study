<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<c:set var="articlesList" value="${articlesMap.articlesList }"></c:set>
<c:set var="totArticles" value="${articlesMap.totArticles }"></c:set>
<c:set var="section" value="${articlesMap.section }"></c:set>


<c:set var="pageNum" value="${articlesMap.pageNum }"></c:set>

<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Articles</title>
<style type="text/css">
  a{
  	
  	text-decoration : none;
  	color : inherit;
  }
</style>
</head>
<body>
  <table>
    <tr>
	  <td>글번호</td>
	  <td>작성자</td>
   	  <td>제목</td>
	  <td>작성일</td>
	</tr>
	<c:choose>
	  <c:when test="${articlesList==null}">
	  	<tr>
	  	  <td colspan="4">등록된 글이 없습니다.</td>
	  	</tr>
	  </c:when>
	  <c:when test="${articlesList!=null}">
	  	
	  	<%-- items : 리스트가 받아올 배열이름, var : for문 내부에서 사용할 변수 , varStatus : 상태용 변수 --%>
	    <c:forEach items="${articlesList}" var="article" varStatus="articleNum">
	      <tr>
	      	<td style="width:100px">${articleNum.count}</td>
	      	<td style="width:100px">${article.id }</td>
	      	<td>
	      	  <c:choose>
	      	  	<c:when test="${article.level > 1 }">
	      	  	  <c:set var="padding" value="${20*(article.level-1)}"></c:set>
	      	  	  <div style="padding-left : ${padding}px; "><a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}"><span>[답변]</span><span>${article.title}</span></a></div>
	      	  	</c:when>
	      	  	<c:otherwise>
	      	  	  <div><a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}"><span>${article.title}</span></a></div>
	      	  	</c:otherwise>
	      	  </c:choose>	
	      	</td>
	      	<td>${article.writeDate }</td>
	      </tr>
	    </c:forEach>

	  </c:when>
	</c:choose>	
  </table>
  <div>
    <c:if test="${totArticles != null}">
	  <c:choose>
	    <c:when test="${totArticles >100 }">
	      <c:forEach var="page" begin="1" end="10" step="1">
	      	<c:if test="${section>1 && page==1}">
	      	  <a href="${contextPath}/board/listArticles.do?section=${section-1}&pageNum=10">prev</a>
	      	</c:if>
	      	<a href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10+page }</a>
	      	<c:if test="${page==10}">
	      	  <a href="${contextPath}/board/listArticles.do?section=${section+1}&pageNum=1">next</a>
	      	</c:if>
	      </c:forEach>
	    </c:when>
	      	  
	    <c:when test="${totArticles == 100 }">
	      <c:forEach var="page" begin="1" end="10" step="1">
	        <a href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">${((section-1)*10)+page }</a>
	      </c:forEach>
	    </c:when>
	    
	    <c:when test="${totArticles < 100 }">
	      <c:forEach var="page" begin="1" end="${(totArticles/10)+1}" step="1">
	        <a href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">${((section-1)*10)+page }</a>
	      </c:forEach>
	    </c:when>
	  </c:choose>
	</c:if>
  </div>
  <div><a href="${contextPath}/board/articleForm.do">글쓰기</a></div>
</body>
</html>