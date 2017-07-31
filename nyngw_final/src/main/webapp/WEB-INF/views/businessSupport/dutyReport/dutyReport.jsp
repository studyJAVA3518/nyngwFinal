<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>내 업무보고</h2>
<p class="docTitleDescription">
	본인이 시행한 업무를 상사에게 보고하는 페이지.
</p>
<script>
$(function(){  
    $('#searchDate option[value=${setSearchOption}]').prop('selected',true);
    $('#reportType option[value=${setReportOption}]').prop('selected',true);
});
$(function(){ //전체선택 체크박스 클릭
	$("#check_all").click(function() {
			//만약 전체 선택 체크박스가 체크된상태일경우 
			if ($("#check_all").prop("checked")) {
				//해당화면에 전체 checkbox들을 체크해준다 
				$("input[type=checkbox]").prop("checked", true);
				// 전체선택 체크박스가 해제된 경우
			} else {
				//해당화면에 모든 checkbox들의 체크를해제시킨다. 
				$("input[type=checkbox]").prop("checked", false);
			}
		})
})
function dutyReportDelete() {
	var items=[];
	$('input[name="dr_chk"]:checkbox:checked').each(function(){items.push($(this).val());});
	
	$.ajax({
		url : '/businessSupport/dutyReport/dutyReportDelete',
		type : 'post',
		data :  {'dr_chk': items},
		success : function(result) {
			location.href=result.uri;
		},
		error : function() {
			
		},
		dataType : 'json'
	})
}
</script>
<div>
	<form action="/businessSupport/dutyReport/dutyReport">
		검색기간&nbsp;
			<select name="searchDate" id="searchDate" class="form-control docInputSelect" style="width: 100px;">
				<option value="today">금일</option>
				<option value="week">1주일</option>
				<option value="month">1개월</option>
				<option value="trimester">3개월</option>
			</select>&nbsp;&nbsp;
		보고유형&nbsp;
			<td><select name="reportType" id="reportType" class="form-control docInputSelect" style="width: 110px;">
					<option value="code1">일일보고</option>
					<option value="code2">주간보고</option>
					<option value="code3">월간보고</option>
			</select>&nbsp;&nbsp;
		검색입력&nbsp;
			<select name="titleType" id="titleType" class="form-control docInputSelect" style="width: 80px;">
					<option value="dr_title">제목</option>
					<option value="dr_content">내용</option>
			</select> &nbsp;&nbsp;
		<input type="text" name="val" value="${select.val}"  class="form-control eaInputSearch" style="width: 225px;">&nbsp;
		<input type="submit" value="검색" class="btn btn-default">
	</form>
</div>
<br>
<br>
<div>
	<div>
		<div>
			<table class="table table-bordered">
				<tr>
					<th><input type="checkbox" id="check_all" value="all"></th>
					<th>번호</th>
					<th>보고일</th>
					<th>제목</th>
					<th>보고유형</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
				<c:choose>
					<c:when test="${viewData.documentTotalCount > 0 }">
						<c:forEach items="${viewData.documentList }" var="dutyReport" varStatus="i">
							<tr>
								<td><input type="checkbox" name="dr_chk" value="${dutyReport.dr_number}"></td>
								<td>${fn:substring(dutyReport.dr_number,2,1007777)}</td>
								<td><fmt:formatDate value="${dutyReport.dr_date}" pattern="yyyy/MM/dd" /></td>
								<td><a href="/businessSupport/dutyReport/dutyReportDetail?dr_number=${dutyReport.dr_number }&page=${pageNumber}&reportType=${setReportOption}&searchDate=${setSearchOption}&val=${select.val}">${dutyReport.dr_title}</a></td>
								<td>${fn:substring(dutyReport.dr_code_name,0,2) }보고</td>
								<td>${mem_name }</td>
								<td><fmt:formatDate value="${dutyReport.dr_writedate}" pattern="yyyy/MM/dd" /></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td style="text-align: center;" colspan="7">내용이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<div id="pageNum" style="text-align: center;">
			<c:if test="${beginPage > perPage}">
				<a
					href="<c:url value="/businessSupport/dutyReport/dutyReport?page=${beginPage-1}&searchDate=${select.searchDate}&reportType=${select.reportType}&val=${select.val}&titleType=${select.titleType}"/>">이전</a>
			</c:if>
			<c:forEach var="i" begin="${beginPage}" end="${endPage}">
				<a
					href="/businessSupport/dutyReport/dutyReport?page=${i}&searchDate=${select.searchDate}&reportType=${select.reportType}&val=${select.val}&titleType=${select.titleType}">[${i}]</a>
			</c:forEach>
			<c:if test="${endPage < viewData.getPageTotalCount()}">
				<a
					href="<c:url value="/businessSupport/dutyReport/dutyReport?page=${endPage + 1}&searchDate=${select.searchDate}&reportType=${select.reportType}&val=${select.val}&titleType=${select.titleType}"/>">다음</a>
			</c:if>
		</div>
	</div>
		<button class="btn btn-default"><a href="/businessSupport/dutyReport/dutyReportWriteForm" id="dutyReportWriteForm">글쓰기</a></button>
		<input type="button" value="선택삭제" class="btn btn-default" onclick="dutyReportDelete();" /> 
</div>
