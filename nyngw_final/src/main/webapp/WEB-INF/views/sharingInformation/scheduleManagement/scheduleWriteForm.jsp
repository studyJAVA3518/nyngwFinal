<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h2>일정 등록 페이지</h2>
<form>
	<input type="hidden" value="${sc_code_number }" name="sc_code_number">
	<table class="table table-bordered tableGray">
		<tr>
			<th>일정제목</th>
			<td><input type="text" name="sc_title"class="form-control"></td>
		</tr>
		<tr>
		    <th>날짜</th>
		    <td><input type="text" class="inputTypeDate form-control docInputSearch" value="" name="sc_date"></td>
		</tr>
		<tr>
		    <th>시간</th>
		    <td><input type="text"class="form-control docInputSearch" value="" name="sc_time"></td>
		</tr>
		<tr>
			<th colspan="2">내용</th>
		</tr>
		<tr>
			<td colspan="2">
				<textarea name="sc_content" rows="10" cols="100" style="resize: none;"></textarea>
			</td>
		</tr>
	</table>
	<div class="insertJoinBtnWrap textCenter">
		<button type="button" onclick="scheduleWrite_go(this.form);" class="btn btn-default">등록</button>
		<button class="btn btn-default"><a href="/sharingInformation/scheduleManagement/schedule?sc_code_number=${sc_code_number}">취소</a></button>
	</div>
</form>

<script>
	function scheduleWrite_go(form){
		form.method="post";
		form.action="/sharingInformation/scheduleManagement/scheduleWrite";
		form.submit();
	}
</script>