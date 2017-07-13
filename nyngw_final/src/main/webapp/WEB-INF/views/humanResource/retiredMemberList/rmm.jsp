<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


퇴사자 리스트
<form action="" method="post">
	<table>
		<tr>
			<td>
				<select name="list" class="btn btn-default">
					<option value="" selected="selected">부서이름 ㄱㄱㄱㄱ</option>
					<option value=""></option>
				</select>
				<input type="text" name="" />
				<input type="button" value="검색" class="btn btn-default" />
			</td>
		</tr>
	</table>
	<table class="table table-bordered">
		<tr>
			<th>퇴사</th>
			<th>부서</th>
			<th>이름</th>
			<th>직책</th>
			<th>연락처</th>
		</tr>
	</table>
	<input type="button" value="복직" name="" class="btn btn-default" /> <input
		type="button" value="퇴사" name="" class="btn btn-default" />
</form>