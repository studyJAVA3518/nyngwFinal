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
<h2>개인업무 상세</h2>
<p class="docTitleDescription">
	개인업무의 상세 정보를 볼 수 있는 페이지
</p>
	<form>
		<table class="table table-bordered tableGray">
			<tr>
				<th >제목</th>
				<td colspan="3">${dutyDocument.dd_title}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dutyDocument.mem_name}</td>
				<th>업무시작일</th>
				<td><fmt:formatDate value="${dutyDocument.dd_start_date}" pattern="yyyy/MM/dd"/></td>
			</tr>
			<tr>	
				<th>작성일자<input type="hidden" value="${page}"><input type="hidden" value="${reportType}"><input type="hidden" value="${searchDate}"></th>
				<td><fmt:formatDate value="${dutyDocument.dd_date}" pattern="yyyy/MM/dd"/></td>
				<th>공개여부</th>
				<td>
					<c:choose>
						<c:when test="${dutyDocument.dd_public eq 'y'}">
							&nbsp;&nbsp;<input type="checkbox" checked="checked" disabled="disabled">&nbsp;내용공개
						</c:when>
						<c:otherwise>
							&nbsp;&nbsp;<input type="checkbox" disabled="disabled">&nbsp; 내용공개
						</c:otherwise>
					</c:choose>
			</tr>
			<tr>
				<th>업무일지</th>
				<td>${dutyDocument.dd_select_name}</td>
				<th>보고유형</th>
				<td>${dutyDocument.dd_name}</td>
			</tr>
			<tr>
				<td colspan="4">
					${dutyDocument.dd_content}
				</td>
			</tr>
		</table>
		<div class="insertJoinBtnWrap textCenter">
			<button class="btn btn-default"><a href="/businessSupport/dutyDocument/personalUpdateForm?dd_number=${dutyDocument.dd_number}">수정</a></button>
			<button class="btn btn-default"><a href="/businessSupport/dutyDocument/personal?page=${page}&reportType=${reportType}&searchDate=${searchDate}&val=${val}">취소</a></button>
		</div>
	</form>
</body>
</html>