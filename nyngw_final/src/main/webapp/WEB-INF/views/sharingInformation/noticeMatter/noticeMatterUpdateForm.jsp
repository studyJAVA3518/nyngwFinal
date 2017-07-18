<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form name="tx_editor_form" style="width: 750px;" id="tx_editor_form" action="/sharingInformation/noticeMatter/nmUpdate" method="post"  accept-charset="utf-8">
			<table class="table table-bordered">
				<tr>
					<th>게시글번호</th>
					<td>${board.board_number }<input type="hidden" value="${board.board_number}" name="board_number" readonly="readonly"></td>
					<th>게시글구분번호</th>
					<td>공지사항</td>
					<th>작성자</th>
					<td>${board.mem_name}<input type="hidden" name="board_mem_number" value="${board.board_mem_number}" readonly="readonly"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3"><input type="text" name="board_title" value="${board.board_title}"></td>
					<th>작성날자</th>
					<td><fmt:formatDate value="${board.board_date}" pattern="yyyy/MM/dd"/></td>
				</tr>
				<tr>
					<th colspan="6">내용</th>
				</tr>
			</table>
				<jsp:include page="/WEB-INF/views/common/daumOpenEditor/editor.jsp" flush="false"/>
			<table>
				<tr>
					<td colspan="6"><textarea rows="15" cols="97" style="resize: none;" name="board_content">${board.board_content }</textarea></td>
				</tr>
				<tr>
					<th>파일이름</th>
					<td colspan="5"><input type="hidden" name="board_file_name"></td>
				</tr>
			</table>
			<input type="hidden" value="${page}">
			<input type="submit" value="수정" />
			<input type="reset" value="초기화" />
			<button type="button"><a href="/sharingInformation/noticeMatter/nmList?page=${page }">취소</a></button>
		</form>
	</div>
</body>
</html>