<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 환경설정관리 -> 기획홍보부 설정 -> 급여 정책 설정에 대한 화면 -->

<script>
	//급여종류 등록
	function insert_payKind_go(){
		
		if(!document.getElementById("pk_name").value){
			alert("급여 이름을 입력해야 합니다.");
			return;
		}
		
		document.payKindInsertForm.action= "<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/insertPayKind";
		document.payKindInsertForm.method = "post";
		document.payKindInsertForm.submit();
	}
	
	//급여종류 수정
	function update_payKind_go(){
		
		if(!document.getElementById("up_pk_number").value){
			alert("급여 이름을 입력해야 합니다.");
			return;
		}
		if(!document.getElementById("up_pk_name").value){
			alert("급여 이름을 입력해야 합니다.");
			return;
		}
		if(!document.getElementById("up_pk_tax").value){
			alert("급여 이름을 입력해야 합니다.");
			return;
		}
		
		document.payKindUpdateForm.action= "<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/updatePayKind";
		document.payKindUpdateForm.method = "post";
		document.payKindUpdateForm.submit();
	}
	
	
	
	//급여종류 팝업창 열기(데이터넘기기)
	$(function(){
		
		//급여종류 수정 창 숨기기
		$('#updatePayKindBox').css('display', 'none');
		
		//급여종류수정팝업 보여주기
		$(".payKindPopupBtn").click(function(){
			var tmp = $(this).siblings('input').val();
			$.ajax({
				url:'/enovironmentSetting/planPublicRelationsSetting/checkPayKindOne',
				type:'get',
				data: {'tmp_pk_number' : tmp},
				success : function(res){
					$('#up_pk_number').val(res.pk_number);				
					$('#up_pk_name').val(res.pk_name);				
					$('#up_pk_tax').children().each(function(){
						if($(this).val()==res.pk_tax){
							$(this).prop('selected','selected');
						}
					});
				},
				dataType : 'json'
			})
			
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
	<table class="table textCenter">
		<form class="form-inline" name="payKindUpdateForm">
			<tr>
				<th>급여 종류</th>
				<td>
					<input type="hidden" id="up_pk_number" name="up_pk_number" class="form-control"/>
					<input type="text" id="up_pk_name" name="up_pk_name" class="form-control"/>
				</td>
			</tr>
			<tr>
				<th>공제여부</th>
				<td>
					<select name="up_pk_tax" id="up_pk_tax" class="form-control">
						<option value="n">공제 X(급여인 경우)</option>
						<option value="y">공제 O(세금인 경우)</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" class="btn btn-default" onclick="update_payKind_go();" value="수정하기"/>
				</td>
			</tr>
		</form>
	</table>
</div>

<h2>급여종류 등록 및 삭제</h2>

<div class="row">
	<div class="col-md-6">
		
		<h4>급여 종류 설정</h4>
		<table class="table textCenter">
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
								<a href="<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/deletePayKind?del_pk_number=${payKind.pk_number}" class="btn btn-default">삭제</a>
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
				<input type="text" name="pk_name" id="pk_name" class="form-control inlinePayText"/>
				<select name="pk_tax" class="form-control">
					<option value="n">공제 X(급여인 경우)</option>
					<option value="y">공제 O(세금인 경우)</option>
				</select>
				<input type="button" class="btn btn-default" onclick="insert_payKind_go();" value="등록"/>		
			</div>
		</form>
	</div>
</div>


<h2>퇴직금정책 설정</h2>

<table class="table textCenter">
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

