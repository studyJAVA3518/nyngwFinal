<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	공지사항 등록폼
	<div>
		<form action="/sharingInformation/noticeMatter/nmWrite" method="post">
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
				<tr>
					<td colspan="2">
						<textarea rows="15" cols="97" name="board_content" style="resize: none;"></textarea>
					</td>
				</tr>
				<tr>
					<th>파일이름</th>
					<td><input type="text" name="board_file_name"></td>
				</tr>
			</table>
			<input type="hidden" value="${page}">
			<input type="submit" value="등록" />
			<input type="reset" value="초기화" />		
			<button type="button"><a href="/sharingInformation/noticeMatter/nmList?page=${page }">취소</a></button>
		</form>
	</div>
</body>
</html>