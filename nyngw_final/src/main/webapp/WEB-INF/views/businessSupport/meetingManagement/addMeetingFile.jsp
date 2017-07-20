<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
   function file_change(file){
   var str=file.lastIndexOf("\\")+1;   //파일 마지막 "\" 루트의 길이 이후부터 글자를 잘라 파일명만 가져온다.
   file = file.substring(str, file.length);
   document.getElementsByName('board_file_name')[0].value=file;
}
</script>

<div>
		<form name="tx_editor_form" style="width: 750px;" id="tx_editor_form" action="meetingFileInsert" method="POST" accept-charset="utf-8">
			<table class="table table-border">
			<tr>
				<th>회의록명</th>
				<td>
					<input type="text" name="md_name">
				</td>
				<th>회의일</th>
				<td>
					<input type="date" name="md_date">
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="md_writer" value="${meetingList3.mem_name }" readonly="readonly"></td>
				<th>회의</th>
				<td><button type=""></button></td>
			</tr>
			<tr>
				<th colspan="4">내용</th>
			</tr>
		</table>
			<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
			<input type="hidden" value="${page}">
			<div><button onclick='saveContent()'>등록</button></div>
			<input type="reset" value="초기화" />	
			<div style="text-align: center;">
				<button type="button"><a href="/businessSupport/meetingManagement/meetingFile?page=${page }">취소</a></button>
			</div>
		</form>
	</div>

		