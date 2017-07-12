<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	상세페이지
	<table class="table table-bordered">
		<tr>
			<th >제목</th>
			<td colspan="3">나야나그룹웨어솔루션산출물</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>김병현</td>
			<th>업무시작일</th>
			<td>2017.07.12</td>
		</tr>
		<tr>
			<th>업무일지</th>
			<td>개인일지</td>
			<th>보고유형</th>
			<td>일일일지</td>
		</tr>
		<tr>
			<th>공개여부</th>
			<td>
				&nbsp;&nbsp;<input type="checkbox">&nbsp;내용공개 &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox">&nbsp;&nbsp;&nbsp;의견공개
			</td>
			<th>작성일지</th>
			<td>2017.07.12 19:35:01</td>
		</tr>
		<tr>
			<td colspan="4">
				<textarea rows="20" cols="110" style="resize: none;" readonly="readonly">
				</textarea>
			</td>
		</tr>
	</table>
	<button><a href="/businessSupport/dutyDocument/personalUpdateForm">수정</a></button>
	<button><a href="/businessSupport/dutyDocument/personal">취소</a></button>
</body>
</html>