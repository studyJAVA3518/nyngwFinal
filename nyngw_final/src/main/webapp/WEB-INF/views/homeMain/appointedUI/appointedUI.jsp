<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
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
		    $('#big_number option[value=${big_number}]').prop('selected',true);
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
			    $('#middleMenu option[value=${middleMenu}]').prop('selected',true);
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
		    $('#big_number1 option[value=${big_number1}]').prop('selected',true);
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
			    $('#middleMenu1 option[value=${middleMenu1}]').prop('selected',true);
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
		    $('#big_number2 option[value=${big_number2}]').prop('selected',true);
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
			    $('#middleMenu2 option[value=${middleMenu2}]').prop('selected',true);
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
})
	function userUiSave(){
	    var a = $('#middleMenu').val(); 
	    var b = $('#middleMenu1').val(); 
	    var c = $('#middleMenu2').val();
	    if(a != b && b != c && c != a){
			$.ajax({
				url : '/homeMain/appointedUI/userUiSave' ,
				type : 'post' ,
				data : $('#userUi').serialize() , 
				success : function(res){
					alert('UI설정이 저장되었습니다.');
					location.href=res.uri;
				},
				dataType : 'json'
			});
	    }else{
			alert('값을 서로 다르게 해주세요.');	    	
	    }
	}

</script>
<style>
	.uiTopWrap > h2 {
		margin : 25px 0 15px;
		padding-left : 20px;
	}
	.userInterFaceSetting{
		background-color: rgba( 0, 0, 0, 0.5 );
	}
	#topDiv {
		height: 60px;
		line-height : 30px;
		margin : 5px 0 15px;
		padding-left : 20px;
	}
	#userUi > div > div> h4{
		margin : 30px 0px 18px;
	}
	.uiSelect {
		display : inline-block;
		width : 115px;
	}
	.uiSelectWrap {
		overflow : hidden;
		margin : 15px 0 20px 10px;
	}
	.uiSelectBtnWrap {
		padding : 15px;
	}
	
	.uiBox_01 h4, .uiBox_02 h4, .uiBox_03 h4 {
		line-height : 150px;
	}
	
	.uiBox_01 {
		height: 150px; 
		border: 2px solid #00a0c7; 
		text-align: center;
		margin : 0 0 15px 5px;
		border-radius : 10px;
		background-color : #f1f5f8;
	}
	.uiBoxBottomWrap {
		margin : 0 0 15px 4px;
	}
	.uiBox_02 {
		height: 150px; 
		border: 2px solid #00a0c7;
		text-align: center;
		border-radius : 10px;
		width: 49%;
		margin-right : 2%;
		background-color : #f1f5f8;
	}
	
	.uiBox_03 {
		height: 150px; 
		border: 2px solid #00a0c7;
		text-align: center;
		border-radius : 10px;
		width: 49%;
		background-color : #f1f5f8;
	}
	.uiBottomWrap {
		margin : 15px auto 30px;
		text-align : center;
		
	}
</style>
<div id="allDiv row">
	
	<div class="row">
		<div class="col-md-12 uiTopWrap">
			<h2>UI 설정하기</h2>
			
			<div id="topDiv">
				<button class="btn">자동</button>이 버튼을 누르면 밑에 화면1,2,3이 자주들어간 곳으로 설정됩니다.
				<br>
				<input type="checkbox" id="autoAndUser"> 체크시 자동으로 사용자가 자주 사용하는 화면으로 설정되고 미체크시 UI를 선택할 수 있습니다.
			</div>
		</div>
	</div>
	
	<div id="uiSelect" class="row">
		<div class="col-md-12">
			<form id="userUi">
				<div class="row uiSelectWrap">
					<div class="col-md-4">
						<h4>화면1</h4>
						<div class="row">
							<select name="big_number" id="big_number" class="form-control uiSelect"></select>
							<select name="middleMenu" id="middleMenu" class="form-control uiSelect"></select>
						</div>
					</div>
				
					<div class="col-md-4">
						<h4>화면2</h4>
						<div style="width: 100%;">
							<select name="big_number1" id="big_number1" class="form-control uiSelect"></select>
							<select name="middleMenu1" id="middleMenu1" class="form-control uiSelect"></select>
						</div>
					</div>
		
					<div class="col-md-4">
						<h4>화면3</h4>
						<div style="width: 100%;">
							<select name="big_number2" id="big_number2" class="form-control uiSelect"></select>
							<select name="middleMenu2" id="middleMenu2" class="form-control uiSelect"></select>
						</div>
					</div>
				</div>
			</form>
		</div>
		
		<div class="col-md-12">
			<div class="row uiBox_01">
				<h4>화면 1</h4> 
			</div>
			<div class="row uiBoxBottomWrap">
				<div class="col-md-6 uiBox_02">
					<h4>화면 2</h4> 
				</div>
				<div class="col-md-6 uiBox_03">
					<h4>화면 3</h4> 
				</div>
			</div>
		</div>
	</div>
	<div class="row uiBottomWrap">
		<button type="button" id="userInterFaceButton" onclick="userUiSave()" class="btn btn-default">저장</button>
		<button class="btn btn-default" onclick="location.href='<%=request.getContextPath()%>/homeMain/main'">홈으로</button>
	</div>
</div>