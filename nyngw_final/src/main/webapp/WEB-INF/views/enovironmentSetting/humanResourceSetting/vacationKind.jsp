<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- 환경설정관리 -> 인사부 설정 -> 휴가 종류 설정
에 대한 화면 -->

<h2>휴가 종류 설정</h2>
<table class="table">
	<form method="post" class="form-inline">
		<tr>
			<div class="form-group">
				<th>휴가명</th>
				<th>차감설정</th>
			</div>
		</tr>
		<tr>
			<div class="form-group">
				<td>정기휴가계</td>
				<td>
					<select name="regularVacation">
						<option value="0" class="deductVacation">휴가 차감함</option>
						<option value="1">휴가 차감 안함</option>
					</select>
				</td>
			</div>
		</tr>
		<tr>
			<div class="form-group">
				<td>결근(병가)계</td>
				<td>
					<select name="sickVacation">
						<option value="0">휴가 차감함</option>
						<option value="1">휴가 차감 안함</option>
					</select>
				</td>
			</div>
		</tr>
		<tr>	
			<div class="form-group">
				<td>보건휴가계</td>
				<td>
					<select name="healthVacation">
						<option value="0">휴가 차감함</option>
						<option value="1">휴가 차감 안함</option>
					</select>
				</td>
			</div>
		</tr>
		<tr>
			<div class="form-group">
				<td>조퇴계</td>
				<td>
					<select name="earlyVacation">
						<option value="0">휴가 차감함</option>
						<option value="1">휴가 차감 안함</option>
					</select>
				</td>
			</div>
		</tr>
		<tr>
			<div class="form-group">
				<td>경조사휴가계</td>
				<td>
					<select name="holidayVacation">
						<option value="0">휴가 차감함</option>
						<option value="1">휴가 차감 안함</option>
					</select>
				</td>
			</div>
		</tr>
		<tr>
			<div class="form-group">
				<td>교육/훈련계</td>
				<td>
					<select name="trainingVacation">
						<option value="0">휴가 차감함</option>
						<option value="1">휴가 차감 안함</option>
					</select>
				</td>
			</div>
		</tr>
		<tr>
			<div class="form-group">
				<td>출산휴가계</td>
				<td>
					<select name="maternityVacation">
						<option value="0">휴가 차감함</option>
						<option value="1">휴가 차감 안함</option>
					</select>
				</td>
			</div>
		</tr>
		<tr>
			<div class="form-group">
				<td>사유서계</td>
				<td>
					<select name="reasonVacation">
						<option value="0">휴가 차감함</option>
						<option value="1">휴가 차감 안함</option>
					</select>
				</td>
			</div>
		</tr>
		<tr>
			<div class="form-group">
				<td>연차</td>
				<td>
					<select name="yearlyVacation">
						<option value="0">휴가 차감함</option>
						<option value="1">휴가 차감 안함</option>
					</select>
				</td>
			</div>
		</tr>
		<tr>
			<div class="form-group">
				<td>월차</td>
				<td>
					<select name="monthlyVacation">
						<option value="0">휴가 차감함</option>
						<option value="1">휴가 차감 안함</option>
					</select>
				</td>
			</div>
		</tr>
		<tr>
			<div class="form-group">
				<td>기타휴가계</td>
				<td>
					<select name="ectVacation">
						<option value="0">휴가 차감함</option>
						<option value="1">휴가 차감 안함</option>
					</select>
				</td>
			</div>
		</tr>
		<tr>
			<div class="form-group">
				<td colspan="2">
					<input type="button" class="btn btn-default" onclick="vacationDeduction_go();" value="설정"/>
				</td>
			</div>
		</tr>
	</form>
</table>

<script>
	function vacationDeduction_go(){
		alert("휴가종류 조정~!!!");
	}
</script>