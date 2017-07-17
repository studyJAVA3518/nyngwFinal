<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- 환경설정관리 -> 기획홍보부 설정 ->회사 직급 설정에 대한 화면 -->




<h2>회사 직급 설정</h2>
<p>
	회사의 직급을 등록하거나 수정하실 수 있으며, 위 아래 버튼을 눌러 위치를 변경하실 수 있습니다.<br/>
	ex) 부장, 과장, 대리, 사원 등
</p>
<div class="row">
	<h4>직급 만들기</h4>
	<div class="col-md-12">
		<table class="table">
			<form class="inline-form">
				<tr>
					<th>직급명</th>
					<td>
						<input type="text" id="position_name" name="position_name">
						<input type="submit" value="직급 등록" action="insertPosition_go();"/>
					</td>
				</tr>
			</form>
		</table>
	</div>
</div>

<div class="row">
	<h4>직급 만들기</h4>
	<div class="col-md-12">
		<table class="table">
			<form class="inline-form">
				<tr>
					<th>No</th>
					<th>직급</th>
					<th>위치변경</th>
					<th>관리</th>
				</tr>
				
				<tr>
					<td>1</td>
					<td>사장</td>
					<td>
						<input type="submit" value="▲위로" class="btn btn-default" action="upPoNum_go();"/> 
						<input type="submit" value="▲아래로" class="btn btn-default" action="downPoNum_go();"/> 
					</td>
					<td>
						<input type="submit" value="직급 수정" id="updatePositionFormBtn"/>
						<input type="submit" value="직급 삭제" onclick="deletePosition_go();"/>
					</td>
				</tr>
			</form>
		</table>
	</div>
</div>


<div class="updatePositionBox">
	<h4>직급 수정하기</h4>
	<table class="table">
		<form action="updatePositionForm" method="post">
			<tr>
				<th>직급 이름</th>
				<td>
					<div class="form-group">
						<input type="text" id="position_name" name="position_name"/>
					</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="부서 수정"/>
				</td>
			</tr>
		</form>
	</table>
</div>

<script>
	
	//직급 수정하는 팝업 열어주는 함수
	//부서 수정 창 숨기기
	$(function(){
	
		//부서 수정 창 숨기기
		$('.updatePositionBox').css('display', 'none');
		
		//부서 수정 보여주기
		$(".updatePositionFormBtn").click(function(){
			var tmp = $(this).siblings('input').val();
			$.ajax({
				url:'/enovironmentSetting/planPublicRelationsSetting/checkPositionOne',
				type:'get',
				data: {'해당 input태그 name 적기' : tmp},
				success : function(res){
					$('#up_position_number').val(res.dept_number);				
					$('#up_position_name').val(res.dept_name);				
				},
				dataType : 'json'
			})
			
			$('.updatePositionBox').dialog({
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
	
	//직급 수정하는 함수
	function updatePosition_go(){
		
	}
	
	//직급 삭제하는 함수
	function deletePosition_go(){
		alert("직급삭제!!");
	}
</script>