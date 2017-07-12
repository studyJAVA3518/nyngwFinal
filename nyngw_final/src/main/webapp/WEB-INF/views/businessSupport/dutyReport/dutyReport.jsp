<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
업무지원 >> 업무 보고 상신 >> 업무보고상신<br>
<div>
	<form action="">
		<div>
			<table class="table table-bordered">
				<tr>
					<th>보고유형</th>
					<td>
						<select>
							<option>전체</option>
							<option>일일일지</option>
							<option>주간일지</option>
							<option>월간일지</option>
							<option>기간일지</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>검색입력</th>
					<td><input type="text"></td>
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

</body>
</html>