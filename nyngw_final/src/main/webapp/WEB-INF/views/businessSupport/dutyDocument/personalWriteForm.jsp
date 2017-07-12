<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	글쓰기페이지
	<table class="table table-bordered">
		<tr>
			<th>업무일지종류</th>
			<td>
				<select name="">
					<option value="">내업무일지</option>
					<option value="">부서장요청일지</option>
					<option value="">부서일지</option>
					<option value="">프로젝트일지</option>
				</select>
			</td>
			<th>보고유형</th>
			<td>
				<select name="">
					<option value="">일일일지</option>
					<option value="">주간일지</option>
					<option value="">월간일지</option>
					<option value="">기타일지</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>업무일</th>
			<td colspan="3"><input type="text"> ~ <input type="text"></td>
		</tr>
		<tr>
			<th>공개여부</th>
			<td colspan="3">
				&nbsp;&nbsp;<input type="checkbox">&nbsp;&nbsp;내용공개
				&nbsp;&nbsp;<input type="checkbox">&nbsp;&nbsp;의견공개
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3"><input type="text"></td>
		</tr>
		<tr>
			<td colspan="4">
				<textarea rows="10" cols="99"  style="resize: none;"></textarea>
			</td>
		</tr>
	</table>
	<button><a href="/businessSupport/dutyDocument/personal">저장</a></button>
	<button><a href="/businessSupport/dutyDocument/personal">취소</a></button>
</body>
</html>