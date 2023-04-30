  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<script>
$(document).ready(function() {
  $('form').submit(function() {
    // 파일 첨부 여부 확인
    var fileInput = $('input[type=file]');
    if (fileInput.val() !== '') {
      // 파일 첨부되었을 경우 enctype 속성 변경
      $(this).attr('enctype', 'multipart/form-data');
    }
  });
});
</script>
	<form role="form" action="/controller/board/write" method="post">
		<h3> 제목  <input type="text"
				name='title' placeholder="제목을 입력해 주세요" style="width:100%"></h3> 
		<h2> 내용 
			<textarea name="content" rows="8" style="width:100%"
				placeholder="내용을 입력해 주세요."></textarea></h2>
		
		<h2> 작성자 
			<input type="text"
				name="writer"  placeholder="작성자"  style="width:100%"></h2> 
				<input type = "file" name = "file"><br>
		<button type="submit" class="btn">새글등록</button>
		
		
		
	</form>
</body>
</html>