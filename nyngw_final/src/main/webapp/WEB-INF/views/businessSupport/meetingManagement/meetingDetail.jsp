<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2>회의 일정 상세</h2>
<p class="docTitleDescription">
	회의 일정의 상세 정보를 확인할 수 있는 페이지
</p>
<div>
			<table class="table table-border textCenter tableGray">
			<tr>
				<th>회의제목</th>
				<td colspan="3">
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
			<div class="textCenter">
				${meeting.mt_content }
			</div>
			<input type="hidden" value="${page}">
			<div style="text-align: center; margin-top: 30px;" class="insertJoinBtnWrap textCenter">
				<button class="btn btn-default"><a href="/businessSupport/meetingManagement/meetingUpdateForm?mt_number=${meeting.mt_number}&param_mt_date=<fmt:formatDate value="${meeting.mt_date }" pattern="yyyy-MM-dd"/>">수정하기</a></button>
				<button class="btn btn-default"><a href="/businessSupport/meetingManagement/meetingCalendar?page=${page }">목록</a></button>
			</div>
	</div>