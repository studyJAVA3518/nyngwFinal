<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script>
$(function(){ //전체선택 체크박스 클릭
	$("#allCheck").click(function() {
			//만약 전체 선택 체크박스가 체크된상태일경우 
			if ($("#allCheck").prop("checked")) {
				//해당화면에 전체 checkbox들을 체크해준다 
				$("input[type=checkbox]").prop("checked", true);
				// 전체선택 체크박스가 해제된 경우
			} else {
				//해당화면에 모든 checkbox들의 체크를해제시킨다. 
				$("input[type=checkbox]").prop("checked", false);
			}
		})
	})
	
	
	function vacationYear_go() {

		var numbers=[];
		$('input[name="checkYR"]:checkbox:checked').each(function(){numbers.push($(this).val());});
		var number=[];
		$('input[name="checkYR"]:checkbox').each(function(){number.push($(this).val());});
		var on=[];
		$('select[name="yv_vacation_day"]').each(function(){on.push($(this).val());});
		var ons=[];
		
		for (var i = 0; i < numbers.length; i++) {
			for (var i2 = 0; i2 < number.length; i2++) {
				if(numbers[i]==number[i2]){
					ons[i]=on[i2];
				}
			}
		}
		
		$.ajax({
			url : '/enovironmentSetting/humanResourceSetting/modify',
			type : 'post',
			data : {"numbers":numbers
				,"ons" :ons
			},
			success : function(res) {
				if(res.su=="ok"){
					alert("success");
				}
			},
			error : function() {

			},
			dataType : 'json'
		})

	}
	
	function vacationYearDEL_go() {

		var numbers=[];
		$('input[name="checkYR"]:checkbox:checked').each(function(){numbers.push($(this).val());});
		
		$.ajax({
			url : '/enovironmentSetting/humanResourceSetting/deleteYear',
			type : 'post',
			data : {"numbers":numbers},
			success : function(res) {
				if(res.su=="ok"){
					alert("success");
				}
			},
			error : function() {

			},
			dataType : 'json'
		})

	}
	
	function addAndCheck(){
		
		var memdata = $('#yearVacation').serialize();
		
		$.ajax({
			url : '/enovironmentSetting/humanResourceSetting/addYearVacation',
			type : 'post',
			data :memdata,
			success : function(res) {
				if(res.su=="ok"){
					alert("success");
				}else{
					alert("기존에 존재함");
				}
			},
			error : function() {

			},
			dataType : 'json'
		})
	}
	
	
</script>
<!-- 환경설정관리 -> 인사부 설정 -> 휴가 일수 설정에 대한 화면 -->

<h2>휴가 일수 설정</h2>
<p>직원별로 휴가일수를 관리할 수 있으며, 휴가일수를 설정하면 직원들의 휴가 사용현황을 보다 정확하게 보실 수 있습니다.</p>
<div class="row">
	<div class="col-md-6">
		<h4>책정기준</h4>
		<div role="tabpanel">
		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#days" aria-controls="home" role="tab" data-toggle="tab">근무일수 기준</a></li>
		    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">프로필</a></li>
		  </ul>
		
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="days">
		    	<table class="table table-bordered">
		    	<tr>
		    		<th><input type="checkbox" id="allCheck"/></th>
		    		<th>연차</th>
		    		<td colspan="2">연차별 추가 휴가 일수</td>
		    	</tr>
						<c:forEach items="${yearList }" var="year">
							<tr>
								<th><input type="checkbox" name="checkYR" value="${year.yv_year }"/></th>	
								<th>${year.yv_year }</th>
								<td>${year.yv_vacation_day}</td>
								<td><select name="yv_vacation_day" class="btn btn-default">
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
								</select></td>
							</tr>
						</c:forEach>
					</table>
					<input type="button" value="변경" onclick="vacationYear_go();"/>
					<input type="button" value="삭제" onclick="vacationYearDEL_go();"/>
					<form id="yearVacation">
						<input type="text" name="yv_year" />
						<select name="yv_vacation_day" class="btn btn-default">
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
						</select>
						<button onclick="addAndCheck();">추가</button>
					</form> 
					</div>
		    <div role="tabpanel" class="tab-pane" id="profile">
		    	프로필
		    </div>
		  </div>
		
		</div>
	</div>
</div>

<script>
$(function(){
	$('#myTab a').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
	})
})
</script>