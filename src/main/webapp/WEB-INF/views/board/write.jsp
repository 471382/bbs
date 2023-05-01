  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/controller/resources/css/template.css"/>
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
<!-- <form action="/controller/board/write" method="post" class="form-horizontal">
    <div class="form-group">
        <label for="title" class="control-label col-sm-2">Title</label>
        <div class="col-sm-10">
            <input type="text" path="title" id="title" class="form-control" required="required"/>
        </div>
    </div>
   
    <div class="form-group">
        <label for="content" class="control-label col-sm-2">Content</label>
        <div class="col-sm-10">
            <textarea path="content" id="content" class="form-control" rows="8" required="required"></textarea>
        </div>
    </div>
     <div class="form-group">
        <label for="writer" class="control-label col-sm-2">Writer</label>
        <div class="col-sm-10">
           <input type="text" path="writer" id="writer" class="form-control" required="required"/>
        </div>
    </div>
    <div class="form-group">
        <label for="file" class="control-label col-sm-2">File</label>
        <div class="col-sm-10">
            <input type="file" name="file" id="file" class="form-control-file"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </div>
</form> -->
	<form role="form" action="/controller/board/write" method="post" enctype="multipart/form-data">
		<h1> 제목  <input type="text"
				name='title' placeholder="제목을 입력해 주세요" style="width:100%; height:30px"></h3> 
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