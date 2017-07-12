<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

미팅파일!!!!!!!!!!!!!!!
<div>
		<form action="">
			<table class="table table-border">
				<tr>
					<th>검색기간</th>
					<td>
						<button type="button">당일</button>
						<button type="button">1주일</button>
						<button type="button">1개월</button>
						<button type="button">3개월</button>
					</td>
				</tr>
				<tr>
					<th>검색입력</th>
					<td>
						<select name="">
							<option value="">전체</option>
							<option value="">제목</option>
							<option value="">작성자</option>
						</select>
						<input type="text">
					</td>
				</tr>
			</table>
				<input type="submit" value="검색">
		</form>
		<br>
		<br>
		<table class="table table-border">
			<tr>
				<th>회의록번호</th>
				<th>작성일자</th>
				<th>회의록명</th>
				<th>작성자</th>
			</tr>
			<tr>
				<td>1</td>
				<td>2017.07.12</td>
				<td><a href="/businessSupport/meetingManagement/meetingFile">처음회의록</a></td>
				<td>꼬부기</td>
			</tr>
		</table>
			<button><a href="/businessSupport/meetingManagement/addMeetingFile">글쓰기</button>
	</div>
