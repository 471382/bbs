  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/controller/resources/css/template.css" />
<title>Read Page</title>
<script>
$(document).ready(function(){
	
	var formObj = $("form[role='form']");
	
	
	$(".btn-warning").on("click", function(){
		formObj.attr("action", "/controller/board/modify");
		formObj.attr("method", "get");		
		formObj.submit();
	});
	
	$(".btn-danger").on("click", function(){
		formObj.attr("action", "/controller/board/remove");
		formObj.submit();
	});
	
	$(".btn-primary").on("click", function(){
		self.location = "/controller/board/list";
	});
	
});

</script>
</head>
<body>
 <div class="main">
  
<form role="form" method="post">
	<input type='hidden' name='bno' value="${boardDto.bno}">
</form>
	<h1 class="pageMotif">READ</h1>
	<h2 class="title">
		제목<BR>
		<div style = "border:2px solid blue; width:80%;">
		${boardDto.title }
		</div></h2>
	
		<h2 class="text">
		내용</h2><div style = "border:2px solid green; width:80%; height:600px;">
		<c:if test="${boardDto.file != null and boardDto.file != ''}">
    <c:choose>
        <c:when test="${boardDto.file.endsWith('.JPG') or boardDto.file.endsWith('.JPEG') or boardDto.file.endsWith('.PNG') or boardDto.file.endsWith('.GIF')}">
            <a title="클릭 시 다운로드" href="${pageContext.request.contextPath}/download?fileName=${boardDto.file}"><img class="imgFile" src="/controller/resources/workspace/${ boardDto.file }"/></a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/download?fileName=${boardDto.file}">${boardDto.file}</a>
        </c:otherwise>
    </c:choose>
</c:if>
		<br><div class = "content">${boardDto.content }</div></div>
	
		<h2 class="writer">
		작성자 <BR><div style = "border:2px solid pink; width:40%;">${boardDto.writer }</div>
		<%-- <input type="text"
			name="writer" style="width:50%" value="${boardDto.writer}"
			readonly="readonly"> --%>
		<h2>

<!-- /.box-body -->
<div class="box-footer">
	<button type="submit" class="btn btn-warning">수정</button>
	<button type="submit" class="btn btn-danger">삭제</button>
	<button type="submit" class="btn btn-primary">글 목록</button>
</div>

			



</div>
	
</body>
</html>