<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 환경설정관리 -> 기획홍보부 설정 -> 급여 정책 설정에 대한 화면 -->

<script>
	function insert_payKind_go(){
		alert("페이종류 등록 고!!");
	}
	
	$(function(){
		
		//급여종류 수정 창 숨기기
		$('#updatePayKindBox').css('display', 'none');
		
		//부서 수정 보여주기
		$(".payKindPopupBtn").click(function(){
// 			var tmp = $(this).siblings('input').val();
// 			$.ajax({
// 				url:'/enovironmentSetting/planPublicRelationsSetting/checkDeptOne',
// 				type:'get',
// 				data: {'tmp_dept_number' : tmp},
// 				success : function(res){
// 					$('#up_dept_number').val(res.dept_number);				
// 					$('#up_dept_name').val(res.dept_name);				
// 					$('#up_dept_membernumber_origin').val(res.dept_membernumber);				
// 					$('#up_dept_tel').val(res.dept_tel);				
// 					$('#up_dept_addr').val(res.dept_addr);	
// 					$('').val
				
// 				},
// 				dataType : 'json'
// 			})
			
			$('#updatePayKindBox').dialog({
				width: 700,
				height: 500,
				modal: true,
				buttons: {
			       "취소": function() {
						$(this).dialog("close");
					}
				},
				close: function() {

				}
			});
		})
	})
</script>

<!-- 급여종류 수정 팝업 -->
<div class="row" id="updatePayKindBox">
	<h4>급여 종류 수정하기</h4>
	<table class="table">
		<tr>
			<th>급여 종류</th>
			<td>??</td>
		</tr>
		<tr>
			<th>공제여부</th>
			<td>
				<select name="">
					<option value="n">공제안함(급여에 더해주는 경우)</option>
					<option value="y">공제함(세금인 경우)</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" class="btn btn-default" onclick="" value="수정하기"/>
			</td>
		</tr>
	</table>
</div>

<h2>급여정책 설정</h2>

<div class="row">
	<div class="col-md-6">
		<h4>급여 종류 설정</h4>
		<table class="table">
			<tr>
				<th>No</th>
				<th>종류</th>
				<th>공제여부</th>
				<th> </th>
			</tr>
			<c:forEach var="payKind" varStatus="status" items="${payKindList}" >
				<form>
					<tr>
						<td>${status.count}</td>
						<td>${payKind.pk_name}</td>
						<c:choose>
							<c:when test="${payKind.pk_tax=='n'}">
								<td>해당없음</td>
							</c:when>
							<c:otherwise>
								<td>공제됨</td>
							</c:otherwise>
						</c:choose>
						<td>
							<c:choose>
							<c:when test="${payKind.pk_number=='pk1'||payKind.pk_number=='pk2'||payKind.pk_number=='pk3'}">
								수정불가
							</c:when>
							<c:otherwise>
								<input type="hidden" name="pk_number" value="${payKind.pk_number}"/>
								<input type="button" value="수정" class="btn btn-default payKindPopupBtn">
							</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</form>
			</c:forEach>
		</table>
	</div>
	<div class="col-md-6">
		<h5>급여 종류 추가하기</h5>
		<form class="form-inline" name="payKindInsertForm">
			<div class="form-group">
				<input type="text" name="" id="" class="form-control inlinePayText"/>
				<select name="pk_name" class="form-control">
					<option value="y">공제 O</option>
					<option value="n">공제 X</option>
				</select>
				<input type="button" class="btn btn-default" onclick="insert_payKind_go();" value="등록"/>		
			</div>
		</form>
	</div>
