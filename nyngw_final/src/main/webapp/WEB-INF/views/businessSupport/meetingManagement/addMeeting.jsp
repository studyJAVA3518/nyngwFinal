<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<form action="">
			<table class="table table-border">
				<tr>
					<th>회의록명</th>
					<td>
						<input type="text">
					</td>
					<th>회의번호</th>
					<td>
						여기에 회의테이블 회의번호 시퀀스
					</td>
				</tr>
				<tr>
					<th>회의장소</th>
					<td>
						<select name="">
							<option value="">병현세미나실</option>
							<option value="">현근세미나실</option>
							<option value="">건우세미나실</option>
						</select>
					</td>
					<th>회의일</th>
					<td>
						<input type="date">
					</td>
				</tr>
				
			</table>
			
			<textarea rows="20" cols="100" style="resize: none;">몰라 여기 에디터</textarea>
				<input type="submit" value="작성 완료"/>
				<button><a href="/businessSupport/meetingManagement/meetingCalendar">취소</a></button>
		</form>
			
		<br>
		<br>
		
		