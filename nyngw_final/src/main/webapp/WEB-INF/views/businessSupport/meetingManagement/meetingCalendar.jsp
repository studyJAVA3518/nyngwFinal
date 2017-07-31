<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h2>회의 일정</h2>
<p class="docTitleDescription">
	회의 일정을 쓰고 관리할 수 있는 페이지.
</p>
	<table class="table table-border textCenter">
		<tr>
			<th>검색입력</th>
			<td colspan="4">
			<form action="/businessSupport/meetingManagement/meetingCalendar">
				<select name="index" class="form-control docInputSelect">
					<option value="mt_reader">회의주최자</option>
					<option value="mt_date">회의일자</option>
					<option value="mt_title">회의명</option>
				</select>
			<input type="hidden" value="${select.index}">
			<input type="text" name="val" class="form-control docInputSelect" style="width: 60%;" value="${select.val}">
			<input type="submit" value="검색" class="btn btn-default">
			</form> 
			</td>
		</tr>
	<tr>
		<th>회의명</th>
		<th>회의일자</th>
		<th>회의장소</th>
		<th>회의주최자</th>
	</tr>
	<c:choose>
		<c:when test="${viewData.boardCountPerPage > 0}">
		<c:forEach items="${viewData.meetingList}" var="board" >
		<tr>
			<td><a href="/businessSupport/meetingManagement/meetingDetail?mt_number=${board.mt_number}&page=${pageNumber}">${board.mt_title}</a></td>
			<td><fmt:formatDate value="${board.mt_date}" pattern="yy'년'MM'월'dd'일'"/></td>
			<td>${board.mr_name }</td>
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

	<div id="pageNum" class="textCenter">
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
	<div style="float: right;">
		<button class="btn btn-default"><a href="/businessSupport/meetingManagement/addMeetingForm">글쓰기</a></button>
	</div>	
			
			