</div>
<div class="row">
	<h4>직급별 급여정책 설정</h4>
	<p>직급별로 급여를 설정합니다</p>
	<div class="col-md-4">
		<table class="table">
			<tr>
				<th>No</th>
				<th>직급</th>
				<th>시간당 시급</th>
			</tr>
			<c:forEach var="payHourView" varStatus="status" items="${payViewHourList}">
				<tr>
					<form class="form-inline" name="payPolicyHourForm" method="post">
						<td>${status.count}</td>
						<td>${payHourView.position_name}</td>
						<td>
							<input type="hidden" id="" name="" value="${payHourView.pp_number}" class="form-control inlinePayText"/>
							<input type="text" id="" name="" value="${payHourView.pp_pay}" class="form-control inlinePayText"/>
							<input type="button" class="btn btn-default" id="UpdatePayPolicyHourBtn" value="수정"/>
						</td>
					</form>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="col-md-4">
		<table class="table">
			<tr>
				<th>No</th>
				<th>직급</th>
				<th>기본급<br/>(시간당 시급+209시간)</th>
			</tr>
			<c:forEach var="payBasicView" varStatus="status" items="${payViewBasicList}">
				<tr>
					<form class="form-inline" name="payPolicyBasicPosForm" method="post">
						<td>${status.count}</td>
						<td>${payBasicView.position_name}</td>
						<td>
							<fmt:formatNumber value="${payBasicView.pp_pay}" type="currency" currencySymbol="￦"/>
							
						</td>

					</form>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="col-md-4">
		<table class="table">
			<tr>
				<th>No</th>
				<th>직급</th>
				<th>직책수당</th>
			</tr>
			<c:forEach var="payPosView" varStatus="status" items="${payViewPositionList}">
				<tr>
					<form class="form-inline" name="payPolicyPosForm" method="post">
						<td>${status.count}</td>
						<td>${payPosView.position_name}</td>
						<td>
							<input type="hidden" id="" name="" value="${payPosView.pp_number}" class="form-control inlinePayText"/>
							<input type="text" id="" name="" value="${payPosView.pp_pay}" class="form-control inlinePayText"/>
							<input type="button" class="btn btn-default" id="UpdatePayPolicyPosBtn" value="수정"/>
						</td>

					</form>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<div class="row">
	<div class="col-md-6">
		<h4>전체 급여정책 설정</h4>
		<p>회사원 전체에게 동일하게 적용되는 급여를 설정합니다.</p>
		<table class="table">
			<tr>
				<th>No</th>
				<th>급여 정책 이름</th>
				<th colspan="2">금액</th>
			</tr>
			<c:forEach var="payView" varStatus="status" items="${payViewList}">
				<c:if test="${payView.pp_position_number=='position0' && payView.pk_tax=='n'}">
					<tr>
						<form name="payAllPosUpdateForm">
							<td>${status.index }</td>
							<td>${payView.pk_name }</td>
							<td>
								<input type="text" class="form-control" value="${payView.pp_pay}" name="" id=""/>
								<input type="hidden" class="form-control" value="${payView.pp_number}" name="" id=""/>
							</td>
							<td><input type="button" class="btn btn-default" id="UpdatePayPolicyBtn" value="금액 수정"/></td>
						</form>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
	<div class="col-md-6">
		<h4>4대 보험 설정</h4>
		<table class="table">
			<tr>
				<th>No</th>
				<th>이름</th>
				<th>비율</th>
			</tr>
			<c:forEach var="payView" varStatus="status" items="${payViewList}">
				<c:if test="${payView.pp_position_number=='position0' && payView.pk_tax=='y'}">
					<form name="payTaxUpdateForm">
						<tr>
							<td>${status.index }</td>
							<td>${payView.pk_name }</td>
							<td>
								<fmt:formatNumber value="${payView.pp_pay}" pattern="##0.000%" />
								
							</td>
						</tr>
					</form>
				</c:if>
			</c:forEach>
		</table>
	</div>
</div>


<h2>퇴직금정책 설정</h2>

<table class="table">
	<form class="form-inline" action="paySetting_go();" method="post">
		<tr>
			<th>적용되는 경우</th>
			<td>근속연수가 1년 이상(365일)인 경우에 퇴직금을 지급합니다.</td>
		</tr>
		<tr>
			<th>계산 방법</th>
			<td>(직전 3개월치의 급여 / 3개월치의 날짜) * 30일</td>
		</tr>
	</form>
</table>

