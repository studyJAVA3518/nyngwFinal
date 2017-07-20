<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action ="#function" method="post">
<table class="table table-bordered">
	<tr>
		<th>구분</th>
		<td>급여명세서</td>
	</tr>
	<tr>
		<th>수령 년월</th>
		<td><input type="date" /></td>
	</tr>
	<tr>
		<th>
			<select  class="btn btn-default">
				<option value="" selected="selected">부서선택</option>
				<option value=""></option>
			</select>
		</th>
		<td>
			<input type="text"  placeholder="이름입력" />
			<button class="btn btn-default">검색</button>
		</td>
	</tr>
</table>
</form>
<table class="table table-bordered">
	<tr>
		<th>부서</th>
		<th>직책</th>
		<th>이름</th>
		<th>기본급+직책수당+식대</th>
		<th>기타수당</th>
		<th>4대보험료</th>
		<th>최종금액</th>
	</tr>
	<tr>
		<td>인사부</td>
		<td>대리</td>
		<td>홍길동</td>
		<td>000</td>
		<td>000</td>
		<td>000</td>
		<td>000</td>
	</tr>
</table>