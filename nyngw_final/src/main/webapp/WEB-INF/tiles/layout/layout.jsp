<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@page import="com.nyngw.sharingInformation.scheduleManagement.service.ScheduleManagementServiceImpl"%>
<%@page import="com.nyngw.dto.ScheduleVO"%>
<%@page import="java.util.List"%>
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
	<title>GroupWare Solution - NYN GroupWare</title>
	
	<!-- jQuery -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- jQuery monthPicker -->
	<script src="<%=request.getContextPath() %>/resources/js/MonthPicker.min.js"></script>
	<link href="<%=request.getContextPath()%>/resources/css/MonthPicker.min.css" rel="stylesheet">
	<script src="https://cdn.rawgit.com/digitalBush/jquery.maskedinput/1.4.1/dist/jquery.maskedinput.min.js"></script>
	
	<!-- Bootstrap Core CSS -->
	<link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
	<!-- Custom CSS -->
	<link href="<%=request.getContextPath()%>/resources/css/simple-sidebar.css" rel="stylesheet">
	<!-- jquery.form.js - ajaxSubmit() 사용 -->
	<script type='text/javascript' src='http://malsup.github.com/jquery.form.js'></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
	
	<!-- fontawesome(클래스화 된 아이콘) css inport -->
	<link href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css" rel="stylesheet">
   
	<!-- 풀캘린드-->
	<script src='<%=request.getContextPath() %>/resources/js/fullCalendar/moment.min.js'></script>
	<script src='<%=request.getContextPath() %>/resources/js/fullCalendar/fullcalendar.min.js'></script>
	<script src='<%=request.getContextPath() %>/resources/js/fullCalendar/ko.js'></script>
	<!-- 캘린더 css -->
	<link href='<%=request.getContextPath() %>/resources/css/base.css' rel='stylesheet' />
	<link rel='stylesheet' href='<%=request.getContextPath() %>/resources/css/fullcalendar.min.css' />
   
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
    
    <!-- 다음에디터를 쓰기위한 링크 -->
	<link rel="stylesheet" href="<c:url value="/resources/daumOpenEditor/css/editor.css"/>" type="text/css" charset="utf-8"/>
	<script src="<c:url value="/resources/daumOpenEditor/js/editor_loader.js"/>" type="text/javascript" charset="utf-8"></script>
   
    <!-- 조직도 트리를 위한 js -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/tree/zooTree.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/tree/zooTree.css">
    
    <!-- 프로젝트 메인화면 구성에 대한 js import -->
    <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
    
    <script>
	$(function(){
	        
		$('#codeDialog').css('display', 'none');
		$('#memoDialog').css('display', 'none');
	        
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
	        
		$.ajax({
			url : '/memo/list',
			type : 'post',
			data : "",
			success : function(res) {
				if(res.su=="ok"){
					var list= res.list;
					var code="";
					$.each(list, function( index, list ) {
						code += "<tr style='border-bottom:1px solid #99a3b6;'><td><a href='#' onclick=modifyMemo('"+list.memo_number+"');>"+list.memo_title+"</a></td><td style='width:25px'><a href='#' onclick=deleteMemo('"+list.memo_number+"');><i class='fa fa-trash' aria-hidden='true'></i></a></td></tr>";
					});
		          	code+="<tr><td style='color:red;'>"+res.page.currentPageNo+"</td><td><a href='#' onclick=paging('"+res.page.currentPageNo+"');>next&nbsp;&nbsp;&nbsp;</a></td></tr>";
					$('#memo').html(code);
				}
			},
			dataType : 'json'
		})
	})
       
    function paging(page){
		page=parseInt(page, 10)+1;
		$.ajax({
			url : '/memo/list',
			type : 'post',
			data : {"page":page},
			success : function(res) {
				if(res.su=="ok"){
					var list= res.list;
					var code="";
					$.each(list, function( index, list ) {
						code += "<tr style='border-bottom:1px solid #99a3b6;'><td><a href='#' onclick=modifyMemo('"+list.memo_number+"');>"+list.memo_title+"</a></td><td style='width:25px'><a href='#' onclick=deleteMemo('"+list.memo_number+"');><i class='fa fa-trash' aria-hidden='true'></i></a></td></tr>";
					});
				    code+="<tr><td style='color:red;'>"+res.page.currentPageNo+"</td><td><a href='#' onclick=paging('"+res.page.currentPageNo+"');>next&nbsp;&nbsp;&nbsp;</a></td></tr>";
					$('#memo').html(code);
				}
			} ,
		    dataType : 'json'
		})	   
    }
       
	$(document).ready(function() {

		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();

		$('#calendarmini').fullCalendar({
		    theme: true,
		    header: {
		        left: 'title',
		        center:'prev, today, next',   
		        right:''
		    },
		    editable: true,
		    dayClick :function(date,allDay,jsEvent,view){
		         var yy=date.format("YYYY");
		         var mm=date.format("MM");
		         var dd=date.format("DD");
		         var ss=date.format("dd");
		     
		         day=yy+"-"+mm+"-"+dd;
		         
			$.ajax({
				url : '/sharingInformation/scheduleManagement/side',
				type : 'post',
				data : {"date":day},
				success : function(res) {
			        if(res.su=="ok"){
			           
						var code="<tr><th>번호</th><th>제목</th><th>시간</th></tr>";
						var list= res.sc;
			                
						$.each(list, function( index, list ) {
							code+="<tr onclick='location.href='/sharingInformation/scheduleManagement/scheduleDetail?sc_number="+list.sc_number+"'>"
							+"<td>"+(index+1)+"</td><td>"+list.sc_title+"</td>"
							+"<td>"+list.sc_time+"</td></tr>";
						});
			               
						$('#schduleList').html(code);
						$('#codeDialog').dialog({
							width: 500,
							height: 300,
							modal: true,
							buttons: {
								"취소": function() {
									$(this).dialog("close");
								}
							},
							close: function() {
							                     
							}
						});
			        }else{
			           if(confirm(day+" -> 일정을 등록하시겠습니까?")){
			              location.href="/sharingInformation/scheduleManagement/scheduleWriteForm?sc_code_number=code4";
			           }
			        }
				},
		     	error : function() {
		
		     	},dataType : 'json'
		     })
		    },
		    // add event name to title attribute on mouseover
		    eventMouseover: function(event, jsEvent, view) {
		        if (view.name !== 'agendaDay') {
		            $(jsEvent.target).attr('title', event.title);
		        }
		    }
		});

	});
       
	function addMemo(){
		$('#memoDialog').dialog({
	        width: 400,
	        height: 400,
	        modal: true,
	        buttons: {
				"저장" :function(){
					var metadata = $("#memoform").serialize();
					$.ajax({
						url : '/memo/add',
						type : 'post',
						data : metadata,
						success : function(res) {
	       	            	if(res.su=="ok"){
	       	               
	       	            		alert("등록")
								var list= res.list;
								var code="";
								$.each(list, function( index, list ) {
									code += "<tr style='border-bottom:1px solid #99a3b6;'><td><a href='#' onclick=modifyMemo('"+list.memo_number+"');>"+list.memo_title+"</a></td><td style='width:25px'><a href='#' onclick=deleteMemo('"+list.memo_number+"');><i class='fa fa-trash' aria-hidden='true'></i></a></td></tr>";
								});
								code+="<tr><td style='color:red;'>"+res.page.currentPageNo+"</td><td><a href='#' onclick=paging('"+res.page.nextPageNo+"');>next&nbsp;&nbsp;&nbsp;</a></td></tr>";
								$('#memo').html(code);
							}
						}
					})
	       	   		$(this).dialog("close");
				},
				"취소": function() {
					$(this).dialog("close");
				}
			},
	        close: function() {
				$('#memo_title').val("");           
				$('#memo_content').val("");           
			}
		});
	}
	function deleteMemo(memo_number){
		if(confirm("정말로 삭제하시겠습니까?")){
			$.ajax({
				url : '/memo/delete',
				type : 'post',
				data : {"memo_number":memo_number},
				success : function(res) {
					if(res.su=="ok"){
						alert("삭제")
						var list= res.list;
						var code="";
						$.each(list, function( index, list ) {
							code += "<tr><td><a href='#' onclick=modifyMemo('"+list.memo_number+"');>"+list.memo_title+"</a></td><td style='width:25px'><a href='#' onclick=deleteMemo('"+list.memo_number+"');><i class='fa fa-trash' aria-hidden='true'></i></a></td></tr>";
						});
		                code+="<tr><td style='color:red;'>"+res.page.currentPageNo+"</td><td><a href='#' onclick=paging('"+res.page.nextPageNo+"');>next&nbsp;&nbsp;&nbsp;</a></td></tr>";
						$('#memo').html(code);
					}
				}
			})
		}
	}
	function modifyMemo(memo_number){
		$.ajax({
			url : '/memo/modifyform',
			type : 'post',
			data : {"memo_number":memo_number},
			success : function(res) {
				$('#memo_title').val(res.memo.memo_title);
				$('#memo_content').val(res.memo.memo_content);
				$('#memo_number').val(res.memo.memo_number);
	            $('#memoDialog').dialog({
					width: 400,
					height: 400,
					modal: true,
					buttons: {
						"수정" :function(){
							var metadata = $("#memoform").serialize();
							$.ajax({
								url : '/memo/add',
								type : 'post',
								data : metadata,
								success : function(res) {
									if(res.su=="ok"){
										alert("등록")
									    var list= res.list;
										var code="";
									$.each(list, function( index, list ) {
										code += "<tr><td><a href='#' onclick=modifyMemo('"+list.memo_number+"');>"+list.memo_title+"</a></td><td style='width:25px'><a href='#' onclick=deleteMemo('"+list.memo_number+"');><i class='fa fa-trash' aria-hidden='true'></i></a></td></tr>";
									});
									code+="<tr><td style='color:red;'>"+res.page.currentPageNo+"</td><td><a href='#' onclick=paging('"+res.page.nextPageNo+"');>next&nbsp;&nbsp;&nbsp;</a></td></tr>";
										$('#memo').html(code);
									}
								}
							})
							$(this).dialog("close");
						},
						"취소": function() {
							$(this).dialog("close");
						}
					},
					close: function() {
	                                
					}
				});
	        }
		})
	}
       
