<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- 환경설정관리 -> 기획홍보부 설정 -> 급여 정책 설정
에 대한 화면 -->

<h2>급여정책 설정</h2>

<table class="table">
	<form class="form-inline" action="paySetting_go();" method="post">
		<tr>
			<th></th>
			<th>기본급</th>
			<th>수당</th>
			<th>식대</th>
		</tr>
		<tr>
			<th>사원</th>
			<td>1300000</td>
			<td>200000</td>
			<td>100000</td>
		</tr>
		<tr>
			<th>주임</th>
			<td>1500000</td>
			<td>200000</td>
			<td>100000</td>
		</tr>
		<tr>
			<th>대리</th>
			<td>1700000</td>
			<td>300000</td>
			<td>100000</td>
		</tr>
		<tr>
			<td colspan="4"><input type="button" value="등록" onclick="paySetting_go();" class="btn btn-default"/></td>
		</tr>
	</form>
</table>

<h2>퇴직금정책 설정</h2>

<table class="table">
	<form class="form-inline" action="paySetting_go();" method="post">
		<tr>
			<th>퇴직금 설정</th>
			
		</tr>
		<tr>
			<td>퇴직금은 근속연수가 1년 이상(365일)인 경우 계산되며 직전 3개월치의 급여를 일수로 나누어 30일을 곱하여 계산됩니다.</td>
		</tr>
	</form>
</table>

<script>
	function paySetting_go(){
		alert("페이정책 고!!");
	}
</script>