<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					location.href="/humanResource/joinMemberList/detail?mem_id=${member.mem_id }";
					location.href="/humanResource/joinMemberList/detail?mem_id=${member.mem_id }";
					alert("수정 성공");
				} else {
					location.href="/humanResource/joinMemberList/detail?mem_id=${member.mem_id }";
					location.href="/humanResource/joinMemberList/detail?mem_id=${member.mem_id }";
					alert("수정이 실패하였습니다.");
				}
			},
			error : function() {
				location.href="/humanResource/joinMemberList/detail?mem_id=${member.mem_id }";
				location.href="/humanResource/joinMemberList/detail?mem_id=${member.mem_id }";
				alert("결과를 확인해주세요.");
			},
			dataType : 'json'
		})
	}
	function modifyDeleteMember() {
		if (confirm("정말로 퇴사하시겠습니까?") == true) {
			
			var memdata = $('#memberDetail').serialize();
			$.ajax({
				url : '/humanResource/joinMemberList/retired',
				type : 'post',
				data : memdata,
				success : function(res) {
					alert("화면전환만들어야되는데...");
					location.href=result.url;
				},
				error : function() {
					alert("회원 퇴사처리가 실패했습니다.");
					location.href="/humanResource/joinMemberList/detail";
				},
				dataType : 'json'
			})
		}
	}
</script>
<form id="memberDetail" method="post">
	<h2>사원정보</h2>
	<br> 
	아이디 <input type="text" id="mem_id" name="mem_id" class="form-control" value="${member.mem_id }" readonly /> 
	이름 <input type="text" id="mem_name" name="mem_name" class="form-control" value="${member.mem_name }" readonly /> 
	<br> 
	생년월일<input type="text" id="mem_reg" name="mem_reg" class="form-control" value="${member.mem_reg }" readonly /> 
	<br> 
	연락처<input type="tel" id="mem_tel" name="mem_tel" class="form-control" placeholder="${member.mem_tel }" value="${member.mem_tel }" readonly />
	<br>
	부서 ${member.dept_name} 수정할 부서 선택
<%-- 	<input type="tel" class="form-control" placeholder="${member.dept_name }" value="${member.dept_name }" readonly />  --%>
	<select class="form-control" name="mem_dept_number">
		<c:forEach items="${depList}" var="depList">
			<option value="${depList.dept_number}">${depList.dept_name}</option>
		</c:forEach>
	</select> 
	<br>
	직급 ${member.position_name} 수정할 직급 선택
<%-- 	<input type="tel" class="form-control" placeholder="${member.position_name }" value="${member.position_name }" readonly />  --%>
	<select class="form-control" name="mem_position_number">
		<c:forEach items="${posList}" var="posList" begin="1" end="${posList.size()}">
			<option value="${posList.position_number}">${posList.position_name}</option>
		</c:forEach>
	</select>
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
	거래은행<input type="text" id="mdi_bank" name="mdi_bank" class="form-control" value="${member.mdi_bank }" readonly/> 
	게좌번호<input type="text" id="mdi_bank_account" name="mdi_bank_account" class="form-control" value="${member.mdi_bank_account }" readonly/> 
	<br>

	<button onclick="modifyMember();" class="btn btn-default">사원수정</button>
	<button onclick="modifyDeleteMember();" class="btn btn-default">byebye</button>
</form>
