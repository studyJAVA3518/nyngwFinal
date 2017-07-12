<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- 환경설정관리 -> 기획홍보부 설정 ->회사 직급 설정
에 대한 화면 -->

<h2>회사 직급 설정</h2>
<p>
	회사의 직급을 등록하거나 수정하실 수 있으며, 위 아래 버튼을 눌러 위치를 변경하실 수 있습니다.
	ex) 부장, 과장, 대리, 사원 등
</p>
<div class="row">
	<h4>직급 만들기</h4>
	<div class="col-md-12">
		<table class="table">
			<form class="inline-form">
				<tr>
					<th>직급명</th>
					<td>
						<input type="text" id="position_name" name="position_name">
						<input type="submit" value="직급 등록" action="insertPosition_go();"/>
					</td>
				</tr>
			</form>
		</table>
	</div>
</div>

<div class="row">
	<h4>직급 만들기</h4>
	<div class="col-md-12">
		<table class="table">
			<form class="inline-form">
				<tr>
					<th>No</th>
					<th>직급</th>
					<th>위치변경</th>
					<th>관리</th>
				</tr>
				
				<tr>
					<td>1</td>
					<td>사장</td>
					<td>
						<input type="submit" value="▲위로" class="btn btn-default" action="upPoNum_go();"/> 
						<input type="submit" value="▲아래로" class="btn btn-default" action="downPoNum_go();"/> 
					</td>
					<td>
						<input type="submit" value="직급 등록" action="updatePosition_go();"/>
						<input type="submit" value="직급 등록" action="deletePosition_go();"/>
					</td>
				</tr>
				<tr>
					<td>2</td>
					<td>부사장</td>
					<td>
						<input type="submit" value="▲위로" class="btn btn-default" action="upPoNum_go();"/> 
						<input type="submit" value="▲아래로" class="btn btn-default" action="downPoNum_go();"/> 
					</td>
					<td>
						<input type="submit" value="직급 등록" action="updatePosition_go();"/>
						<input type="submit" value="직급 등록" action="deletePosition_go();"/>
					</td>
				</tr>
			</form>
		</table>
	</div>
</div>