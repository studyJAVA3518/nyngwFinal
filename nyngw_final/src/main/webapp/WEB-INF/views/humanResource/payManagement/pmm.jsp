<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
	
$(function() {
//      $( ".inputTypeDate" ).datepicker({ dateFormat: 'yy-mm-dd'}); 
//      $( ".inputTypeMonth" ).monthpicker({ dateFormat: 'yy/mm'}); 
	
   //기타수당 금액 변경 클릭->금액 수정
	$('.memPayUpdateFormBtn').click(function(){
		var mp_number = $(this).parents().siblings('td.mp_bonus').children('input#up_mp_number').val();
		var mp_bonus = $(this).parents().siblings('td.mp_bonus').children('input#up_mp_bonus').val();
		var position_number = $(this).parents().siblings('td.mp_bonus').children('input#up_position_number').val();

		$.ajax({
			url:'/humanResource/payManagement/updateMPajax',
			type:'get',
			data: {
				'mp_number' : mp_number,
				'mp_bonus' : mp_bonus,
				'position_number' : position_number
			},
			success : function(res){
				if(res.result>0){
					alert('기타수당 금액 변경에 성공했습니다.');
					
				}else{
					alert('기타수당 금액 변경에 실패했습니다.');
				}
			},
			dataType : 'json'
		})
	})
	
	
	//급여 등록 팝업창 기본값=닫아준다
	$('.updateMemPayBox').css('display', 'none');
	
	//급여 등록 버튼 클릭시 급여등록팝업창 뜬다
	$('#memPayInsertFormBtn').click(function(){
			
		$('.updateMemPayBox').dialog({
			width: 700,
			height: 500,
			modal: true,
			buttons: {
		       "취소": function() {
					$(this).dialog("close");
				}
			},
			close: function() {
				$('#textArea').val('');
			}
		});
		
		
		
		//셀렉트 태그에 부서정보 가져오기
		$.ajax({
			url : '/humanResource/payManagement/viewMPDeptjax',
			type : 'get',
			success : function(res){
				var code = "";
				$.each(res,function(i,v){
					code +='<option value="' + v.dept_number+ '">' + v.dept_name + "</option>";
				})
				$('#dept_number').html(code);
				$('#dept_number').trigger('change');
			},
			dataType : 'json'
		})
		
		//선택한 부서에 해당하는 직급 가져오기
		/////////////////////////////////////
		var dept_number = "";
		$('#dept_number').change(function(){
			dept_number = $(this).val();
			$.ajax({
				url : '/humanResource/payManagement/viewMPPositionjax',
				type : 'get',
				data : {'dept_number' : dept_number},
				success : function(res){
					var code = "";
					$.each(res,function(i,v){
						code +="<option value='" + v.mem_position_number + "'>"+ v.position_name +"</option>";
					})
					$('#position_number').html(code);
					$('#position_number').trigger('change');
				},
				dataType : 'json'
			})
		})
		
		//선택한 부서에 해당하는 직급에 해당하는 사원 가져오기
		/////////////////////////////////////
		var position_number = "";
		$('#position_number').change(function(){
			position_number = $(this).val();
			$.ajax({
				url : '/humanResource/payManagement/viewMPNameListjax',
				type : 'get',
				data : {'dept_number' : dept_number,
						'position_number' : position_number	
				},
				success : function(res){
					var code = "";
					$.each(res,function(i,v){
						code +="<option value='" + v.mem_number + "'>"+ v.mem_name +"</option>";
					})
					$('#mem_number').html(code);
					$('#mem_number').trigger('change');
				},
				dataType : 'json'
			})
		})
		
		//사원을 선택하면 해당월을 선택할 수 있도록 조정
		/////////////////////////////////////
		var mem_number = "";
		var clickMonth = "";
		
		$('#mem_number').change(function(){
			mem_number = $(this).val();
			clickMonth = $('#payMonth').val()
				
			$.ajax({
				url : '/humanResource/payManagement/viewMPMemberPayInfoAjax',
				type : 'get',
				data : {'dept_number' : dept_number,
						'position_number' : position_number,
						'mem_number' : mem_number,
						'clickMonth' : clickMonth
				},
				success : function(res){
					
					$('#in_mem_number').val(mem_number);
					$('#in_basicPay').val(res.basicPay);
					$('#vacationDayDuring').html(res.vacationDayDuring);
					$('#in_vacationCost').val(res.vacationCost);
					$('#in_bonus').val('0');
				},
				dataType : 'json'
			})
			
		})
		
		
	})

});
</script>

