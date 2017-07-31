<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h2>회의 일정 수정페이지</h2>
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
<script>
function meetingDelete(mt_number){
	var con_test = confirm("해당 일정을 삭제하시겠습니까?");
	if(con_test==true){
		$.ajax({
            type : "POST",
            url : "/businessSupport/meetingManagement/meetingDelete",
            data : {'mt_number' : mt_number},
            success : function(result){
            	location.href=result.uri;
	        }, 
	        error : function(){
	        },
            dataType : 'json' 
		});
	}
}
</script>

<div>
<form name="tx_editor_form" style="width: 750px;" id="tx_editor_form" action="/businessSupport/meetingManagement/meetingUpdate" method="post"  accept-charset="utf-8">
			<table class="table table-border textCenter tableGray">
			<input type="hidden" name="mt_number" value="${meeting.mt_number }">
			<tr>
				<th>회의제목</th>
				<td>
					<input type="text" class="form-control" name="mt_title" value="${meeting.mt_title }">
				</td>
				<th>회의장소</th>
					<td>
						<select name="mt_mr_number" class="form-control">
						<c:forEach items="${meetingroom }" var="meetingroom">
							<option value="${meetingroom.mr_number }">${meetingroom.mr_name }</option>
						</c:forEach>
						</select>
					</td>
			</tr>
			<tr>
				<th>회의일</th>
				<td>
					<input type="text" class="inputTypeDate form-control" name="param_mt_date" value="${param_mt_date }">
				</td>
				<th>회의주최자</th>
				<td><input type="text" name="mt_reader" class="form-control" value="${meeting.mt_reader }" readonly="readonly"/></td>
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
			<input type="reset" value="초기화" class="btn btn-default"/>
			<button type="button" onclick="meetingDelete('${meeting.mt_number}');" class="btn btn-default">삭제하기</button>
			<button class="btn btn-default"><a href="/businessSupport/meetingManagement/meetingCalendar?page=${page }">취소</a></button>
			</div>
	</form>
			
	</div>
	
	<textarea id="text_content" style="display:none;">
	${meeting.mt_content}
	</textarea>