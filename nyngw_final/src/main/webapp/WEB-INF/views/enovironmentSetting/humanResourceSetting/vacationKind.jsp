<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 환경설정관리 -> 인사부 설정 -> 휴가 종류 설정
에 대한 화면 -->


<script>
function vacation_go() {

	var memdata = $('#vacation').serialize();
		$.ajax({
			url : '/enovironmentSetting/humanResourceSetting/addVacation',
			type : 'post',
			data : memdata,
			success : function(res) {
				if(res.su=="ok"){
					alert("성공적으로 휴가를 추가하였습니다.");
				}else{
					alert("휴가 추가에 실패하였습니다.");
				}
			},
			error : function() {

			},
			dataType : 'json'
		})

}
function vacationDelete_go() {
	var numbers=[];
	$('input[name="check_number"]:checkbox:checked').each(function(){numbers.push($(this).val());});
		
	$.ajax({
			url : '/enovironmentSetting/humanResourceSetting/delete',
			type : 'post',
			data : {'numbers':numbers},
			success : function(res) {
				if(res.su=="ok"){
					alert("성공적으로 휴가를 삭제하였습니다.");
				}else{
					alert("휴가 삭제에 실패하였습니다.");
				}
			},
			error : function() {

			},
			dataType : 'json'
		})

}

</script>
<h2>휴가 등록</h2>
<div style="border-bottom:1px solid #ddd;padding-bottom:45px;">
	<form id="vacation">
	   <input class="form-control" style="width:260px;display:inline-block;" type="text" name="vp_kind" placeholder="휴가명"/>
	   <input class="form-control" style="width:290px;display:inline-block;" type="text" name="vp_totalday" placeholder="휴가일수"/>
		<select name="vp_payonoff" style="width:170px;display:inline-block;" class="form-control docInputSelect" >
			<option value="y" class="deductVacation">급여 지급</option>
			<option value="n">급여 미지급</option>
		</select>
		<button class="btn btn-default" type="button" onclick ="vacation_go();">등록</button>
	</form>
</div>	

<br>
<div class="insertJoinBtnWrap">
<h2>휴가 종류 설정</h2>
<form method="post" class="form-inline" >
	<table class="table textCenter tableGray">
		<tr>
			<div class="form-group">
				<th><input type="checkbox" id="allCheck"/></th>
				<th>휴가명</th>
				<th>휴가 일수</th>
				<th>급여유무</th>
			</div>
		</tr>
		<c:forEach items="${vacationList}" var="va"  varStatus="status">
			<tr>
				<div>
					<td><input type="checkbox" value="${va.vp_number}" name="check_number" id="${va.vp_number }" />
					<td>${va.vp_kind}</td>
					<td><input class="form-control" type="text" value="${va.vp_totalday }" name="day" id="${va.vp_number }_dy" /></td>
					<td><select class="form-control docInputSelect" name="on" id="${va.vp_number}_on" >
							<option value="y" class="deductVacation">급여 지급</option>
							<option value="n">급여 미지급</option>
					</select></td>
				</div>
			</tr>
		</c:forEach>
	<tr><td colspan="4">
		<input type="button" class="btn btn-default" value="수정" onclick="vacationDeduction_go(this.form);"/>
		<input type="button" class="btn btn-default" value="삭제" onclick="vacationDelete_go();"/>
	</td></tr>
	</table>
</form>
</div>
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


	
	function vacationDeduction_go(f) {

		var numbers=[];
		$('input[name="check_number"]:checkbox:checked').each(function(){numbers.push($(this).val());});
		var number=[];
		$('input[name="check_number"]:checkbox').each(function(){number.push($(this).val());});
		var day=[];
		$('input[name="day"]').each(function(){day.push($(this).val());});
		var days=[];
		var on=[];
		$('select[name="on"]').each(function(){on.push($(this).val());});
		var ons=[];
		
		for (var i = 0; i < numbers.length; i++) {
			for (var i2 = 0; i2 < number.length; i2++) {
				if(numbers[i]==number[i2]){
					days[i]=day[i2];
					ons[i]=on[i2];
				}
			}
		}
		
		$.ajax({
			url : '/enovironmentSetting/humanResourceSetting/save',
			type : 'post',
			data : {"numbers":numbers
				,"days" :days
				,"ons" :ons
			},
			success : function(res) {
				if(res.su=="ok"){
					alert("성공적으로 수정하였습니다.");
				}else{
					alert("수정에 실패하였습니다.");
				}
			},
			error : function() {

			},
			dataType : 'json'
		})

	}
</script>