<style>
 .form-control {
 	display : inline-block;
 }
</style>

<h2>급여관리</h2>
<p class="docTitleDescription">
	사원들의 급여를 확인할 수 있습니다.
	기본월급 : <br/> 
	<b>기본급+직책수당+식대-(기본급*무급휴가일수/해당월수)</b><br/>
</p>

<form action ="/humanResource/payManagement/pmm">
	<div class="searchSpace">
	수령연월 
		<input type="month" class="form-control inputTypeMonth docInputSelect" name="mp_pay_date"/>
		<select class="form-control docInputSelect" name="dept_name">
			<option value="">부서선택</option>
			<option value="">전체</option>
			<c:forEach items="${deptList}" var="dept">
				<option value="${dept.dept_number }">${dept.dept_name}</option>
			</c:forEach>
		</select>
		<input type="text"  placeholder="검색할 사원 이름입력" name="mem_name" class="form-control" style="display:inline-block;width:335px;"/>
		<button class="btn btn-default" type="submit">검색</button>
	</div>
</form>

<div class="row insertDocBtnWrap textRight">
	<button type="button" id="memPayInsertFormBtn" class="btn btn-default">급여 등록</button>
</div>

<table class="table table-bordered">
	<tr>
		<th>부서</th>
		<th>직책</th>
		<th>이름</th>
		<th>기본월급</th>
		<th>기타수당</th>
		<th>4대보험료</th>
		<th>최종금액</th>
		<th>수령일자</th>
		<th>급여수정</th>
	</tr>
	<c:choose>
		<c:when test="${memPayPageViewVO.memberTotalCount > 0 }">
			<c:forEach items="${memPayPageViewVO.memberPayViewList }" var="memPay">
				<tr>
					<td class="textCenter">
						${memPay.dept_name}
					</td>
					<td class="textCenter">
						${memPay.position_name}
					</td>
					<td class="textCenter">
						${memPay.mem_name}
					</td>
					<td class="textRight">
						<fmt:formatNumber value="${memPay.mp_basic_pay}" type="currency" currencySymbol="￦"/>
					</td>
					<td class="textRight mp_bonus">
						<input type="text" value="${memPay.mp_bonus}" name="up_mp_bonus" id="up_mp_bonus" class="textRight up_mp_bonus form-control" style="display:inline; width:100px;"/>
						<input type="hidden" value="${memPay.mp_number}" name="up_mp_number" id="up_mp_number" class="textRight up_mp_bonus form-control" style="display:inline; width:100px;"/>
						<input type="hidden" value="${memPay.position_number}" name="up_position_number" id="up_position_number" class="textRight up_mp_bonus form-control" style="display:inline; width:100px;"/>
					</td>
					<td class="textRight">
						<fmt:formatNumber value="${memPay.mp_insurance}" type="currency" currencySymbol="￦"/>
					</td>
					<td class="textRight">
						<fmt:formatNumber value="${memPay.mp_final_salary}" type="currency" currencySymbol="￦"/>
					</td>
					<td class="textCenter">
						${memPay.mp_pay_date}
					</td>
					<td class="textCenter">
						<input type="button" class="btn btn-default memPayUpdateFormBtn" value="수정"/>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td style="text-align: center;" colspan="9">내용이 없습니다.</td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>
<div id="pageNum" class="textCenter">
	<c:if test="${beginPage > perPage}">
		<a href="<c:url value="/humanResource/payManagement/pmm?page=${beginPage-1}&mem_name=${mem_name}&dept_name=${dept_name}"/>">이전</a>
	</c:if>
	<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
		<a href="<c:url value="/humanResource/payManagement/pmm?page=${pno}&mem_name=${mem_name }&dept_name=${dept_name}" />">[${pno}]</a>
	</c:forEach>
	<c:if test="${endPage < memPayPageViewVO.getPageTotalCount()}">
		<a href="<c:url value="/humanResource/payManagement/pmm?page=${endPage + 1}&mem_name=${mem_name }$dept_name=${dept_name}"/>">다음</a>
	</c:if>
	
	<!-- 
		아래부분 지우시고 
		위에 부분 마지막 if 문 "addressBookViewVO.getPageTotalCount()" 부분하고
		검색어 url parameter로 넘겨주는 부분만 자기 것으로 수정하시면 되요~
	-->	
