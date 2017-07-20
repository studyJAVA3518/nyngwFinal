<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
업무지원 >> 업무 보고 상신 >> 내 업무보고<br>
<div>
	<form action="">
		<div>
			<table class="table table-bordered">
				<tr>
					<th>검색기간</th>
					<td>
						<select name="searchDate" id="searchDate">
							<option value="today">금일</option>
							<option value="week">1주일</option>
							<option value="month">1개월</option>
							<option value="trimester">3개월</option>
						</select>
					</td>
					<th>보고유형</th>
					<td>
						<select name="reportType" id="reportType">
							<option>전체</option>
							<option value="code1">일일보고</option>
							<option value="code2">주간보고</option>
							<option value="code3">월간보고</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>검색입력</th>
					<td colspan="3">
						<select name="titleType" id="titleType">
							<option value="">전체</option>
							<option value="dr_title">제목</option>
							<option value="dr_content">작성자</option>
						</select>
						<input type="text" name="val" value="${select.val}">
					</td>
				</tr>
			</table>
		</div>
		<div style="text-align:center;"><input type="submit" value="검색" class="btn"></div>
	</form>
</div><br><br>
<div>
	<div>
		<div>
			<form action="">
				<table class="table table-bordered">
					<tr>
						<th><input type="checkbox" name="check_all" value="all"></th>
						<th>번호</th>
						<th>보고일</th>
						<th>제목</th>
						<th>보고유형</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
					<tr>
						<td><input type="checkbox" name="check_one" value="one"></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<div>
			<input type="button" value="선택삭제">
		</div>
	</div>
	<div style="text-align:right;"><button><a href="/businessSupport/dutyReport/dutyReportWrite">글쓰기</a></button></div>
</div>
