<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*, java.text.*" %>
<%Date date = new Date();
	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
	String strdate = simpleDate.format(date);
%>
<script>
$(function(){   
    var loadContent = function() {
       /* 저장된 컨텐츠를 불러오기 위한 함수 호출 */
       Editor.modify({
//           "attachments": function () { /* 저장된 첨부가 있을 경우 배열로 넘김, 위의 부분을 수정하고 아래 부분은 수정없이 사용 */
//              var allattachments = [];
//              for (var i in attachments) {
//                 allattachments = allattachments.concat(attachments[i]);
//              }
//              return allattachments;
//           }(),
          "content": document.getElementById("text_content") /* 내용 문자열, 주어진 필드(textarea) 엘리먼트 */
       });
    };
    
    loadContent();
 });
</script>


<div>
<form name="tx_editor_form" id="tx_editor_form" action="/businessSupport/meetingManagement/meetingFileUpdate" method="post"  accept-charset="utf-8">
			<table class="table table-border textCenter">
			<input type="hidden" name="mt_number" value="${meetingFile.md_number }">
			<tr>
				<th>회의록명</th>
				<td>
					<input type="text" name="mt_title" value="${meetingFile.md_name }">
				</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>
					<input type="date" name="md_date" value="<%= strdate %>" readonly="readonly">
				</td>
				<th>작성자</th>
				<td><input type="text" name="md_writer" value="${meetingFile.md_writer }" readonly="readonly"/></td>
			</tr>
		</table>
		<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
<!-- 			<table> -->
<!-- 				<tr> -->
<%-- 					<td colspan="6"><textarea rows="15" cols="97" style="resize: none;" name="board_content">${meeting.mt_content }</textarea></td> --%>
<!-- 				</tr> -->
				
<!-- 			</table> -->
			
			<input type="hidden" value="${page}">
			<div style="text-align: center;" class="insertJoinBtnWrap textCenter">
			<button onclick='saveContent()' class="btn btn-default">등록</button>
			<input type="reset" class="btn btn-default" value="초기화" />
			<button class="btn btn-default"><a href="/businessSupport/meetingManagement/meetingFile?page=${page }">취소</a></button>
			</div>
	</form>
			
	</div>
	<textarea id="text_content" style="display:none;">
	${meeting.mt_content}
	</textarea>
