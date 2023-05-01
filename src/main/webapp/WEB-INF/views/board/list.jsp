
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="/controller/resources/css/template.css?after" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>List Page</title>
<script>
	var result = '${msg}';

	if (result == 'success') {
		alert("처리가 완료되었습니다.");
	}
	$(document).ready(
			function() {
				$('#searchBtn').on(
						"click",
						function(event) {
							alert("list" + '${pageMaker.makePage(1)}'
									+ '&searchType='
									+ $("select option:selected").val()
									+ "&keyword=" + $('#keywordInput').val());
							self.location = "list" + '${pageMaker.makePage(1)}'
									+ '&searchType='
									+ $("select option:selected").val()
									+ "&keyword=" + $('#keywordInput').val();
						});

				$('.writeBtn').on("click", function(event) {
					location.href = "/controller/board/write";
				});
			});
</script>
</head>
<body>
	<div class="main">
		<h2>게시판</h2>
		<h5>목록</h5>
		<div class="search-box">
			<select name="searchType">
				<option value="n"
					<c:out value="${pageMaker.searchType eq '-----'?'selected':'' }"/>>-----</option>
				<option value="t"
					<c:out value="${pageMaker.searchType eq 't'?'selected':'' }"/>>title</option>
				<option value="c"
					<c:out value="${pageMaker.searchType eq 'c'?'selected':'' }"/>>content</option>
				<option value="w"
					<c:out value="${pageMaker.searchType eq 'w'?'selected':'' }"/>>writer</option>
				<option value="tc"
					<c:out value="${pageMaker.searchType eq 'tc'?'selected':'' }"/>>title
					or content</option>
				<option value="cw"
					<c:out value="${pageMaker.searchType eq 'cw'?'selected':'' }"/>>content
					or writer</option>
				<option value="tcw"
					<c:out value="${pageMaker.searchType eq 'tcw'?'selected':'' }"/>>all</option>
			</select> <input type="text" name="keyword" id="keywordInput"
				value="${pageMaker.keyword }">
			<button id="searchBtn">검색</button>
		</div>

		<table>
			<tr>
				<th style="width: 10px">No</th>
				<th style="width: 130px">제목</th>
				<th style="width: 100px">작성자</th>
				<th style="width: 120px">작성일자</th>
				<th style="width: 70px">조회수</th>
			</tr>

			<c:forEach items="${list}" var="dto">
				<tr>
					<td style="width: 10px">${dto.bno }</td>
					<td style="width: 50px"><a
						href="/controller/board/read${pageMaker.makeSearch() }&bno=${dto.bno}">${dto.title }</a></td>
					<td style="width: 30px">${dto.writer}</td>
					<td style="width: 200px">${dto.regdate}</td>

					<td style="width: 20px">${dto.viewcnt}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<button class='writeBtn'>작성</button>

	<div class='paging'>
		<c:if test="${pageMaker.page !=1 }">
			<a href='list${pageMaker.makeSearch(1)}'>&laquo;</a>
		</c:if>
		<c:if test="${pageMaker.prev }">
			<a href='list${pageMaker.makeSearch(pageMaker.startPage-1) }'>&lt;</a>
		</c:if>

		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
			var="idx">
			<c:if test="${pageMaker.page == idx}">
				<span class="current-page">${idx }</span>
			</c:if>
			<c:if test="${pageMaker.page != idx}">
				<a href='list${pageMaker.makeSearch(idx)}'<c:out value="${pageMaker.page==idx?' class=active ':'' }" />>${idx}</a>
			</c:if>
			<%-- <a href='list${pageMaker.makeSearch(idx)}' 
			<c:out value="${pageMaker.page==idx?' class=active ':'' }" />>${idx}</a>--%>
		</c:forEach>

		<c:if test="${pageMaker.next }">
			<a href='list${pageMaker.makeSearch(pageMaker.endPage+1) }'>&gt;</a>
		</c:if>

		<c:if test="${pageMaker.page != pageMaker.totalEndPage}">
			<a href='list${pageMaker.makeSearch(pageMaker.totalEndPage) }'>&raquo;</a>
		</c:if>
	</div>









</body>
</html>