<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
#allDiv{
	width: 100%;
	height: 500px;
}
#allDiv>div{
	width: 100%;
}
#bodyDiv{
	height: 90%;
}
#topDiv{
	height: 10%;
}
#bodyDiv>div{
	border:  1px solid black;
}
</style>
<div id="allDiv">
	<div id="topDiv">
		<button class="btn">자동</button>이 버튼을 누르면 밑에 화면1,2,3이 자주들어간 곳으로 설정됨.
	</div>
	<div id="bodyDiv">
		<div style="width: 100%; height: 50%;">
			<div style="width: 100%;">
				<select>
					<option>대분류목록</option>
				</select>
				<select>
					<option>중분류목록</option>
				</select>
			</div>
			<h1>화면1</h1>
		</div>
		<div style="width: 50%; height: 50%; float: left;">
			<div style="width: 100%;">
				<select>
					<option>대분류목록</option>
				</select>
				<select>
					<option>중분류목록</option>
				</select>
			</div>
			<h1>화면2</h1>
		</div>
		<div style="width: 50%; height: 50%; float: left;">
			<div style="width: 100%;">
				<select>
					<option>대분류목록</option>
				</select>
				<select>
					<option>중분류목록</option>
				</select>
			</div>
			<h1>화면1</h1>
		</div>
	</div>
	<div style="width: 100%;">
		<button class="btn">저장</button>
		<button class="btn">취소</button>
	</div>
</div>