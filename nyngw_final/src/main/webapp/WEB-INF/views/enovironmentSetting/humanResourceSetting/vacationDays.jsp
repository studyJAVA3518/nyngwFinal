<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(function(){ 
	$('#tabHead a').click(function (e) {
		e.preventDefault()
		$(this).tab('show')
	})
	
	$('#tabHead a[href="#days"]').css('background-color','#ccc');
	
	$('#tabHead a[href="#days"]').click(function(){
		$('#tabHead a[href="#days"]').css('background-color','#ccc');
		$('#tabHead a[href="#profile"]').css('background-color','rgba( 255, 255, 255, 0)');
		
	});
	$('#tabHead a[href="#profile"]').click(function(){
		$('#tabHead a[href="#days"]').css('background-color','rgba( 255, 255, 255, 0)');
		$('#tabHead a[href="#profile"]').css('background-color','#ccc');
	});
	
	
	
	$('#codeDialog').css('display', 'none');	
	
	//전체선택 체크박스 클릭
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
<script>
   $(function(){  
       $('#mem_dept_number option[value=${dept_number}]').prop('selected',true);
   });
</script>
<h2>휴가 일수 설정</h2>
<p class="docTitleDescription">직원별로 휴가일수를 관리할 수 있으며, 휴가일수를 설정하면 직원들의 휴가 사용현황을 보다 정확하게 보실 수 있습니다.</p>
<br>
<div class="row">
	<!-- Nav tabs -->
	<ul id="tabHead" class="nav nav-tabs" role="tablist">
		<li class="active" role="presentation">
			<a data-target="#days" href="#days" aria-controls="days" role="tab" data-toggle="tab" aria-expanded="true" id="daysA"> 
				연차별 휴가
			</a>
		</li>
		<li role="presentation">
			<a data-target="#profile" href="#profile" aria-controls="profile" role="tab" data-toggle="tab" aria-expanded="false">
				사원 휴가 현황
			</a>
		</li>
	</ul>

  <!-- Tab panes -->
	<div id="tabContent" class="tab-content">
    	<!-- 왼쪽탭 탭 -->
	    <div role="tabpanel" class="tab-pane fade active in"  id="days">
	    	<div class="hrTapWrap">
	    		<div id="addDay" style="border-bottom:1px solid #ddd;padding-bottom:45px;">
					<h4>연차별 휴가 일수 추가</h4>
					<form id="yearVacation">
						<input class="form-control" type="text" name="yv_year" style="display:inline-block;width:200px"/>
						&nbsp;&nbsp;&nbsp;&nbsp;년차
						&nbsp;&nbsp;&nbsp;&nbsp;
						<select name="yv_vacation_day" class="form-control docInputSelect">
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
						&nbsp;&nbsp;&nbsp;&nbsp;일
						&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-default" onclick="addAndCheck();">추가</button>
					</form> 
				</div>	
	    		<br>
	    		<div id="editDay"  style="border-bottom:1px solid #ddd;padding-bottom:45px;">
	    			<h4>연차별 휴가 일수 수정</h4>
			    	<table class="table table-bordered tableGray textCenter">
				    	<tr>
				    		<th><input type="checkbox" id="allCheck"/></th>
				    		<th>연차 (년)</th>
				    		<td colspan="2">연차별 추가 휴가 일수</td>
				    	</tr>
						<c:forEach items="${yearList }" var="year">
							<tr>
								<th><input type="checkbox" name="checkYR" value="${year.yv_year }"/></th>	
								<th>${year.yv_year }</th>
								<td>${year.yv_vacation_day}</td>
								<td><select id="yv_vacation_day" name="yv_vacation_day" class="form-control docInputSelect">
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
					<div class="textCenter">
						<input type="button" value="변경" class="btn btn-default" onclick="vacationYear_go();"/>
						<input type="button" value="삭제" class="btn btn-default" onclick="vacationYearDEL_go();"/>
					</div>	
				</div>	
			</div>	
		</div>
		
		<!-- 오른쪽 탭 -->
	    <div role="tabpanel" class="tab-pane fade" id="profile">
			<div class="hrTapWrap">
		    	<form action="vacationDaysForm">
			    	<select name="mem_dept_number" id="mem_dept_number" class="form-control docInputSelect">
						<option value="all" >모든부서</option>
						<option value="dept3" >인사부</option>
						<option value="dept4" >기획홍보부</option>
						<option value="dept5" >영업부</option>
						<option value="dept6" >생산부</option>
						<option value="dept7" >인사1팀</option>
						<option value="dept8" >인사2팀</option>
						<option value="dept9" >기획1팀</option>
						<option value="dept10" >기획2팀</option>
					</select>
					<input class="form-control" type="text" name="mem_name" style="display:inline-block;width:400px"/>
					<button class="btn btn-default">검색</button>
				</form>
				<br>
		    	<table class="table table-bordered tableGray">
			    	<tr>
			    		<th>사원 아이디</th>
			    		<th>사원 명</th>
			    		<th>부서 명</th>
			    		<th>직급 명</th>
			    		<th>연차</th>
			    		<th>휴가</th>
			    		<th>사용한 휴가</th>
			    		<th>남은 휴가</th>
		    		</tr>
			    	<c:forEach items="${memberList }" var="member">
				    	<tr onclick="location.href='/enovironmentSetting/humanResourceSetting/detailVacation?mem_number=${member.mem_number}'">
							<td>${member.mem_id }</td>
							<td>${member.mem_name }</td>
							<td>${member.dept_name }</td>
							<td>${member.position_name }</td>
							<td>${member.mem_carear }</td>
							<td>${member.vp_totalday }</td>
							<td>${member.use_vacation }</td>
							<td>${member.nouse_vacation }</td>
						</tr>
					</c:forEach>
				</table>
				<div class="textCenter">
					<c:if test="${page.currentPageNo >= page.firstPageNo + page.sizeOfPage}">
						<a href="/enovironmentSetting/humanResourceSetting/vacationDaysForm?page=${i}&mem_dept_number=${dept_number}">이전</a>
					</c:if>
					<c:forEach begin="${page.startPageNo}" end="${page.endPageNo}" step="1"	var="i">
						<a href="/enovironmentSetting/humanResourceSetting/vacationDaysForm?page=${i}&mem_dept_number=${dept_number}">[${i}]</a>
					</c:forEach>
					<c:if test="${page.startPageNo+page.sizeOfPage < page.finalPageNo}">
						<a href="/enovironmentSetting/humanResourceSetting/vacationDaysForm?page=${i}&mem_dept_number=${dept_number}">다음</a>
					</c:if>
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