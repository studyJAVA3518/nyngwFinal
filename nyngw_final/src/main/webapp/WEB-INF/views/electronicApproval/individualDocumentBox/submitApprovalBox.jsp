<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h2>상신문서함</h2>
<p class="docTitleDescription">
	상신 문서함은 본인이 작성하여 상신한 결재 문서들을 검색 할 수 있으며, 결재 진행 상태를 확인 할 수 있는 메뉴입니다.
</p>
<div class="eaSearchDivMagin">
	<form>
		검색일자
			<select name="EADateOption" class="form-control docInputSelect" style="width: 120px;">
				<option>기안일</option>
				<option>최종 결재일</option>
			</select>&nbsp;
		결재상태
			<select name="EAStatusOption" class="form-control docInputSelect"style="width: 100px;">
				<option>--선택--</option>
				<option>상신</option>
				<option>진행</option>
				<option>종결</option>
				<option>반려</option>
			</select>&nbsp;
		문서검색
			<select name="docSearchOption" class="form-control docInputSelect"style="width: 105px;">
				<option>--선택--</option>
				<option>제목</option>
				<option>품의번호</option>
				<option>문서분류</option>
			</select>&nbsp;
		<input type="text" name="searchText" class="form-control eaInputSearch" style="width: 220px;">
		<button type="button" onclick="submitApprovalBox_go(this.form);" class="btn btn-default">검색</button>
	</form>
</div>
<table class="table textCenter" border="1">
	<tr>
		<th>품의번호</th>
		<th>제목</th>
		<th>기안일</th>
		<th>결재 상태</th> <!-- 	결재상태  ( 결재 한 번도 안 됐을 때 '상신'/결재가 한 번이라도 이루어 졌을 때 '진행' -->
		<th>현재최종결재자</th> <!-- 최종 결재자 -->
	</tr>

	<!-- EA=electronicApproval (전자결재) -->
	<c:choose>
	<c:when test="${viewData.documentCountPerPage > 0}">
	<c:forEach items="${viewData.sangsinList }" var="EA" varStatus="status">
		<tr>
			<td>${EA.ea_number }</td>
			<td><a href="/electronicApproval/individualDocumentBox/submitApprovalDetail?ea_number=${EA.ea_number}">${EA.ea_title }</a></td>
			<td>${EA.ea_startdate}</td>
			<td>${ahStatusList[status.index] }</td>
			<td>${astMemberList[status.index] }</td>
		</tr>
	</c:forEach>
	</c:when>
	<c:otherwise>
	<tr>
		<td colspan="5" style="text-align: center;">상신한 문서가 없습니다.</td>
	</tr>
</c:otherwise>
</c:choose>
</table>

<div id="pageNum" class="textCenter">
		<c:if test="${beginPage > perPage}">
			<a href="<c:url value="/electronicApproval/individualDocumentBox/submitApprovalBox?page=${beginPage-1}&index=${select.index}&val=${select.val}"/>">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
			<a href="<c:url value="/electronicApproval/individualDocumentBox/submitApprovalBox?page=${pno}&index=${select.index}&val=${select.val}" />">[${pno}]</a>
		</c:forEach>
		<c:if test="${endPage < viewData.getPageTotalCount()}">
			<a href="<c:url value="/electronicApproval/individualDocumentBox/submitApprovalBox?page=${endPage + 1}&index=${select.index}&val=${select.val}"/>">다음</a>
		</c:if>
</div>
<script>
	function submitApprovalBox_go(form){
		form.method="get";
		form.action="/electronicApproval/individualDocumentBox/submitApprovalBox";
		form.submit();
	} 
</script>