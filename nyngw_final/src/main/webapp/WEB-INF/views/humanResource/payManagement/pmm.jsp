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
		
		/////////////////////////////////////
		var dept_number = "";
		$('#dept_number').change(function(){
			dept_number = $(this).val();
			$.ajax({
				url : 'prodName.jsp',
				type : 'post',
				data : {'dept_number' : dept_number},
				success : function(res){
					var code = "";
					$.each(res,function(i,v){
						code +="<option value='" + v.id + "'>"+ v.name +"</option>";
					})
					$('#prod').html(code);
					$('#prod').trigger('change');
				},
				dataType : 'json'
			})
		})
		/////////////////////////////////////
// 		$('#prod').change(function(){
// 			var prodId = $(this).val();
// 			$.post(
// 				'detail.jsp',
// 				'id='+prodId,
// 				function(res){
// 					var code = "<table class='table table-bordered'>";
// 					code += "<tr><td>항목</td><td>내용</td>";
// 					code += "<tr><td>PROD_ID</td><td>" + res.id + "</td></tr>";
// 					code += "<tr><td>PROD_NAME</td><td>" + res.name + "</td></tr>";
// 					code += "<tr><td>PROD_LGU</td><td>" + res.lgu + "</td></tr>";
// 					code += "<tr><td>PROD_BUYER</td><td>" + res.buyer + "</td></tr>";
// 					code += "<tr><td>PROD_COST</td><td>" + res.cost + "</td></tr>";
// 					code += "<tr><td>PROD_PRICE</td><td>" + res.price + "</td></tr>";
// 					code += "<tr><td>PROD_SALE</td><td>" + res.sale + "</td></tr>";
// 					code += "<tr><td>PROD_OUTLINE</td><td>" + res.outline + "</td></tr>";
// 					code += "<tr><td>PROD_DETAIL</td><td>" + res.detail + "</td></tr>";
// 					code += "<tr><td>PROD_PROPERSTOCK</td><td>" + res.properstock + "</td></tr>";
// 					code += "<tr><td>PROD_SIZE</td><td>" + res.size + "</td></tr>";
// 					code += "<tr><td>PROD_COLOR</td><td>" + res.color + "</td></tr>";
// 					code += "<tr><td>PROD_DELIVERY</td><td>" + res.delivery + "</td></tr>";
// 					code += "<tr><td>PROD_QTYIN</td><td>" + res.qtyin + "</td></tr>";
// 					code += "<tr><td>PROD_QTYSALE</td><td>" + res.qtysale + "</td></tr>";
// 					code += "<tr><td>PROD_MILEAGE</td><td>" + res.mileage + "</td></tr>";
					
// 					code +="</table>";
// 					$('#result').html(code);
// 				},
// 				'json'
// 			)
// 		})
		///////////////////////////////////////////////////////////////////
	})

});
</script>

<style>
 .form-control {
 	display : inline-block;
 }
</style>

<form action ="/humanResource/payManagement/pmm">
<table class="table table-bordered">
	<tr>
		<th>구분</th>
		<td>급여명세서</td>
	</tr>
	<tr>
		<th>수령연월</th>
		<td>
			<input type="month" class="form-control inputTypeMonth" name="mp_pay_date"/>
		</td>
	</tr>
	<tr>
		<th>
			<select class="form-control" name="dept_name" style="display:inline-block;width:150px;">
				<option value="">부서선택</option>
				<option value="">전체</option>
				<c:forEach items="${deptList}" var="dept">
					<option value="${dept.dept_number }">${dept.dept_name}</option>
				</c:forEach>
				
			</select>
		</th>
		<td>
			<input type="text"  placeholder="검색할 사원 이름입력" name="mem_name" class="form-control" style="display:inline-block;width:150px;"/>
			<button class="btn btn-default" type="submit">검색</button>
		</td>
	</tr>
</table>
</form>
<p>기본월급은 (기본급+직책수당+식대)로 계산됩니다.</p>
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
<div id="pageNum">
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

<div class="row">
	<button type="button" id="memPayInsertFormBtn" class="btn btn-default">급여 등록</button>
</div>



<div class="updateMemPayBox">
	<!-- 급여 등록하는 팝업창 -->
	<h4>급여 등록하기</h4>
	<table class="table">
		<form name="insertMPForm">
			<tr>
				<td colspan="2">
					<label for="dept_number">부서 선택</label>
					<select name="dept_number" id="dept_number" style="width : 120px;" class="form-control">
						<option selected="selected">부서 선택</option>
					</select>
					<label for="position_number">직급 선택</label>
					<select name="position_number" id="position_number" style="width : 120px;" class="form-control">
						<option selected="selected">직급 선택</option>
					</select>
					<label for="mem_number">이름 선택</label>
					<select name="mem_number" id="mem_number" style=" width : 120px;" class="form-control">
						<option selected="selected">이름선택 선택</option>
					</select>
				</td>
			</tr>
		</form>
	</table>
</div>