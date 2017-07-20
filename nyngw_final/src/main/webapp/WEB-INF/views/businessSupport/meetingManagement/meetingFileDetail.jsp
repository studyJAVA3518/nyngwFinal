<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
			<table class="table table-border">
			<tr>
				<th>회의록명</th>
				<td>
					${board.md_title }
				</td>
				<th>작성일</th>
					<td>
					<fmt:formatDate value="${board.md_date }" pattern="yyyy-MM-dd"/>
					</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
						${board.md_writer }
				</td>
			</tr>
			<tr>
				<th colspan="4">내용</th>
			</tr>
		</table>
			${meeting.mt_content }
			<input type="hidden" value="${page}">
			<div style="text-align: center;">
			
			<button><a href="/businessSupport/meetingManagement/meetingFileUpdateForm?mt_number=${meeting.mt_number}&param_mt_date=<fmt:formatDate value="${meeting.mt_date }" pattern="yyyy-MM-dd"/>">수정하기</a></button>
			<button><a href="/businessSupport/meetingManagement/meetingFile?page=${page }">목록</a></button>
			</div>
	</div>