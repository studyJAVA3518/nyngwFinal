<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

회의 일정!!!!!!!!!!!!!!!
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
				<th>회의번호</th>
				<th>회의일자</th>
				<th>회의록명</th>
				<th>회의장소</th>
				<th>회의 주최자</th>
			</tr>
			<tr>
				<td>1</td>
				<td>2017.07.12</td>
				<td><a href="/businessSupport/meetingManagement/meetingCalendar">회의하자우리</a></td>
				<td>병현세미나실</td>
				<td>꼬부기</td>
			</tr>
		</table>
			<button><a href="/businessSupport/meetingManagement/addMeeting">글쓰기</button>
	</div>
