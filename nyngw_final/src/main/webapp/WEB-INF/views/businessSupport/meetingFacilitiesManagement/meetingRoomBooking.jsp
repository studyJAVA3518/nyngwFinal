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

<script type="text/javascript">
	function searchReservation_go(form){
		form.method="get";
		form.action="/businessSupport/meetingFacilitiesManagement/searchReservation";
		form.submit();
	}

</script>
<!--

//-->
</script>
회의실 예약!!!!!!!!!!!!!!
	<div>
		<form action="">
			<table class="table table-bordered">
				<tr><th>날짜</th><td><input type="date"></td></tr>
			</table>
			<button type="button" onclick="searchReservation_go(this.form);">검색</button>
		</form>
	</div>
	<div>
	<!-- bookingListMap.회의실이름.rv_number(rv_time)(rv_mem_number)(rv_mr_number) -->
	<!-- mrList => 회의실 리스트 -->
		<%
			List<MeetingRoomVO> mrList = (List) request.getAttribute("mrList");	//회의실 리트스 로드
			for (int i = 0; i<mrList.size(); i++){%>
		<div class="col-md-4">
			<table class="table">
				<tr><th><%=mrList.get(i).getMr_name()%> </th></tr>	<!-- 각각의 회의실 리스트의 이름 출력 -->
				
				<%List<ReservationVO> RList = (List) request.getAttribute(mrList.get(i).getMr_number());	//각 회의실 별 예약 리스트 (모든 시간의 정보가 들어있는 게 아님)
				if (RList.size()==0){	//예약정보가 없을 시 모든시간 사용가능
					for (int j = 9; j<18; j++){%>
					<tr><td style="background:green;"><%=j %>시 &nbsp;&nbsp; 사용가능</td></tr>
					<%}%>
				<%}else{	//예약정보가 하나라도 있을 시
					int RListIndex = 0;
					for(int j = 9 ; j<18 ;j++){
						if(RList.get(RListIndex).getRv_time().equals(j+"")){%>
							<tr><td style="background:red;"><%=j %>시 &nbsp;&nbsp; 예약중</td></tr>
							<%if(RListIndex+1!=RList.size()){
								RListIndex++;
							}
						}else{%>
							<tr><td style="background:green;"><%=j %>시 &nbsp;&nbsp; 사용가능</td></tr>
						<%}
					}
				}%>
				<tr><td><button><a href="/businessSupport/meetingFacilitiesManagement/reservation">예약하기</a></button></tr>
			</table>
		</div>
		<%}%>
	</div>