</script>
<style>
/*side bar*/
.sidebarBtnClick {
   right: 250px;
}

#calendarmini {
    width: 250px;
    margin: 0 0;
    color:white;
}
#calendarmini .fc-header-title h2 {
    font-size: 0.3em;
    white-space: normal !important;
}
#calendarmini .fc-day-grid fc-unselectable{
   height:250px;
}
#calendarmini .fc-scroller fc-day-grid-container{
   height:300px;
}
#calendarmini .fc-day-top fc-sun fc-other-month fc-past{
   height:23px;
}
#calendarmini .fc-day-number{
   height:23px;
}
#calendarmini .fc-basic-view .fc-body .fc-row {
    min-height: 0.8em;
}
#calendarmini .fc-bg{
   height:23px;
}
#calendarmini .fc-view fc-month-view fc-basic-view{
   line-height: 300px;
}
#calendarmini .fc-day ui-widget-content fc-sun fc-other-month fc-past{
   padding: 0;
}
#calendarmini .fc-day-header ui-widget-header fc-sun{
   height :100px;
}
#calendarmini .fc-content-skeleton{
   padding : 0;
}
#caledarmini .fc-day-header ui-widget-heade{
   height:20px;
}
.fc-left {
	text-align : center;
}
.fc-toolbar.fc-header-toolbar {
	margin : 0 auto 1em;
	padding : 10px;
}
.fc-toolbar h2 {
	text-align : center;
	margin :5px 0;
	display : block;
	margin : 0 auto;
	font-size : 20px;
}
.fc-view-container {
	padding : 5px;
	opacity : 0.9;
	
}
.fc-basic-view {
	border-radius : 5px;
}
.sideTitle {
	color : #fff;
	margin : 15px auto 5px;
}

