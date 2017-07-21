<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
			<table class="table table-border">
			<tr>
				<th>회의제목</th>
				<td>
					${meeting.mt_title }
				</td>
			</tr>
			<tr>
				<th>회의장소</th>
					<td>
						${meeting.mr_name }
					</td>
				<th>회의일</th>
				<td>
					<fmt:formatDate value="${meeting.mt_date }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th>회의주최자</th>
				<td>${meeting.mt_reader }</td>
				<th>회의참석자</th>
				<td>${meeting.mt_members }</td>			
			</tr>
			<tr>
				<th colspan="4">내용</th>
			</tr>
		</table>
			${meeting.mt_content }
			<input type="hidden" value="${page}">
			<div style="text-align: center;">
			
			<button><a href="/businessSupport/meetingManagement/meetingUpdateForm?mt_number=${meeting.mt_number}&param_mt_date=<fmt:formatDate value="${meeting.mt_date }" pattern="yyyy-MM-dd"/>">수정하기</a></button>
			<button><a href="/businessSupport/meetingManagement/meetingCalendar?page=${page }">목록</a></button>
			</div>
	</div>