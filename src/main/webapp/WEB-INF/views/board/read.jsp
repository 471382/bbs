  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	<h1>READ</h1>
	<h2>
		제목<BR>
		<div style = "border:2px solid blue; width:40%;">
		${boardDto.title }
		</div>
		<%-- <input type="text"
			name='title'  style="width:50%" value="${boardDto.title}"
			readonly="readonly"> --%></h2>
	
		<h2>
		내용<BR><div style = "border:2px solid green; width:40%; height:200px;">${boardDto.content }</div>
		<%-- <textarea  style="width:50%" name="content" rows="3"
			readonly="readonly">${boardDto.content}</textarea> --%>	</h2>
	
		<h2>
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