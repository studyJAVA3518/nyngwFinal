<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
			<table class="table table-border textCenter">
			<tr>
				<th>회의록명</th>
				<td>
					${meetingFile.md_name }
				</td>
				<th>작성일</th>
					<td>
					<fmt:formatDate value="${meetingFile.md_date}" pattern="yyyy/MM/dd"/>
					</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
						${meetingFile.md_writer }
				</td>
			</tr>
			<tr>
				<th colspan="4">내용</th>
			</tr>
		</table>
			${meetingFile.md_content }
			<input type="hidden" value="${page}">
			<div style="text-align: center;">
			
			<button><a href="/businessSupport/meetingManagement/meetingFileUpdateForm?md_number=${meetingFile.md_number}&param_md_date=<fmt:formatDate value="${meetingFile.md_date }" pattern="yyyy-MM-dd"/>">수정하기</a></button>
			<button><a href="/businessSupport/meetingManagement/meetingFile?page=${page }">목록</a></button>
			</div>
	</div>