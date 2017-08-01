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
	<h2>게시글 등록</h2>
<p class="docTitleDescription">
	게시글을 등록할 수 있습니다.
</p>
	<div>
		<form enctype="multipart/form-data" name="tx_editor_form" style="width: 750px;" id="tx_editor_form" action="write" method="post" accept-charset="utf-8">
			<table class="table table-bordered tableGray">
				<tr>
					<th>게시판종류</th>
					<td>게시판</td>
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
			<table class="table table-bordered tableGray">
				<tr>
					<th>첨부파일</th>
					<td colspan="3"><input type="file" name="board_file_name" onchange="javascript:file_change(this.value);">
					</td>
						
				</tr>
			</table>
		</form>
		<div class="insertJoinBtnWrap textCenter">
			<input type="hidden" value="${page}">
			<button type="button" onclick="saveContent();"class="btn btn-default">등록</button>
			<input type="reset" value="초기화" class="btn btn-default"/>	
			<button type="button"class="btn btn-default"><a href="/sharingInformation/board/list?page=${page }">취소</a></button>
		</div>
	</div>
