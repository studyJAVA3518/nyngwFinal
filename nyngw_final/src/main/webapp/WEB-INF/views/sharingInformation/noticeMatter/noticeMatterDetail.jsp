<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div>
		<table class="table table-bordered">
			<tr>
				<th>제목</th>
				<td>${board.board_title}</td>
				<th>작성자</th>
				<td>${board.mem_name}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${board.board_date}" pattern="yyyy/MM/dd"/></td>
				<th>조회수</th>
				<td>${board.board_count }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<textarea rows="20" cols="71" style="resize: none;" readonly="readonly">${board.board_content }</textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">${board.board_file_name }</td>
			</tr>
		</table>
			<button><a href="/sharingInformation/board/updateForm?board_number=${board.board_number}">수정하기</a></button>
			<button type="button" onclick="boardDelete('${board.board_number}');">삭제하기</button>
			<button><a href="/sharingInformation/noticeMatter/nmList?page=${page }">목록</a></button>
	</div>
</body>
</html>