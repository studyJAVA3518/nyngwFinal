<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action ="#function" method="post">
<table class="table table-bordered">
	<tr><th>구분</th><td>급여명세서</td></tr>
	<tr><th>수령 년월</th><td><input type="date" /></td></tr>
	<tr><th><select  class="btn btn-default">
					<option value="" selected="selected">부서선택</option>
					<option value=""></option>
				</select></th><td><input type="text"  placeholder="이름입력" /><button class="btn btn-default">검색</button></td></tr>
</table>
</form>
<table class="table table-bordered">
	<tr><th>부서</th><th>이름</th><th>직책</th><th>기본급</th><th>상여금</th><th>보너스</th></tr>
</table>