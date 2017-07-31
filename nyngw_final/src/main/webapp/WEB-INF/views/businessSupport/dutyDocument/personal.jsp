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
<h2>개인업무조회</h2>
<p class="docTitleDescription">
	로그인한 사원의 개인업무를 조회할 수 있다.
</p>
<script>
$(function(){  
    $('#searchDate option[value=${setSearchOption}]').prop('selected',true);
    $('#reportType option[value=${setReportOption}]').prop('selected',true);
});

$(function(){ //전체선택 체크박스 클릭
	$("#allCheck").click(function() {
			//만약 전체 선택 체크박스가 체크된상태일경우 
			if ($("#allCheck").prop("checked")) {
				//해당화면에 전체 checkbox들을 체크해준다 
				$("input[type=checkbox]").prop("checked", true);
				// 전체선택 체크박스가 해제된 경우
			} else {
				//해당화면에 모든 checkbox들의 체크를해제시킨다. 
				$("input[type=checkbox]").prop("checked", false);
			}
		})
})

function documentDelete() {
	var items=[];
	$('input[name="dd_chk"]:checkbox:checked').each(function(){items.push($(this).val());});
	
	$.ajax({
		url : '/businessSupport/dutyDocument/personalDelete',
		type : 'post',
		data :  {'dd_chk': items},
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
	<form action="/businessSupport/dutyDocument/personal">
		검색기간&nbsp;
			<select name="searchDate" id="searchDate" class="form-control docInputSelect" style="width: 100px;">
				<option value="today">금일</option>
				<option value="week">1주일</option>
				<option value="month">1개월</option>
				<option value="trimester">3개월</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		보고유형&nbsp;
			<select name="reportType" id="reportType" class="form-control docInputSelect" style="width: 120px;">
				<option value="">전체</option>
				<option value="code1">일일일지</option>
				<option value="code2">주간일지</option>
				<option value="code3">월간일지</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		검색입력&nbsp;&nbsp;
			<input type="text" name="val" value="${select.val}" class="form-control eaInputSearch">&nbsp;
		<input type="submit" value="검색"  class="btn btn-default">
	</form>
		<br>
		<br>
		<table class="table table-border">
			<tr>
				<th><input type="checkbox" id="allCheck" value="1"></th>
				<th>번호</th>
				<th>제목</th>
				<th>등록일</th>
				<th>보고유형</th>
			</tr>
			<c:choose>
				<c:when test="${viewData.documentTotalCount > 0 }">
					<c:forEach items="${viewData.documentList }" var="board" varStatus="i">
						<tr>
							<td><input type="checkbox" name="dd_chk" value="${board.dd_number}"><input type="hidden" name="reportType" value="${select.reportType}"></td>
							<td>${fn:substring(board.dd_number,2,10077777)}</td>
							<td><a href="/businessSupport/dutyDocument/personalDetail?dd_number=${board.dd_number}&page=${pageNumber}&reportType=${setReportOption}&searchDate=${setSearchOption}&val=${select.val}">${board.dd_title }</a></td>
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
			<c:if test="${beginPage > perPage}">
				<a href="<c:url value="/businessSupport/dutyDocument/personal?page=${beginPage-1}&searchDate=${select.searchDate}&reportType=${select.reportType}&val=${select.val}&setSearchOption=${setSearchOption}"/>">이전</a>
			</c:if>
			<c:forEach var="i" begin="${beginPage}" end="${endPage}">
				<a href="/businessSupport/dutyDocument/personal?page=${i}&searchDate=${select.searchDate}&reportType=${select.reportType}&val=${select.val}&setSearchOption=${setSearchOption}">[${i}]</a>
			</c:forEach>
			<c:if test="${endPage < viewData.getPageTotalCount()}">
				<a href="<c:url value="/businessSupport/dutyDocument/personal?page=${endPage + 1}&searchDate=${select.searchDate}&reportType=${select.reportType}&val=${select.val}&setSearchOption=${setSearchOption}"/>">다음</a>
			</c:if>
		</div>
	</div>
	<button><a href="/businessSupport/dutyDocument/personalWriteForm">글쓰기</a></button>
<!-- 	<button><a href="/businessSupport/dutyDocument/personalWriteForm">선택삭제</a></button> -->
	<input type="button" value="선택삭제" onclick="documentDelete();" /> 
</body>
</html>