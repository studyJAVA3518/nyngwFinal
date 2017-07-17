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
업무지원 >> 업무일지 >> 개인업무조회
<div>
		<form action="/businessSupport/dutyDocument/personal">
			<table class="table table-border">
				<tr>
					<th>검색기간</th>
					<td>
						<select name="searchDate">
							<option value="today" selected="selected">금일</option>
							<option value="week">1주일</option>
							<option value="month">1개월</option>
							<option value="trimester">3개월</option>
						</select>
<%-- 						<input type="hidden" name="index" value="${index}"> --%>
					</td>
					<th>보고유형</th>
					<td>
						<select name="reportType">
							<option value="%" selected="selected">전체</option>
							<option value="code1">일일일지</option>
							<option value="code2">주간일지</option>
							<option value="code3">월간일지</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>검색입력</th>
					<td colspan="3">
						<label>제목</label>
						<input type="text" name="val">
					</td>
				</tr>
			</table>
			<input type="submit" value="검색">
		</form>
		<br>
		<br>
		<table class="table table-border">
			<tr>
				<th><input type="checkbox" value="1"></th>
				<th>번호</th>
				<th>제목</th>
				<th>등록일</th>
				<th>보고유형</th>
			</tr>
			<c:choose>
				<c:when test="${viewData.documentTotalCount > 0 }">
					<c:forEach items="${viewData.documentList }" var="board" varStatus="i">
						<tr>
							<td><input type="checkbox"  value="1"></td>
							<td>${fn:substring(board.dd_number,2,10077777)}</td>
							<td><a href="/sharingInformation/board/detail?board_number=${board.dd_number}&page=${pageNumber}">${board.dd_title }</a></td>
							<td><fmt:formatDate value="${board.dd_date}" pattern="yyyy/MM/dd"/></td>
							<td>${board.dd_code_name }</td>
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
			<c:forEach begin="1" end="${viewData.getPageTotalCount()}" step="1" var="i">
				<a href="/businessSupport/dutyDocument/personal?page=${i}&searchDate=${select.searchDate}&reportType=${select.reportType}&val=${select.val}">[${i}]</a>
			</c:forEach>
		</div>
	</div>
	<button><a href="/businessSupport/dutyDocument/personalWriteForm">글쓰기</a></button>
	<button><a href="/businessSupport/dutyDocument/personalWriteForm">선택삭제</a></button>
</body>
</html>