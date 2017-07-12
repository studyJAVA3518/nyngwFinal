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
	<h1>공지사항</h1>
	<form action="/sharingInformation/board/list">
		<select name="index">
				<option value="board_mem_number" selected="selected">작성자</option>
				<option value="board_title">제목</option>
		</select>
		<input type="text" name="val">
		<input type="submit" value="검색">
		<button type="button"><a href="/sharingInformation/noticeMatter/nmWriteForm">등록</a></button>
	</form>
	<table class="table table-bordered">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>등록일</th>
			<th>작성자</th>
			<th>기타</th>
		</tr>
		<tr>
			<td>번호~~!@@@</td>
			<td><a href="/sharingInformation/noticeMatter/nmDetail">$111</a></td>
			<td>날짜~~~</td>
			<td>작성자~</td>
			<td>
				<a href="/sharingInformation/noticeMatter/nmUpdateForm">삭제&nbsp;/</a> 
				<a href="/sharingInformation/noticeMatter/nmUpdateForm">&nbsp;수정</a>
			</td>
		</tr>
	</table>
</body>
</html>