<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 환경설정관리 -> 기획홍보부 설정 ->회사 부서 설정에 대한 화면 -->

<script>

//부서 등록하기
function insertDept_go(){
	document.insertDeptForm.action= "<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/companyDepartmentInsert";
	document.insertDeptForm.method = "post";
	document.insertDeptForm.submit();
}

//부서 수정하기
function updateDept_go(){
	document.updateDeptForm.action= "<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/companyDepartmentUpdate";
	document.updateDeptForm.method = "post";
	document.updateDeptForm.submit();
}

//부서 삭제하기
function deleteDept_go(){
	
}

$(function(){
	
	//부서 수정 창 숨기기
	$('.updateDeptBox').css('display', 'none');
	 
	//부서 수정 보여주기
	$(".updateDeptFormBtn").click(function(){
		$.ajax({
			url:'/enovironmentSetting/planPublicRelationsSetting/checkDeptOne',
			type:'get',
			data: {"tmp_dept_number":tmp_dept_number},
			success : function(res){
		alert("!!!!");
				$('#up_dept_name').val(res.dept_name);				
				$('#up_dept_membernumber').val(res.dept_membernumber);				
				$('#up_dept_tel').val(res.dept_tel);				
				$('#up_dept_addr').val(res.dept_addr);				
			},
			dataType : 'json'
		})
		
		$('.updateDeptBox').dialog({
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
	})
})

</script>


<h2>회사 부서 설정</h2>
<p>
	회사의 부서를 등록하는 메뉴입니다.
	부서를 만들때 자동으로 부서 메뉴도 생성됩니다.
</p>
<div class="row">
	<h4>부서 만들기</h4>
	<table class="table">
		<form method="post" name="insertDeptForm">
			<tr>
				<th>부서 이름</th>
				<td>
					<div class="form-group">
						<input type="text" id="dept_name" name="dept_name"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>부서장 사원번호</th>
				<td>
					<div class="form-group">
						<select name="dept_membernumber">
							<c:forEach var="member" items="${upperMemList}">
								<option value="${member.mem_number}">
									${member.mem_number}&#09;${member.mem_name}&#09;${member.position_name}
								</option>
							</c:forEach>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<th>부서 연락처</th>
				<td>
					<div class="form-group">
						<input type="text" id="dept_tel" name="dept_tel"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>부서 위치</th>
				<td>
					<div class="form-group">
						<input type="text" id="dept_addr" name="dept_addr"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>상위 부서 선택</th>
				<td>
					<div class="form-group">
						<select name="dept_parents">
							<c:forEach var="dept" items="${dvList}">
								<option value="${dept.dept_number}">
									${dept.dept_name}
								</option>
							</c:forEach>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="부서 등록" onclick="insertDept_go(this.form);"/>
				</td>
			</tr>
		</form>
	</table>
</div>

<div class="row">
	<h4>부서 리스트 (총 n 개의 부서가 등록되어 있습니다.)</h4>
	
	<c:forEach var="depart" items="${dvList}">
		<div class="row">
			<div class="col-md-3">${depart.dept_name}</div>
			<div class="col-md-9">
				<input type="button" value="수정" class="btn btn-default updateDeptFormBtn" />
				<input type="hidden" id="tmp_dept_number" name="tmp_dept_number" value="${depart.dept_number }"/>
				<a href="/enovironmentSetting/planPublicRelationsSetting/companyDepartDelete?deleteDeptNum=${depart.dept_number}" class="btn btn-default">삭제</a>
			</div>
		</div>
	</c:forEach>
</div>

<div class="updateDeptBox">
	<!-- 부서를 수정하는 팝업창 -->
	<h4>부서 수정하기</h4>
	<table class="table">
		<form method="post" name="updateDeptForm">
			<tr>
				<th>부서 이름</th>
				<td>
					<div class="form-group">
						<input type="text" id="up_dept_name" name="up_dept_name"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>부서장 사원번호</th>
				<td>
					<div class="form-group">
						<input type="text" readonly/>
						<br/>
						수정하기  
						<select name="up_dept_membernumber">
							<c:forEach var="member" items="${upperMemList}">
								<option value="${member.mem_number}">
									${member.mem_number}&#09;${member.mem_name}&#09;${member.position_name}
								</option>
							</c:forEach>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<th>부서 연락처</th>
				<td>
					<div class="form-group">
						<input type="text" id="up_dept_tel" name="up_dept_tel" />
					</div>
				</td>
			</tr>
			<tr>
				<th>부서 위치</th>
				<td>
					<div class="form-group">
						<input type="text" id="up_dept_addr" name="up_dept_addr"/>
					</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="button" value="부서 수정" id="updateDeptBtn" onclick="updateDept_go();"/>
				</td>
			</tr>
		</form>
	</table>
</div>

