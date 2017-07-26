<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<form>
	<input type="hidden" value="${schedule.sc_number }" name="sc_number">
	<table class="table">
		<tr>
			<td>제목</td>
			<td><input type="text" value="${schedule.sc_title }" name="sc_title"></td>
		</tr>
		<tr>
			<td>날짜</td>
			<td><input type="text" class="inputTypeDate" value="${schedule.sc_date }" name="sc_date"></td>
		</tr>
		<tr>
			<td>시간</td>
			<td><input type="text" value="${schedule.sc_time }" name="sc_time"></td>
		</tr>
	</table>
	<textarea rows="10" cols="100" name="sc_content">${schedule.sc_content}</textarea>
	<button type="button" onclick="scheduleEdit_go(this.form)">확인</button>
	<button type="button" onclick="scheduleEdit_go(this.form)">취소</button>
</form>

<script>
	function scheduleEdit_go(form){
		form.method="post";
		form.action="/sharingInformation/scheduleManagement/scheduleEdit";
		form.submit();
	}
	
</script>