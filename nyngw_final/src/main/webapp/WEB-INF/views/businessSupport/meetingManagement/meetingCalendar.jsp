<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
회의 일정!!!!!!!!!!!!!!!
			<table class="table table-border">
				<tr>
					<th>검색입력</th>
					<td colspan="4">
					<form action="/businessSupport/meetingManagement/meetingCalendar">
						<select name="index">
							<option value="mt_reader">주최자</option>
							<option value="mt_date">날짜</option>
							<option value="mt_title">회의명</option>
						</select>
					<input type="hidden" value="${select.index}">
					<input type="text" name="val" value="${select.val}">
					<input type="submit" value="검색">
					</form> 
					</td>
				</tr>
			<tr>
				<th>회의번호</th>
				<th>회의일자</th>
				<th>회의장소</th>
				<th>회의명</th>
				<th>회의 주최자</th>
			</tr>
			<c:choose>
				<c:when test="${viewData.boardCountPerPage > 0}">
				<c:forEach items="${viewData.meetingList}" var="board" >
				<tr>
					<td>${board.mt_number}</td>
					<td><fmt:formatDate value="${board.mt_date}" pattern="yy'년'MM'월'dd'일'"/></td>
					<td>${board.mt_mr_number }</td>
					<td><a href="/businessSupport/meetingManagement/meetingDetail?mt_number=${board.mt_number}&page=${pageNumber}">${board.mt_title}</a></td>
					<td>${board.mt_reader}</td>
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
		<c:if test="${beginPage > perPage}">
			<a href="<c:url value="/businessSupport/meetingManagement/meetingCalendar?page=${beginPage-1}&index=${select.index}&val=${select.val}"/>">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
			<a href="<c:url value="/businessSupport/meetingManagement/meetingCalendar?page=${pno}&index=${select.index}&val=${select.val}" />">[${pno}]</a>
		</c:forEach>
		<c:if test="${endPage < viewData.getPageTotalCount()}">
			<a href="<c:url value="/businessSupport/meetingManagement/meetingCalendar?page=${endPage + 1}&index=${select.index}&val=${select.val}"/>">다음</a>
		</c:if>
	
	
	
	</div>
			<button><a href="/businessSupport/meetingManagement/addMeetingForm">글쓰기</a></button>
			
			
			