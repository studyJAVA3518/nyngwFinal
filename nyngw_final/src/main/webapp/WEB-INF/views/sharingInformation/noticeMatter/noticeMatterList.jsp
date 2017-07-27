<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>공지사항</h1>
	<form action="/sharingInformation/noticeMatter/nmList">
		<select name="index">
				<option value="board_mem_number" selected="selected">작성자</option>
				<option value="board_title">제목</option>
		</select>
		<input type="hidden" value="${select.index}">
		<input type="text" name="val" value="${select.val}">
		<input type="submit" value="검색">
		<sec:authorize access="hasAnyRole('role_master','role_hr_admin','role_admin','role_ppr_admin')">
		<button type="button"><a href="/sharingInformation/noticeMatter/nmWriteForm?page=${pageNumber }">등록</a></button>
		</sec:authorize>
	</form>
	<table class="table table-bordered">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>등록일</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
		<c:choose>
			<c:when test="${viewData.boardTotalCount > 0 }">
				<c:forEach items="${viewData.boardList }" var="board" varStatus="i">
					<tr>
						<td>${fn:substring(board.board_number,5,10077777)}</td>
						<td><a href="/sharingInformation/noticeMatter/nmDetail?board_number=${board.board_number}&page=${pageNumber}">${board.board_title }</a></td>
						<td><fmt:formatDate value="${board.board_date}" pattern="yyyy/MM/dd"/></td>
						<td>${board.mem_name }</td>
						<td>${board.board_count}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td style="text-align: center;" colspan="5">내용이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div id="pageNum">
		<c:if test="${beginPage > perPage}">
			<a href="<c:url value="/sharingInformation/noticeMatter/nmList?page=${beginPage-1}&index=${select.index}&val=${select.val}"/>">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
			<a href="<c:url value="/sharingInformation/noticeMatter/nmList?page=${pno}&index=${select.index}&val=${select.val}" />">[${pno}]</a>
		</c:forEach>
		<c:if test="${endPage < viewData.getPageTotalCount()}">
			<a href="<c:url value="/sharingInformation/noticeMatter/nmList?page=${endPage + 1}&index=${select.index}&val=${select.val}"/>">다음</a>
		</c:if>
	</div>
</body>
</html>