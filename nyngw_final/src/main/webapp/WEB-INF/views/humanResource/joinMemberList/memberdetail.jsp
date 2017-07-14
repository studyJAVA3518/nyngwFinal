<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script>
	function modifyMember() {
		if (!($("#mdi_bank").val() && $("#mdi_bank_account").val())) {
			alert("미입력 사항");
			return;
		}

		var memdata = $('#memberDetail').serialize();
		$.ajax({
			url : '/humanResource/joinMemberList/modify',
			type : 'post',
			data : memdata,
			success : function(res) {
				if (res.status == "ok") {
					alert("수정 성공");
				} else {
					alert("수정이 실패하였습니다.");
				}
			},
			error : function() {
				alert("ㅈ 망이다.");
			},
			dataType : 'json'
		})
	}

	function modifyDeleteMember() {
		if (confirm("퇴사?????") == true) {
			
			var memdata = $('#memberDetail').serialize();
			$.ajax({
				url : '/humanResource/joinMemberList/retired',
				type : 'post',
				data : memdata,
				success : function(res) {
					alert("화면전환만들어야되는데...");
				},
				error : function() {
					alert("ㅈ 망이다.");
				},
				dataType : 'json'
			})
		}
	}
</script>
<form id="memberDetail" method="post">
	<table class="table table-bordered">
		<tr>
			<td>사원정보</td>
		</tr>
	</table>
	<br> 
	아이디 <input type="text" id="mem_id" name="mem_id" class="form-control" value="${member.mem_id }" readonly /> 
	이름 <input type="text" id="mem_name" name="mem_name" class="form-control" value="${member.mem_name }" readonly /> 
	<br> 
	생년월일<input type="text" id="mem_reg" name="mem_reg" class="form-control" value="${member.mem_reg }" readonly /> 
	<br> 
	연락처<input type="tel" id="mem_tel" name="mem_tel" class="form-control" placeholder="${member.mem_tel }" value="${member.mem_tel }" readonly />
	<br>
	부서<input type="tel" class="form-control" placeholder="${member.dept_name }" value="${member.dept_name }" readonly /> 
	<br>
	직급<input type="tel" class="form-control" placeholder="${member.position_name }" value="${member.position_name }" readonly /> 
	<br>
	<div class="input-group">
		주소 <input type="text" class="form-control" id="mem_zip" name="mem_zip" value="${member.mem_zip }" readonly />
	</div>
	<div class="input-group">
		<input type="text" class="form-control" id="mem_addr1" name="mem_addr1" value="${member.mem_addr1 }" readonly /> 
		상세주소 <input	type="text" class="form-control" id="mem_addr2" name="mem_addr2" value="${member.mem_addr2 }" readonly />
	</div>
	<br> 
	이메일<input type="email" id="mem_email" name="mem_email" class="form-control" value="${member.mem_email }" readonly /> 
	<br>
	거래은행<input type="text" id="mdi_bank" name="mdi_bank" class="form-control" value="${member.mdi_bank }" /> 
	게좌번호<input type="text" id="mdi_bank_account" name="mdi_bank_account" class="form-control" value="${member.mdi_bank_account }" /> 
	<br>

	<button onclick="modifyMember();" class="btn btn-default">사원수정</button>
	<button onclick="modifyDeleteMember();" class="btn btn-default">byebye</button>
</form>
