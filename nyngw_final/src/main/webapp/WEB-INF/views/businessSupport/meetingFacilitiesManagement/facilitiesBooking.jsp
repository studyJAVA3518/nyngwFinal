<%@page import="com.nyngw.dto.ReservationVO"%>
<%@page import="com.nyngw.dto.MeetingRoomVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	fieldset{
		width:225px;
		height:270px;
		margin:5px;
		float: left;
		border:3px; 
	}
</style>
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
	<!-- bookingListMap.회의실이름.rv_number(rv_time)(rv_mem_number)(rv_mr_number) -->
	<!-- mrList => 회의실 리스트 -->
		<%
			List<MeetingRoomVO> mrList = (List) request.getAttribute("mrList");
			for (MeetingRoomVO mrVO : mrList){%>
		<div class="col-md-4">
			<table class="table">
				<tr><th><%=mrVO.getMr_name()%> </th></tr>	<!-- 각각의 회의실 리스트의 이름 출력 -->
						<%List<ReservationVO> RList = (List) request.getAttribute(mrVO.getMr_number());
						for(ReservationVO rVO : RList){
							for(int i = 9; i<16;i++){
								if(rVO.getRv_time().equals(i+"")){%>
									<tr><td style="background-color:red;">예약중</td></tr>
								<%}
							}
						}%>
				<tr><td><button><a href="/businessSupport/meetingFacilitiesManagement/reservation">예약하기</a></button></tr>
			</table>
		</div>
		<%}%>
	</div>
