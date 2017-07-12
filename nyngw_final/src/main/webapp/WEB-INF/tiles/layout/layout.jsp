<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>부트스트랩 템플릿</title>

	<!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/resources/css/simple-sidebar.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
    
    <!-- fontawesome(클래스화 된 아이콘) css inport -->
    <link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
	
	<!-- 풀캘린드-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/fullCalendar/fullcalendar.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/fullCalendar/fullcalendar.min.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/fullCalendar/moment.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/fullCalendar/fullcalendar.min.js"></script>
	
    <!-- 초기화 css import -->
    <link href="<%=request.getContextPath()%>/resources/css/reset.css" rel="stylesheet">
    
    <!-- 프로젝트 메인화면 구성에 대한 css import -->
    <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/businessSupport.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/documentManagement.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/electronicApproval.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/enovironmentSetting.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/error.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/homeMain.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/humanResource.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/login.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/mypage.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/sharingInformation.css" rel="stylesheet">
    
    
    <!-- 프로젝트 메인화면 구성에 대한 js import -->
    <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
    <script>
	    $(function(){
	    	
	    	//사이드바
	    	$("#menu-toggle").click(function(e) {
		        e.preventDefault();
		        $("#wrapper").toggleClass("toggled");
		        $("#menu-toggle").toggleClass("sidebarBtnClick");
		    });
	    	
	    	$('.downMenu').hide();
	    	
	    	$(".headerBottom a").hover(function(){
	    		$('.downMenu').show();
	    	    }, function(){
	    	    $('.downMenu').hide();
	    	});
	    	
	    })
	    
	    $(document).ready(function(){
			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();
			
			var calendar = $('#calender').fullCalendar({
				
				header:{
					left : 'prev,next today',
					cneter : 'title',
					right : 'month,agendaWeek,agendaDay'
				},
				
				selectable: true,
				selectHelper: true,
				select: function(start, end, allDay){
					var title = prompt('일정을 입력하셈.');
					if(title){
						calendar.fullCalendar('renderEvent',{
							title: title,
							start: start,
							end: end,
							allDay: allday
						},
						true
						);
					}
					calendar.fullCalendar('unselect');
				},
				editable: true,
				events:[
				  {
					  title: '01 All Day Event',
					  start: new Date(y, m, 1)
				  },
				  {
					  title: '02 Long Event',
					  start: new Date(y, m, d-5),
					  end: new Date(y, m, d-2)
				  }
				        ]
			})
		})
	    
	</script>
	<style>
		/*side bar*/
		.sidebarBtnClick{
			position : absolute;
			top : 150px;
			right :250px;	
		}
	</style>
  </head>
  <body onload="printClock(); fnOnLoad()">
  	
  	<!-- 화면 전체를 감싸는 div -->
  	<div class="container-fliud" id="wrapper">
  	
  		
  		<!-- side-bar -->
  		<a href="#menu-toggle" class="btn btn-default sidebarBtn" id="menu-toggle">사<br/>이<br/>드<br/>바</a>
  		
  		<div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <div class="clock" id="clock">
                </li>
                <li class="sidebar-brand">
<!--                     <div id="calender"></div> -->
                </li>
                <li>
                    
                </li>
            </ul>
        </div>
  		
  		<!-- 헤더 전체 부분 : 부트스트랩 클래스 이외에 
  		header 클래스를 추가에 헤더에 추가로 들어갈 css를 설정-->
  		<div class="container-fliud header" id="header">
			<tiles:insertAttribute name="header" />
		</div>

		<!-- 컨텐츠 전체 부분 : 부트스트랩 클래스 이외에 
  		content 클래스를 추가에 컨텐츠에 추가로 들어갈 css를 설정 -->
		<div class="container-fliud content">
  			<!-- 컨텐츠 가운데 감싸는 부분 -->
  			<div class="container" id="contents">
  				<!-- 사이드메뉴 -->
  				<div class="col-md-3" id="side">
					<tiles:insertAttribute name="side" />
  				</div>

				<!-- 컨텐트 -->
				<div>
					<div id="content" class="col-md-9">
						<tiles:insertAttribute name="content" />
					</div>
				</div>
			</div>
		</div>		
		
		<!-- 푸터 전체 부분  : 부트스트랩 클래스 이외에 
  		footer 클래스를 추가에 푸터에 추가로 들어갈 css를 설정-->
		<div class="container-fliud footer" id="footer">
			<tiles:insertAttribute name="footer" />
		</div>  		
  	</div>
  </body>
</html>