</div>

<div class="updateMemPayBox">
	<!-- 급여 등록하는 팝업창 -->
	<h4>급여 등록하기</h4>
	<table class="table">
		<form name="insertMPForm">
			<tr>
				<td colspan="2">
					지급월 선택<input type="month" class="form-control inputTypeMonth" id="payMonth" name="payMonth" style="width:100px;" value="2017-07"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label for="dept_number">부서 선택</label>
					<select name="dept_number" id="dept_number" style="width : 120px;" class="form-control">
					</select>
					<label for="position_number">직급 선택</label>
					<select name="position_number" id="position_number" style="width : 120px;" class="form-control">
					</select>
					<label for="mem_number">이름 선택</label>
					<select name="mem_number" id="mem_number" style=" width : 120px;" class="form-control">
					</select>
				</td>
			</tr>
			<tr>
				<th>기본월급 : 기본급+직책수당+식대</th>
				<td>
					<input type="hidden" name="in_mem_number" id="in_mem_number"/>
					<input type="text" name="in_basicPay" id="in_basicPay" class="form-control"/>
				</td>
			</tr>
			<tr>
				<th>무급휴가 사용일수</th>
				<td id="vacationDayDuring"></td>
			</tr>
			<tr>
				<th>휴가차감금액 : 기본월급/해당월수</th>
				<td>
					<input type="text" name="in_vacationCost" id="in_vacationCost" class="form-control"/>
				</td>
			</tr>
			<tr>
				<th>기타수당</th>
				<td>
					<input type="text" name="in_bonus" id="in_bonus" class="form-control"/>
				</td>
			</tr>
			<tr>
				<th>월급 지급일자 선택</th>
				<td>
					<input type="text" name="in_payDate" id="in_payDate" class="form-control inputTypeDate2"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" class="btn btn-default" onclick="pk_insert_go();" value="급여 등록"/>
				</td>
			</tr>
		</form>
	</table>
</div>

<script>
	function pk_insert_go(){
		
		//금액 입력 형식에 맞는지 체크(숫자만 들어가야함)
		var regNumber = /^[0-9]*$/;
		
		var inputBasicPay = document.getElementById("in_basicPay").value;
		var inputVacationCost = document.getElementById("in_vacationCost").value;
		var inputBonus = document.getElementById("in_bonus").value;
		var inputPayDate = document.getElementById("in_payDate").value;
		
		if(!inputBasicPay){
			alert("기본 월급을 입력해야 합니다.");
			document.getElementById("in_basicPay").focus();
			return;
		}else if(inputBasicPay.match(regNumber) == null){
			alert("숫자만 입력할 수 있습니다.");
			document.getElementById("in_basicPay").focus();
			return;
		}
		if(!inputVacationCost){
			alert("휴가차감금액을 입력해야 합니다.");
			document.getElementById("in_vacationCost").focus();
			return;
		}else if(inputVacationCost.match(regNumber) == null){
			alert("숫자만 입력할 수 있습니다.");
			document.getElementById("in_basicPay").focus();
			return;
		}
		if(!inputBonus){
			alert("기타수당을 입력해야 합니다.");
			document.getElementById("inputBonus").focus();
			return;
		}else if(inputBonus.match(regNumber) == null){
			alert("숫자만 입력할 수 있습니다.");
			document.getElementById("in_basicPay").focus();
			return;
		}
		if(!inputPayDate){
			alert("월급 지급일자를 입력해야 합니다.");
			document.getElementById("in_basicPay").focus();
			return;
		}
		
		document.insertMPForm.method="post";
		document.insertMPForm.action="<%=request.getContextPath()%>/humanResource/payManagement/insertMemberPay";
		document.insertMPForm.submit();
		
	}
</script>