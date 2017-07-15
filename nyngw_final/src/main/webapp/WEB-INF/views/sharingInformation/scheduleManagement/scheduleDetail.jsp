<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

	<table class="table">
		<tr>
			<th>제목</th>
			<td>${schedule.sc_title }</td>
		</tr>
		<tr>
			<th>날짜</th>
			<td>${schedule.sc_date}</td>
		</tr>
		<tr>
			<th>시간</th>
			<td>${schedule.sc_time}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${schedule.sc_mem_number}</td>
		</tr>
	</table>
	<textarea rows="10" cols="100" readonly >
${schedule.sc_content}
	</textarea>
	작성자 본인일 때만 수정버튼이 보여야한다.
	열어줄 때 controller에서 member정보 검색해서 담아주고 작성자(schedule.sc_mem_number)랑 비교하자.
	<a href = "/sharingInformation/scheduleManagement/scheduleEditForm?sc_number=${schedule.sc_number }"><button>수정</button></a>
	<a href = "/sharingInformation/scheduleManagement/scheduleDelete?sc_number=${schedule.sc_number }&sc_code_number=${schedule.sc_code_number}"><button>삭제</button></a>