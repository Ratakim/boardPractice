<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>
<div id="nav">
 <%@ include file="../include/nav.jsp" %>
</div>
	<form method="post" action="/board/write">

		<div>
			제목 : <input name="title" id="title" size="80" placeholder="제목을 입력하세요">
		</div>
		<div>
			작성자 : <input name="writer" id="writer" size="80" placeholder="작성자를 입력하세요">
		</div>
		<br>
		<br> 내용 :
		<div style="width: 800px; height: 100px;">
			<textarea id="content" name="content" rows="6" cols="80"
				placeholder="내용을 입력하세요"></textarea>
		</div>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br>


		<div style="width: 700px; text-align: right;">
			<button type="submit" name="submit">확인</button>
		</div>

	</form>
</body>
</html>