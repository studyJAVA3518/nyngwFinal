<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script>
$(function(){
/////////////////////////////////////////////////////////화면1용
// 		대분류 갖고오기
	$.ajax({
		url : '/homeMain/appointedUI/bigMenu',
		type : 'get',
		success : function(res){
			var code = "";
			$.each(res,function(i, v){
				code += "<option value='" + v.big_number + "'>" + v.big_name +"</option>";
			});
			$('#big_number').html(code);
			$('#big_number').trigger('change');
		},
		dataType : 'json'
	});
// 중분류가져오기
	$('#big_number').change(function(){
		var big_num = $(this).val();
		$.ajax({
			url : '/homeMain/appointedUI/middleMenu' ,
			type : 'post' ,
			data : 'big_num=' + big_num , 
			success : function(res){
				var code = "";
				$.each(res,function(i, v){
					code += "<option value='" + v.mid_number + "'>" + v.mid_name +"</option>";
				});
				$('#middleMenu').html(code);
			},
			dataType : 'json'
		});
	});

///////////////////////////////////화면2용
// 		대분류 갖고오기
	$.ajax({
		url : '/homeMain/appointedUI/bigMenu1',
		type : 'get',
		success : function(res){
			var code = "";
			$.each(res,function(i, v){
				code += "<option value='" + v.big_number + "'>" + v.big_name +"</option>";
			});
			$('#big_number1').html(code);
			$('#big_number1').trigger('change');
		},
		dataType : 'json'
	});
// 중분류가져오기
	$('#big_number1').change(function(){
		var big_num = $(this).val();
		$.ajax({
			url : '/homeMain/appointedUI/middleMenu1' ,
			type : 'post' ,
			data : 'big_num=' + big_num , 
			success : function(res){
				var code = "";
				$.each(res,function(i, v){
					code += "<option value='" + v.mid_number + "'>" + v.mid_name +"</option>";
				});
				$('#middleMenu1').html(code);
			},
			dataType : 'json'
		});
	});



//////////////////////////////////////////////////화면3용
//		대분류 갖고오기
	$.ajax({
		url : '/homeMain/appointedUI/bigMenu2',
		type : 'get',
		success : function(res){
			var code = "";
			$.each(res,function(i, v){
				code += "<option value='" + v.big_number + "'>" + v.big_name +"</option>";
			});
			$('#big_number2').html(code);
			$('#big_number2').trigger('change');
		},
		dataType : 'json'
	});
// 중분류가져오기
	$('#big_number2').change(function(){
		var big_num = $(this).val();
		$.ajax({
			url : '/homeMain/appointedUI/middleMenu2' ,
			type : 'post' ,
			data : 'big_num=' + big_num , 
			success : function(res){
				var code = "";
				$.each(res,function(i, v){
					code += "<option value='" + v.mid_number + "'>" + v.mid_name +"</option>";
				});
				$('#middleMenu2').html(code);
			},
			dataType : 'json'
		});
	});
	
	
	
// 	체크박스로 선택하는 부분
	$("#autoAndUser").on('click', function() {
	      if ( $(this).prop('checked') ) {
	        $('#uiSelect').addClass("userInterFaceSetting");
	        $('#userInterFaceButton').attr('disabled',true);
	        $('#big_number').attr('disabled', 'true');
	        $('#middleMenu').attr('disabled', 'true');
	        $('#big_number1').attr('disabled', 'true');
	        $('#middleMenu1').attr('disabled', 'true');
	        $('#big_number2').attr('disabled', 'true');
	        $('#middleMenu2').attr('disabled', 'true');

	      } else {
	        $('#userInterFaceButton').removeAttr('disabled'); 
	        $('#big_number').removeAttr('disabled'); 
	        $('#middleMenu').removeAttr('disabled'); 
	        $('#big_number1').removeAttr('disabled'); 
	        $('#middleMenu1').removeAttr('disabled'); 
	        $('#big_number2').removeAttr('disabled'); 
	        $('#middleMenu2').removeAttr('disabled'); 
	        $('#uiSelect').removeClass("userInterFaceSetting");
	      }
	});
	$(function(){  
	    $('#big_number option[value=${big_number}]').prop('selected',true);
	    $('#middleMenu option[value=${middleMenu}]').prop('selected',true);
	    $('#big_number1 option[value=${big_number1}]').prop('selected',true);
	    $('#middleMenu1 option[value=${middleMenu1}]').prop('selected',true);
	    $('#big_number2 option[value=${big_number2}]').prop('selected',true);
	    $('#middleMenu2 option[value=${middleMenu2}]').prop('selected',true);
	});
})
	function userUiSave(){
		$.ajax({
			url : '/homeMain/appointedUI/userUiSave' ,
			type : 'post' ,
			data : $('#userUi').serialize() , 
			success : function(res){
				alert('저장됬시유 ㅎ');
				location.href=res.uri;
			},
			dataType : 'json'
		});
	}

</script>
<style>
	.userInterFaceSetting{
		background-color: rgba( 0, 0, 0, 0.5 );
	}
</style>
<div id="allDiv">
	<div id="topDiv" style="height: 50px;">
		<button class="btn">자동</button>이 버튼을 누르면 밑에 화면1,2,3이 자주들어간 곳으로 설정됨.
		<br>
		<input type="checkbox" id="autoAndUser"> 체크시 자동 미체크시 UI선택
	</div>
<!-- 	<div id="bodyDiv"> -->
	<div id="uiSelect">
		<div style="height:60px;">
			<form id="userUi">
				<div style="float: left; margin: 0 30px;">
					<h1>화면1</h1>
					<div style="width: 100%;">
						<select name="big_number" id="big_number"></select>
						<select name="middleMenu" id="middleMenu"></select>
					</div>
				</div>
			
				<div style="float: left; margin: 0px 30px;">
					<h1>화면2</h1>
					<div style="width: 100%;">
						<select name="big_number1" id="big_number1"></select>
						<select name="middleMenu1" id="middleMenu1"></select>
					</div>
				</div>
	
				<div style="float: left; margin: 0px 30px;">
					<h1>화면3</h1>
					<div style="width: 100%;">
						<select name="big_number2" id="big_number2"></select>
						<select name="middleMenu2" id="middleMenu2"></select>
					</div>
				</div>
				<button type="button" id="userInterFaceButton" onclick="userUiSave()">저장</button>
			</form>
		</div>
		<div style="height: 400px;">
			<div style="height: 50%; width: 100%; border: 2px solid black; text-align: center;">
				<h1>화면 1</h1> 
			</div>
			<div style="height: 40%; width: 46%; border: 2px solid black; float: left; margin: 2% 2%; text-align: center; vertical-align: middle;">
				<h1>화면 2</h1> 
			</div>
			<div style="height: 40%; width: 46%; border: 2px solid black; float: left; margin: 2% 2%; text-align: center; vertical-align: middle;">
				<h1>화면 3</h1> 
			</div>
		</div>
	</div>
	<div style="width: 100%;">
		<button class="btn">저장</button>
		<button class="btn">취소</button>
	</div>
</div>