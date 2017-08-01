<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>NYN Group Ware System</title>

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
	
	<!-- 다음에디터를 쓰기위한 링크 -->
	<link rel="stylesheet" href="<c:url value="/resources/daumOpenEditor/css/editor.css"/>" type="text/css" charset="utf-8"/>
	<script src="<c:url value="/resources/daumOpenEditor/js/editor_loader.js"/>" type="text/javascript" charset="utf-8"></script>
	
	 <!-- 조직도 트리를 위한 js -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/tree/zooTree.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/tree/zooTree.css">
    
	
	<!-- Vedio -->
    <!--1. 비디오를 웹브라우저에 노출하기 위한 플러그인입니다. 비디오 재생 플레이어의 일종이라 생각하시면 됩니다.-->
	<script src="<%=request.getContextPath()%>/resources/js/video.js"></script>
    <!--2. 비디오나 이미지를 풀사이즈로 넣는 플러그인입니다. -->
    <script src="<%=request.getContextPath()%>/resources/js/bigvideo.js"></script>
    <!--3. 비디오가 아닌 이미지로 풀사이즈일 경우 bigvideo.js에서 이 파일을 호출하게 됩니다. -->

<script>
    	
    $(function(){
      
		// 비디오나 배경을 노출할 엘리먼트를 선택합니다. $('#videoEle')는 html 태그중에 <div id="videoEle"></div> 를 말하는 것입니다.
		var BV = new $.BigVideo({useFlashForFirefox:false, container:$('.videoEle')});
	    BV.init();
	    
        //웹브라우저마다 지원하는 비디오 형식이 다르기 때문에 다양하게 만들어서 제공해야합니다. 변환은 다음팟인코더나, 카카오인코더를 이용하세요. 
        //옵션중에 doLoop는 영상 반복을 의미합니다. true는 영상 반복, false는 반복 안함입니다.
        BV.show(
            { type: "video/mp4",  src: '../resources/video/company.mp4', doLoop:true }
        );
        //사운드를 0 즉 음소거 상태로 만듭니다. 
        BV.getPlayer().volume(0);
    })
    
</script>

</head>
<body>
	
	<c:if test="${param.error != null }">
		<script>
		  alert("로그인을 실패하였습니다.");
		</script>
	</c:if>
	
	
	<div class="container-fluid indexWrapper BigWrap">

		<div class="videoEle"></div>
		
		<nav class="navbar navbar-default navbar-fixed-top topBar">
			<div class="container">
				<div class="row">
					<div class="col-md-2">
						<img src="<%=request.getContextPath()%>/resources/images/nynLogo.png" class="img-responsive" style="width:60%; height : 60%;">	
					</div>
					<div class="col-md-10 topText">
						Do. and it will be done<br/>Next Year`s Newface Groupware Solotion
					</div>
				</div>
			</div>
		</nav>

		<div class="container">
			<div class="row login">
				<div class="col-md-4 col-md-offset-4">
					<h2>NYN 그룹웨어 시스템 로그인</h2>
					<form action="<c:url value='/user/login'/>" method="post">
						<div class="form-group">
							<input type="text" id="mem_id" name="mem_id" class="form-control" placeholder="아이디를 입력하세요."/>
						</div>
						<div class="form-group">
							<input type="password" id="mem_pwd" name="mem_pwd" class="form-control" placeholder="비밀번호를 입력하세요."/>
						</div>
						<button type="submit" class="btn btn-default">로그인</button>
						<button type="button" class="btn btn-default" onclick="location.href='/login/searchForm'">아이디/비밀번호 찾기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>