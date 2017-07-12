<%@page import="java.util.List"%>
<%-- <%@page import="com.test.calender.vo.Event"%> --%>
<%@page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
<title>FullCalendar - JavaScript Event Calendar</title>

<meta name='description'
	content='Open source JavaScript jQuery plugin for a full-sized, drag &amp; drop event calendar'>
<meta name='keywords'
	content='calendar, JavaScript, jQuery, events, drag and drop'>
<meta name='author' content='Adam Shaw'>


<link rel="apple-touch-icon" sizes="57x57"
	href="https://fullcalendar.io/apple-touch-icon-57x57.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="https://fullcalendar.io/apple-touch-icon-114x114.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="https://fullcalendar.io/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="144x144"
	href="https://fullcalendar.io/apple-touch-icon-144x144.png">
<link rel="apple-touch-icon" sizes="60x60"
	href="https://fullcalendar.io/apple-touch-icon-60x60.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="https://fullcalendar.io/apple-touch-icon-120x120.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="https://fullcalendar.io/apple-touch-icon-76x76.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="https://fullcalendar.io/apple-touch-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180"
	href="https://fullcalendar.io/apple-touch-icon-180x180.png">
<link rel="icon" type="image/png"
	href="https://fullcalendar.io/favicon-192x192.png" sizes="192x192">
<link rel="icon" type="image/png"
	href="https://fullcalendar.io/favicon-160x160.png" sizes="160x160">
<link rel="icon" type="image/png"
	href="https://fullcalendar.io/favicon-96x96.png" sizes="96x96">
<link rel="icon" type="image/png"
	href="https://fullcalendar.io/favicon-16x16.png" sizes="16x16">
<link rel="icon" type="image/png"
	href="https://fullcalendar.io/favicon-32x32.png" sizes="32x32">
<meta name="msapplication-TileColor" content="#2b5797">
<meta name="msapplication-TileImage"
	content="https://fullcalendar.io/mstile-144x144.png">

<!-- 	<link href='//fonts.googleapis.com/css?family=Lato:100,400,700' rel='stylesheet' />
 -->
<link href='resources/css/base.css' rel='stylesheet' />
<link rel='stylesheet' href='resources/css/fullcalendar.min.css' />

<script src='<%=request.getContextPath() %>/resources/js/fullCalendar/moment.min.js'></script>
<script src='<%=request.getContextPath() %>/resources/js/fullCalendar/jquery.min.js'></script>
<script src='<%=request.getContextPath() %>/resources/js/fullCalendar/fullcalendar.min.js'></script>
<script src='<%=request.getContextPath() %>/resources/js/fullCalendar/ko.js'></script>


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
				<%
				//Request에 담겨오는 일정 리스트를 설정해준다
				//EL 태그로 하려다 안되서 그냥 스크립틀릿 사용
// 				List<Event> eventList = (List<Event>)request.getAttribute("eventList");
				%>
<%-- 			 	<%for(int i=0; i<eventList.size();i++){ --%>
// 			  		if(i>0) out.print(",");
// 			  		Event event =eventList.get(i);
<%-- 			  	%> --%>
			  	
			    //FullCalendar 자체적으로 {  } 안에 들어갈 수 있는 것들 
			    //title은 일정의 제목이 되고
			    //start, end는 시작, 끝
			    //url은 클릭하면 이동할 페이지 
			    //기타 내장 key 값들은 https://fullcalendar.io/docs/event_data/Event_Object/ 여기서 참고
// 				{
<%-- 					title: '<%=event.getTitle()%>', --%>
<%-- 					start: '<%=event.getStart_date()%>', --%>
<%-- 					end:'<%=event.getEnd_date()%>', --%>
<%-- 					url:'eventDetail?id=<%=event.getId()%>' --%>
// 				}
<%-- 				<%}%> --%>
			]
			//일정에 마우스를 올렸을 때 (hover)
			/* eventMouseover: function(calEvent, jsEvent) {
			    var tooltip = '<div class="tooltipevent" style="width:300px;height:100px;background:#D5D5D5;position:absolute;z-index:10001;">' + calEvent.getContent + '</div>';
			    $("body").append(tooltip);
			    $(this).mouseover(function(e) {
			        $(this).css('z-index', 10000);
			        $('.tooltipevent').fadeIn('500');
			        $('.tooltipevent').fadeTo('10', 1.9);
			    }).mousemove(function(e) {
			        $('.tooltipevent').css('top', e.pageY + 10);
			        $('.tooltipevent').css('left', e.pageX + 20);
			    });
			}, */
			//일정에 마우스를 내렸을 때
			/* eventMouseout: function(calEvent, jsEvent) {
			     $(this).css('z-index', 8);
			     $('.tooltipevent').remove();
			} */
		});
	});
	
	
	
	
	</script>


</head>
<style>
#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
<body id='index-page'>
	<a href = "/sharingInformation/scheduleManagement/scheduleWriteForm"><button>일정등록</button></a>
	<div id='body' class='section'>
		<div>
			<div class='two-col'>
				<div class='content'>
					<div id='calendar'>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>