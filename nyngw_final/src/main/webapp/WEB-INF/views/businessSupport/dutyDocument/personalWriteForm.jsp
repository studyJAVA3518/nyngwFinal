<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>개인 / 부서 업무일지 등록페이지</h2>
	<form name="tx_editor_form" style="width: 750px;" id="tx_editor_form"  action="/businessSupport/dutyDocument/personalWrite"  method="post" accept-charset="utf-8">
		<table class="table table-bordered">
			<tr>
				<th>보고유형</th>
				<td>
					<select name="dd_code_number"  class="form-control">
						<option value="code1">일일일지</option>
						<option value="code2">주간일지</option>
						<option value="code3">월간일지</option>
					</select>
				</td>
				<th>업무일</th>
				<td>${writeDate}</td>
				<th>공개여부</th>
				<td>
					&nbsp;&nbsp;<input type="checkbox" name="dd_public" value="y">&nbsp;&nbsp;내용공개
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" name="dd_title" class="form-control"></td>
				<th>업무시작일</th>
				<td><input type="text" class="inputTypeDate form-control" name="start_date"></td>
			</tr>
			<tr>
				<th colspan="6">내용</th>
			</tr>
		</table>
		<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
		<div class="insertJoinBtnWrap textCenter">
			<button class="btn btn-default" type="button" onclick="saveContent();">등록</button>
			<button class="btn btn-default"><a href="/businessSupport/dutyDocument/personal">취소</a></button>
		</div>
	</form>
</body>
</html>