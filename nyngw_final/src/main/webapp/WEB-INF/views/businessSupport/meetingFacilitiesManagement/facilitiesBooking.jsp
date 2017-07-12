<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
	fieldset{
		width:225px;
		height:270px;
		margin:5px;
		float: left;
		border:3px; 
	}
</style>
<body>
회의실 예약!!!!!!!!!!!!!!
	<div>
		<form action="">
			<table class="table table-bordered">
				<tr><th>날짜</th><td><input type="date"></td></tr>
			</table>
		</form>
		<input type="button" value="검색">
	</div>
	<div>
		<div>
			<fieldset>
				<table class="table">
					<tr><th>제1회의실</th></tr>
					<tr><td></td></tr>
					<tr><td></td></tr>
					<tr><td><input type="datetime-local"><button><a href="/businessSupport/meetingFacilitiesManagement/reservation">예약하기</a></button></td></tr>
				</table>
			</fieldset>
		</div>
		<div>
			<fieldset>
				<table class="table">
					<tr><th>제2회의실</th></tr>
					<tr><td></td></tr>
					<tr><td></td></tr>
					<tr><td><input type="datetime-local"><button><a href="/businessSupport/meetingFacilitiesManagement/reservation">예약하기</a></button></td></tr>
				</table>
			</fieldset>
		</div>
		<div>
			<fieldset>
				<table class="table">
					<tr><th>제3회의실</th></tr>
					<tr><td></td></tr>
					<tr><td></td></tr>
					<tr><td><input type="datetime-local"><button><a href="/businessSupport/meetingFacilitiesManagement/reservation">예약하기</a></button></td></tr>
				</table>
			</fieldset>
		</div>
	</div>
</body>
</html>