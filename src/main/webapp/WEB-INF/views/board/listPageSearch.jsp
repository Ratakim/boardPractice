<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="nav">
 <%@ include file="../include/nav.jsp" %>
</div>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach items="${list}" var="list">
				<tr>
					<td>${list.no}</td>					
					<td>
					<a href="/board/view?no=${list.no}">${list.title}</a>
					</td>
					<td>${list.writeDate}</td>
					<td>${list.writer}</td>
					<td>${list.hit}</td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
	
	<div>

		<c:if test="${prev}">
			<span>[ <a href="/board/listPageSearch?num=${startPageNum - 1}&searchType=${param.searchType}&keyword=${param.keyword}">이전</a>
				]
			</span>
		</c:if>

		<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
			<span>			
			 <c:if test="${select != num}">
			  <a href="/board/listPageSearch?num=${num}&searchType=${param.searchType}&keyword=${param.keyword}">${num}</a>
			</c:if> 
			<c:if test="${select == num}">
			  <b>${num}</b>
			</c:if>

			</span>
		</c:forEach>

		<c:if test="${next}">
			<span>[ <a href="/board/listPageSearch?num=${endPageNum + 1}&searchType=${param.searchType}&keyword=${param.keyword}">다음</a>
				]
			</span>
		</c:if>

		<%-- <c:forEach begin="1" end="${pageNum}" var="num">
    <span>
     <a href="/board/listPage?num=${num}">${num}</a>
  </span>
 </c:forEach> --%>
	</div>
	
	<div>
  <select name="searchType">
     <option value="t" <c:if test="${param.searchType eq 't'}">selected</c:if>>제목</option>
     <option value="c" <c:if test="${param.searchType eq 'c'}">selected</c:if>>내용</option>
     <option value="tc" <c:if test="${param.searchType eq 'tc'}">selected</c:if>>제목+내용</option>
     <option value="w" <c:if test="${param.searchType eq 'w'}">selected</c:if>>작성자</option>
  </select>
  
  <input type="text" name="keyword" value="${param.keyword}"/>
  
  <button type="button" id="searchBtn">검색</button>
 </div>
 <c:if test="${!empty param.searchType}">
 	<h5>${param.searchType}</h5>
 	<h5>${count}</h5>
 </c:if>
 <script>

 document.getElementById("searchBtn").onclick = function () {
    
  let searchType = document.getElementsByName("searchType")[0].value;
  let keyword =  document.getElementsByName("keyword")[0].value;

  location.href = "/board/listPageSearch?num=1" + "&searchType=" + searchType + "&keyword=" + keyword;
  
 };
</script>

</body>

</html>