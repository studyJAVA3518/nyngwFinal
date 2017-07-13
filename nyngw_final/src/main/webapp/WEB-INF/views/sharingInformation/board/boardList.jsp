<%@page import="com.nyngw.dto.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판</h1>
	
	<form action="/sharingInformation/board/list">
		<select name="index">
				<option value="board_mem_number" selected="selected">작성자</option>
				<option value="board_title">제목</option>
		</select>
		<input type="hidden" value="${select.index}">
		<input type="text" name="val" value="${select.val}">
		<input type="submit" value="검색">
		<button type="button"><a href="/sharingInformation/board/writeForm?page=${pageNumber }">등록</a></button>
	</form>
	<table class="table table-bordered">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>등록일</th>
			<th>작성자</th>
			<th>기타</th>
		</tr>
		<c:choose>
			<c:when test="${viewData.boardCountPerPage>0 }">
				<c:forEach items="${viewData.boardList }" var="board" varStatus="i">
					<tr>
						<td>${fn:substring(board.board_number,5,10077777)}</td>
						<td><a href="/sharingInformation/board/detail?board_number=${board.board_number}&page=${pageNumber}">${board.board_title }</a></td>
						<td><fmt:formatDate value="${board.board_date}" pattern="yyyy/MM/dd"/></td>
						<td>${board.board_mem_number }</td>
						<td><a href="/sharingInformation/board/updateForm?board_number=${board.board_number}&page=${pageNumber}">
										&nbsp;수정</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td style="text-align: center;">내용이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div id="pageNum">
		<c:forEach begin="1" end="${viewData.getPageTotalCount()}" step="1"	var="i">
			<a href="/sharingInformation/board/list?page=${i}&index=${select.index}&val=${select.val}">[${i}]</a>
		</c:forEach>
	</div>

</body>
</html>