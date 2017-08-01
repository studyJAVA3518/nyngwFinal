<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!-- 환경설정관리 -> 기획홍보부 설정 ->회사 직급 설정에 대한 화면 -->




<h2>회사 직급 설정</h2>
<p class="docTitleDescription">
	회사의 직급을 등록하거나 수정하실 수 있으며, 위 아래 버튼을 눌러 위치를 변경하실 수 있습니다.<br/>
	ex) 부장, 과장, 대리, 사원 등
</p>
<div class="row insertJoinBtnWrap textCenter">
	<h4>직급 만들기</h4>
	<div class="col-md-12">
		<form class="inline-form" name="positionInsertForm">
			<table class="table textCenter tableGray">
				<tr>
					<th>직급명</th>
					<td>
						<input type="text" id="position_name" name="position_name" class="form-control"/>
					</td>
				</tr>
				<tr>
					<th>직급레벨</th>
					<td>
						<select class="form-control" name="position_level">
							<c:forEach  var="posLevel" items="${posList}" begin="1" end="${posList.size()}" step="1">
								<option value="${posLevel.position_level+5}" class="form-control">${posLevel.position_name}</option>
							</c:forEach>
						</select>
						<p>
							※위치하고자 하는 직급의 상위 레벨을 선택하세요.<br/>
							(ex. 사원 밑의 직급을 등록하려면 선택바에서 사원을 선택하세요)
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="textRight">
						<input type="button" value="직급 등록" class="btn btn-default" onclick="insertPosition_go();"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

<br>
<div class="row insertJoinBtnWrap">
	<h4>직급 리스트</h4>
	<p class="docTitleDescription">총 ${positionCount}개의 직급이 등록되어 있습니다.</p>
	<div class="col-md-12">
		<form class="inline-form" name="positionListForm">
			<table class="table textCenter tableGray">
				<tr>
					<th>No.</th>
					<th>직급</th>
					<th>위치변경</th>
					<th>관리</th>
				</tr>
				<c:forEach var="position" items="${posList}" begin="1" end="${posList.size()}" step="1" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${position.position_name}</td>
						<td>
							<a href="<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/updateLevelUp?tmp_position_number=${position.position_number}" class="btn btn-default">▲ 위로</a>
							<a href="<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/updateLevelDown?tmp_position_number=${position.position_number}" class="btn btn-default">▼ 아래로</a>
						</td>
						<td>
							<input type="hidden" value="${position.position_number}" name="tmp_position_number" id="tmp_position_number"/>
							<input type="button" value="직급 이름 수정" class="btn btn-default updatePositionFormBtn"/>
							<a href="<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/companyPositionDelete?tmp_position_number=${position.position_number}" value="직급 삭제" class="btn btn-default">직급 삭제</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
</div>


<div class="updatePositionBox">
	<h4>직급 수정하기</h4>
	<form name="updatePositionForm" method="post">
		<table class="table textCenter tableGray">
			<tr>
				<th>직급 이름</th>
				<td>
					<div class="form-group">
						<input class="form-control" type="text" id="up_position_name" name="up_position_name"/>
						<input type="hidden" id="up_position_number" name="up_position_number"/>
					</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="직급 수정" class="btn btn-default" onclick="updatePosition_go();"/>
				</td>
			</tr>
		</table>
	</form>
</div>

<script>
	
	//직급 등록하는 함수
	function insertPosition_go(){
		
		if(!document.getElementById("position_name").value){
			alert("직급 이름을 입력해 주세요.");
			return;
		}
		
		document.positionInsertForm.method="post";
		document.positionInsertForm.action="<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/companyPositionInsert";
		document.positionInsertForm.submit();
	}
	
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
				data: {'tmp_position_number' : tmp},
				success : function(res){
					$('#up_position_number').val(res.position_number);				
					$('#up_position_name').val(res.position_name);				
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
		
		if(!document.getElementById("up_position_name").value){
			alert("직급 이름을 입력해 주세요.");
			return;
		}
		
		document.updatePositionForm.method="post";
		document.updatePositionForm.action="<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/companyPositionUpdate";
		document.updatePositionForm.submit();
		
	}
	
</script>