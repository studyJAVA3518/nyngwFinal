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
<script>
	$(function(){  
		$('#reportType option[value=${dutyDocument.dd_code_number}]').prop('selected',true);
	});
</script>
<body>
	수정페이지
	<form action="/businessSupport/dutyDocument/personalUpdate">
		<table class="table table-bordered">
			<tr>
				<th >제목<input type="hidden" name="dd_number" value="${dutyDocument.dd_number}"></th>
				<td colspan="3"><input type="text" name="dd_title" value="${dutyDocument.dd_title}"></td>
			</tr>
			<tr>
				<th>업무일지</th>
				<td>
					<select name="reportType" id="reportType">
						<option value="code1">일일일지</option>
						<option value="code2">주간일지</option>
						<option value="code3">월간일지</option>
					</select>
				</td>
				<th>작성일자</th>
				<td><fmt:formatDate value="${dutyDocument.dd_date}" pattern="yyyy/MM/dd"/></td>
			</tr>
			<tr>
				<th>보고유형</th>
				<td>${dutyDocument.dd_name}</td>
				<th>공개여부</th>
				<td>
					<c:choose>
						<c:when test="${dutyDocument.dd_select_name eq '부서일지'}">
							&nbsp;&nbsp;<input type="checkbox" name="dd_public" checked="checked" value="y">&nbsp;내용공개&nbsp;&nbsp;&nbsp;&nbsp;
						</c:when>
						<c:otherwise>
							&nbsp;&nbsp;<input type="checkbox" name="dd_public">&nbsp;내용공개&nbsp;&nbsp;&nbsp;&nbsp; <label>체크시 부서업무 공유 미체크시 개인업무</label>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<textarea rows="20" cols="110" style="resize: none;" name="dd_content">${dutyDocument.dd_content}</textarea>
				</td>
			</tr>
		</table>
		<input type="submit" value="수정">
		<button><a href="/businessSupport/dutyDocument/personal">취소</a></button>
	</form>
</body>
</html>