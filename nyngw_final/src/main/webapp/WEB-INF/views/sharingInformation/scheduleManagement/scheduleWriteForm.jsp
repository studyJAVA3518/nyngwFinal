<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form>
	<input type="hidden" value="${sc_code_number }" name="sc_code_number">
	<table class="table table-bordered">
		<tr>
			<th>일정제목</th>
			<td><input type="text" name="sc_title"></td>
		</tr>
		<tr>
		    <th>날짜</th>
		    <td><input type="text" class="inputTypeDate" value="" name="sc_date"></td>
		</tr>
		<tr>
		    <th>시간</th>
		    <td><input type="text" value="" name="sc_time"></td>
		</tr>
	</table>
		<tr>
			<th>내용</th>
			<td>
				<textarea name="sc_content" rows="10" cols="100" style="resize: none;"></textarea>
			</td>
		</tr>
	<br>	
	<button type="button" onclick="scheduleWrite_go(this.form);">등록</button>
	<button>취소</button>
</form>

<script>
	function scheduleWrite_go(form){
		form.method="post";
		form.action="/sharingInformation/scheduleManagement/scheduleWrite";
		form.submit();
	}
</script>