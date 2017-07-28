<%@page import="com.nyngw.dto.ScheduleVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="apple-touch-icon" sizes="57x57" href="https://fullcalendar.io/apple-touch-icon-57x57.png">
<link rel="apple-touch-icon" sizes="114x114" href="https://fullcalendar.io/apple-touch-icon-114x114.png">
<link rel="apple-touch-icon" sizes="72x72" href="https://fullcalendar.io/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="144x144" href="https://fullcalendar.io/apple-touch-icon-144x144.png">
<link rel="apple-touch-icon" sizes="60x60" href="https://fullcalendar.io/apple-touch-icon-60x60.png">
<link rel="apple-touch-icon" sizes="120x120" href="https://fullcalendar.io/apple-touch-icon-120x120.png">
<link rel="apple-touch-icon" sizes="76x76" href="https://fullcalendar.io/apple-touch-icon-76x76.png">
<link rel="apple-touch-icon" sizes="152x152" href="https://fullcalendar.io/apple-touch-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="https://fullcalendar.io/apple-touch-icon-180x180.png">
<link rel="icon" type="image/png" href="https://fullcalendar.io/favicon-192x192.png" sizes="192x192">
<link rel="icon" type="image/png" href="https://fullcalendar.io/favicon-160x160.png" sizes="160x160">
<link rel="icon" type="image/png" href="https://fullcalendar.io/favicon-96x96.png" sizes="96x96">
<link rel="icon" type="image/png" href="https://fullcalendar.io/favicon-16x16.png" sizes="16x16">
<link rel="icon" type="image/png" href="https://fullcalendar.io/favicon-32x32.png" sizes="32x32">
<meta name="msapplication-TileColor" content="#2b5797">
<meta name="msapplication-TileImage" content="https://fullcalendar.io/mstile-144x144.png">

<script>

	$(function() {

		var todayDate = moment().startOf('day');
		var YM = todayDate.format('YYYY-MM');
		var YESTERDAY = todayDate.clone().subtract(1, 'day').format('YYYY-MM-DD');
		var TODAY = todayDate.format('YYYY-MM-DD');
		var TOMORROW = todayDate.clone().add(1, 'day').format('YYYY-MM-DD');
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,today,next', //<  오늘 >  
				center: 'title',  //2017년 7월이라는 달력 제목
				right: '' //월, 주, 일, 일정목록
			},
			defaultDate: new Date(), //캘린더 켜지면 화면상에 나오는 날짜
			editable: false, //마우스로 일정을 움직일 수 있음. 사용자는 캘린더를 보기만 하기 때문에 false
			eventLimit: false,//하루에 이벤트가 3개이상이면 more표시로 줄여주는 기능 다 보여줘야하기 때문에 false
			events: [
			    <%	List<ScheduleVO> scheduleList = (List)request.getAttribute("scheduleList");
			    	for(int i=0; i<scheduleList.size();i++){
				  		if(i>0){
				  			out.print(",");
				  		}
				  		ScheduleVO schedule =scheduleList.get(i);%>
						{
							title: '<%=schedule.getSc_title()%>',
							start: '<%=schedule.getSc_date()%>',
							end:'<%=schedule.getSc_date()%>',
							url:'/sharingInformation/scheduleManagement/scheduleDetail?sc_number=<%=schedule.getSc_number()%>'
						}
					<%}%>
			]
		});
	});
	
	
	
	</script>

<style>
#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>



<div id='body' class='section'>
<c:if test="${au == 'ok'}">
<a href="/sharingInformation/scheduleManagement/scheduleWriteForm?sc_code_number=${sc_code_number }"><button class="btn btn-default">일정등록 </button></a>
</c:if>
	<div>
		<div class='two-col'>
			<div class='content'>
				<div id='calendar'></div>
			</div>
		</div>
	</div>
</div>
