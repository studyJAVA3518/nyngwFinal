<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function file_change(file){
	var str=file.lastIndexOf("\\")+1;   //파일 마지막 "\" 루트의 길이 이후부터 글자를 잘라 파일명만 가져온다.
	file = file.substring(str, file.length);
	document.getElementsByName('board_file_name')[0].value=file;
}

</script>
	<h1>게시판등록폼</h1>
	<div>
		<form enctype="multipart/form-data" name="tx_editor_form" style="width: 750px;" id="tx_editor_form" action="write" method="post" accept-charset="utf-8">
			<table class="table table-bordered">
				<tr>
					<th>게시판종류</th>
					<td>공지사항</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="board_title"></td>
				</tr>
				<tr>
					<th colspan="2">내용</th>
				</tr>
			</table>	
			<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
			<table class="table table-bordered">
				<tr>
					<th>첨부파일</th>
					<td colspan="3"><input type="file" name="board_file_name" onchange="javascript:file_change(this.value);">
					</td>
						
				</tr>
			</table>
		</form>
		<input type="hidden" value="${page}">
		<button type="button" onclick="saveContent();">등록</button>
		<input type="reset" value="초기화" />	
		<button type="button"><a href="/sharingInformation/board/list?page=${page }">취소</a></button>
	</div>
<textarea id="text_content" style="display:none;">
${board.board_content}
</textarea>
<script>
$(function(){   
	var loadContent = function() {
		/* 저장된 컨텐츠를 불러오기 위한 함수 호출 */
		Editor.modify({
//				"attachments": function () { /* 저장된 첨부가 있을 경우 배열로 넘김, 위의 부분을 수정하고 아래 부분은 수정없이 사용 */
//					var allattachments = [];
//					for (var i in attachments) {
//						allattachments = allattachments.concat(attachments[i]);
//					}
//					return allattachments;
//				}(),
			"content": document.getElementById("text_content") /* 내용 문자열, 주어진 필드(textarea) 엘리먼트 */
		});
	};
	
	loadContent();
});
</script>	