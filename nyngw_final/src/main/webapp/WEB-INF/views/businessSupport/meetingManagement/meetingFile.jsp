<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h2>회의록 관리</h2>
<p class="docTitleDescription">
	회의에 사용되는 회의록 관리페이지
</p>
<div>
		<form action="/businessSupport/meetingManagement/meetingFile">
			<select name="index" class="form-control docInputSelect">
				<option value="md_name">제목</option>
				<option value="md_writer">작성자</option>
			</select>
			<input type="hidden" value="${select.index}">
			<input type="text" name="val" value="${select.val}" class="form-control eaInputSearch" style="width: 400px;">
			<input type="submit" value="검색" class="btn btn-default">
		</form>
		<br>
		<br>
		<table class="table table-border textCenter">
			<tr>
				<th>번호</th>
				<th>회의록명</th>
				<th>작성일</th>
				<th>작성자</th>
			</tr>
			<c:choose>
				<c:when test="${viewData.documentTotalCount > 0 }">
					<c:forEach items="${viewData.meeting_DocumentList }" var="board" >
						<tr>
							<td>${fn:substring(board.md_number,2,10077777)}</td>
							<td><a href="/businessSupport/meetingManagement/meetingFileDetail?md_number=${board.md_number}&page=${pageNumber}">${board.md_name}</a></td>
							<td><fmt:formatDate value="${board.md_date}" pattern="yyyy/MM/dd"/></td>
							<td>${board.md_writer }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td style="text-align: center;" colspan="6">내용이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<div id="pageNum" class="textCenter">
			<c:if test="${beginPage > perPage}">
				<a href="<c:url value="/businessSupport/meetingManagement/meetingFile?page=${beginPage-1}&index=${select.index}&val=${select.val}"/>">이전</a>
			</c:if>
			<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
				<a href="<c:url value="/businessSupport/meetingManagement/meetingFile?page=${pno}&index=${select.index}&val=${select.val}" />">[${pno}]</a>
			</c:forEach>
			<c:if test="${endPage < viewData.getPageTotalCount()}">
				<a href="<c:url value="/businessSupport/meetingManagement/meetingFile?page=${endPage + 1}&index=${select.index}&val=${select.val}"/>">다음</a>
			</c:if>
		</div>
	</div>
	<div style="float: right;" class="insertJoinBtnWrap">
		<button class="btn btn-default"><a href="/businessSupport/meetingManagement/addMeetingFile">회의록 쓰기</a></button>
	</div>