#memoform h4{
	margin : 12px 0;
}
#memoform input {
	display : inline-block;
	width : 300px;
}
#memoform textarea {
	resize: none;
	wrap:hard;
}
#memoform p {
	margin : 10px 0;
}
</style>

</head>
<body onload="printClock();">

	<!-- 화면 전체를 감싸는 div -->
	<div class="container-fliud" id="wrapper">
        
		<!-- side-bar -->
		<a href="#menu-toggle" class="sidebarBtn" id="menu-toggle">사<br/>이<br/>드<br/>바</a>
		
		<div id="sidebar-wrapper">
		    <ul class="sidebar-nav">
		        <li class="sidebar-brand">
		            <div class="clock" id="clock"></div>
		        </li>
				<li>
					<label class="sideTitle">${memberName}님의 일정</label>
				</li>
				<li>
				   <div id='calendarmini'></div>
				</li>
				<li>
				   <div id='todayschdule'></div>
				</li>
				<li>
					<label class="sideTitle"><i class="fa fa-smile-o" aria-hidden="true"></i> ${memberName}님의 메모</label>
				</li>
				<li style="box-sizing: border-box;">
					<table id='memo' class="table"></table>
				</li>
				<li><a href="#" onclick="addMemo();"><i class="fa fa-plus-circle" aria-hidden="true"></i> 메모추가</a></li>
			</ul>
		</div>
	        
		<!-- 헤더 전체 부분 : 부트스트랩 클래스 이외에 header 클래스를 추가에 헤더에 추가로 들어갈 css를 설정-->
		<div class="container-fliud header navbar navbar-fixed-top" id="header">
			<tiles:insertAttribute name="header" />
		</div>
	
		<!-- 컨텐츠 전체 부분 : 부트스트랩 클래스 이외에 content 클래스를 추가에 컨텐츠에 추가로 들어갈 css를 설정 -->
		<div class="container-fliud content">
			<!-- 컨텐츠 가운데 감싸는 부분 -->
			<div class="container" id="contents">
				<!-- 사이드메뉴 -->
				<div class="col-md-3" id="side">
					<tiles:insertAttribute name="side" />
				</div>
		
				<!-- 컨텐트 -->
				<div>
					<div id="contentWrap" class="col-md-9">
					   <tiles:insertAttribute name="contents" />
					</div>
				</div>
			</div>
		</div>      
	      
		<!-- 푸터 전체 부분  : 부트스트랩 클래스 이외에 footer 클래스를 추가에 푸터에 추가로 들어갈 css를 설정-->
		<div class="container-fliud footer" id="footer">
			<tiles:insertAttribute name="footer" />
		</div>        
	</div>

	<div id="codeDialog">
		<div class="title" title="일정">
			<strong>일정</strong>
		</div>
		<table id="schduleList" class="table table-bordered"></table>
	</div>
   
	<div id="memoDialog">
		<form id="memoform">
			<h4>메모 입력</h4>
			제목&nbsp;&nbsp;&nbsp;<input type="text" id="memo_title" name="memo_title" class="form-control"/>
			<input type="hidden" id="memo_number" name="memo_number" />
			<p>내용</p> 
			<textarea rows="7" cols="25" id="memo_content" name="memo_content" class="form-control"></textarea>
		</form>
	</div>

</body>
</html>
