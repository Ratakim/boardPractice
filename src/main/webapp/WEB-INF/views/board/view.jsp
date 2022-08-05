<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 조회</title>
</head>
<body>
<div id="nav">
 <%@ include file="../include/nav.jsp" %>
</div>
	

<label>제목</label>
${view.title}<br />

<label>작성자</label>
${view.writer}<br />

<label>내용</label><br />
${view.content}<br />
	
<div>
<a href="/board/modify?no=${view.no}">게시물 수정</a>, <a href="/board/delete?no=${view.no}">게시물 삭제</a>
</div>

<ul>
<c:forEach items="${reply}" var="reply">
<li>
	<div>
		<p>${reply.writer} / <fmt:formatDate value="${reply.write_Date}" pattern="yyyy-MM-dd" /></p>
		<p>${reply.content }</p>
	</div>
</li>	
</c:forEach>
</ul>

<div>

	<form method="post" action="/reply/write">
	
		<p>
			<label>댓글 작성자</label> <input type="text" name="writer">
		</p>
		<p>
			<textarea rows="5" cols="50" name="content"></textarea>
		</p>
		<p>
		<input type="hidden"  name="bno" value="${view.no}">
			<button type="submit">댓글 작성</button>
		</p>
	</form>
	
</div>

</body>
</html>