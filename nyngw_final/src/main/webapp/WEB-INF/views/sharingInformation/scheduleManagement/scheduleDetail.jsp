<%@page import="com.nyngw.dto.ScheduleVO"%>
<%@page import="com.nyngw.dto.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
			<td>${member.mem_name}</td>
		</tr>
	</table>
	<textarea rows="10" cols="100" readonly >
${schedule.sc_content}
	</textarea>
<%	MemberVO member = (MemberVO) request.getAttribute("member");
	ScheduleVO schedule = (ScheduleVO) request.getAttribute("schedule");
	if(member.getMem_number().equals(schedule.getSc_mem_number())){%>
		<a href = "/sharingInformation/scheduleManagement/scheduleEditForm?sc_number=${schedule.sc_number }"><button>수정</button></a>
		<a href = "/sharingInformation/scheduleManagement/scheduleDelete?sc_number=${schedule.sc_number }&sc_code_number=${schedule.sc_code_number}"><button>삭제</button></a>
	<%}%>	