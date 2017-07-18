<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
업무지원 >> 업무일지 >> 부서업무조회
	<div>
		<form action="/businessSupport/dutyDocument/department">
			<table class="table table-border">
				<tr>
					<th>검색기간</th>
					<td>
						<select name="searchDate" id="searchDate">
							<option value="today">금일</option>
							<option value="week">1주일</option>
							<option value="month">1개월</option>
							<option value="trimester">3개월</option>
						</select>
					</td>
					<th>보고유형</th>
					<td>
						<select name="reportType" id="reportType">
							<option value="">전체</option>
							<option value="code1">일일일지</option>
							<option value="code2">주간일지</option>
							<option value="code3">월간일지</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>검색입력</th>
					<td colspan="3">
						<select name="titleType">
							<option value="">전체</option>
							<option value="dd_title">제목</option>
							<option value="mem_name">작성자</option>
						</select>
						<input type="text" name="val" value="">
					</td>
				</tr>
			</table>
				<input type="submit" value="검색">
		</form>
		<br>
		<br>
		<table class="table table-border">
			<tr>
				<th>번호</th>
				<th>업무일</th>
				<th>제목</th>
				<th>보고유형</th>
				<th>작성자</th>
				<th>등록일</th>
			</tr>
			<tr>
				<td>1</td>
				<td>2017.07.12</td>
				<td><a href="/businessSupport/dutyDocument/departmentDetail">나야나그룹웨어솔루션산출물</a></td>
				<td>일일일지</td>
				<td>김병현</td>
				<td>2017.07.12</td>
			</tr>
		</table>
	</div>
</body>
</html>