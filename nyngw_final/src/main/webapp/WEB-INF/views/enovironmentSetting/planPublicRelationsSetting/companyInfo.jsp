<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- 환경설정관리 -> 기획홍보부 설정 -> 회사정보 설정
에 대한 화면 -->

<h2>회사 정보 설정</h2>
<h4>회사 로고 등록 및 변경</h4>
<table class="table">
	<form class="form-inline">
		<tr>
			<td colspan="2">
				<label for="basic">기본 이미지 사용</label>
				<input type="radio" name="logo" value="basic" id="basic"/> 
				<label for="custom">업로드 로고(CI) 사용</label>
				<input type="radio" name="logo" value="custom" id="basic"/> 
				<span class="logoDesc">※ 업로드 로고(CI)를 사용할 경우 업로드할 로고화면의 배경부분을 투명하게 처리해야 합니다.</span>
			<td>
		</tr>
		<tr>
			<th>로고(CI) 변경</th>
			<td><input type="file" value="첨부하기" id="logoFile"/>
				<span class="logoDesc">(최적 사이즈 220*80)</span>
			</td>
		</tr>
		<tr>
			<th>미리보기</th>
			<td>들어갈것인가???...생각해보자</td>
		</tr>
	</form>
	
	<h4>회사 정보 등록</h4>
	<table class="table">
		<form class="form-inline">
			<tr>
				<th>회사 이름</th>
				<td>
					<div class="form-group">
						<input type="text"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>회사 전화번호</th>
				<td>
					<div class="form-group">
						<input type="text"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>회사 주소</th>
				<td>
					<div class="form-group">
						<input type="text"/>
					</div>
				</td>
			</tr>
		</form>
	</table>
</table>
