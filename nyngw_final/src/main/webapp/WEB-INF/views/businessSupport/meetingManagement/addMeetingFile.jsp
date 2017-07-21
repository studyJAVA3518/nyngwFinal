<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*, java.text.*" %>
<%Date date = new Date();
	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
	String strdate = simpleDate.format(date);
%>
<script type="text/javascript">
   function file_change(file){
   var str=file.lastIndexOf("\\")+1;   //파일 마지막 "\" 루트의 길이 이후부터 글자를 잘라 파일명만 가져온다.
   file = file.substring(str, file.length);
   document.getElementsByName('board_file_name')[0].value=file;
}
</script>
<script type="text/javascript">
$(function(){
	$("#submit").click(function(){
	    $('#chk_meet').html("<input type='text' name='md_number' value='"+$("input[name='chk_meet']:radio:checked").val()+"'/>");
	    $('#meetPopup').dialog('close');
	})
	
	 $('#meetPopup').css('display', 'none');
	 $("#meetPopup_go").click(function(){
			$('#meetPopup').dialog({
				width:950,
				height: 700,
				modal: true,
				buttons: {
			       "취소": function() {
						$(this).dialog("close");
					}
				},
				close: function() {
					
				}
			});
		})
})
</script>

<div>
		<form name="tx_editor_form" style="width: 750px;" id="tx_editor_form" action="meetingFileInsert" method="POST" accept-charset="utf-8">
			<table class="table table-border">
			<tr>
				<th>회의록명</th>
				<td>
					<input type="text" name="md_name">
				</td>
				<th>회의</th>
				<td><button id="meetPopup_go">회의찾기</button></td>
				<td id="chk_meet" name="md_number"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="md_writer" value="${meetingList3.mem_name }" readonly="readonly"></td>
				<th>작성일</th>
				<td>
					<input type="date" name="md_date" value="<%= strdate %>" readonly="readonly"/>
				</td>
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

	<div id="meetPopup" style="height:100%;width:100%;">
	<table>
	<tr>
		<th></th>
		<th>회의명</th>
		<th>회의일자</th>
		<th>회의장소</th>
		<th>회의주최자</th>
	</tr>
		<c:choose>
				<c:when test="${a}">
				<c:forEach items="${metlist}" var="board" >
				<tr>
					<td><input type="radio" name="chk_meet" id="chk_meet" value="${board.mt_md_number }"></td>
					<td>${board.mt_title}</td>
					<td><fmt:formatDate value="${board.mt_date}" pattern="yyyy-MM-dd"/></td>
					<td>${board.mt_mr_number }</td>
					<td>${board.mt_reader}</td>
				</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td style="text-align: center;">내용이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
</table>
<button type="button" id="submit">등록</button>
	</div>	