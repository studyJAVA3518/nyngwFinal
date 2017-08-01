<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function modifyMember() {

		var memdata = $('#memberDetail').serialize();
		$.ajax({
			url : '/humanResource/joinMemberList/modify',
			type : 'post',
			data : memdata,
			success : function(res) {
				if (res.status == "ok") {
					location.href="/humanResource/joinMemberList/detail?mem_id=${member.mem_id }";
					location.href="/humanResource/joinMemberList/detail?mem_id=${member.mem_id }";
					alert("수정 성공 했습니다.");
				} else {
					location.href="/humanResource/joinMemberList/detail?mem_id=${member.mem_id }";
					location.href="/humanResource/joinMemberList/detail?mem_id=${member.mem_id }";
					alert("수정이 실패 했습니다.");
				}
			},
			error : function() {
				location.href="/humanResource/joinMemberList/detail?mem_id=${member.mem_id }";
				location.href="/humanResource/joinMemberList/detail?mem_id=${member.mem_id }";
				alert("결과를 확인해 주십시오.");
			},
			dataType : 'json'
		})
	}
	function modifyDeleteMember() {
		if (confirm("정말로 퇴사 하시겠습니까?") == true) {
			
			var memdata = $('#memberDetail').serialize();
			$.ajax({
				url : '/humanResource/joinMemberList/retired',
				type : 'post',
				data : memdata,
				success : function(res) {
					location.href="/humanResource/retiredMemberList/rmm";
// 					location.href=result.url;
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

<h2>사원정보 상세 페이지</h2>
<p class="docTitleDescription">
	사원 정보를 확인하고 수정할 수 있습니다.
</p>
<form id="memberDetail" method="post">
	
	<div class="form-group">
		<label for="mem_id" class="labelMemInsert">아이디</label>
		<input type="text" id="mem_id" name="mem_id" class="form-control inputTypeMemInsert" value="${member.mem_id }" readonly /> 
	</div>
	<br/>
	<div class="form-group">
		<label for="mem_name" class="labelMemInsert">이름</label>
		<input type="text" id="mem_name" name="mem_name" class="form-control inputTypeMemInsert" value="${member.mem_name }" readonly /> 
	</div>
	<br> 
	<div class="form-group">
		<label for="mem_reg" class="labelMemInsert">생년월일</label>
		<input type="text" id="mem_reg" name="mem_reg" class="form-control inputTypeMemInsert" value="${member.mem_reg }" readonly /> 
	</div>
	<br> 
	<div class="form-group">
		<label for="mem_tel" class="labelMemInsert">연락처</label>
		<input type="tel" id="mem_tel" name="mem_tel" class="form-control inputTypeMemInsert" placeholder="${member.mem_tel }" value="${member.mem_tel }" readonly />
	</div>
	<br>
	<div class="form-group">
		<label class="labelMemInsert">부서</label>
		현재부서 [${member.dept_name}] 
	</div>
	<br>
	<div class="form-group">
		<label for="mem_dept_number" class="labelMemInsert">수정할 부서 선택</label>
		<select class="form-control docInputSelect" name="mem_dept_number">
			<c:forEach items="${depList}" var="depList">
				<option value="${depList.dept_number}">${depList.dept_name}</option>
			</c:forEach>
		</select> 
	</div>
	<br>
	<div class="form-group">
		<label class="labelMemInsert">직급</label>
		${member.position_name}
	</div>
	<br/>
	<div class="form-group">
		<label for="mem_position_number" class="labelMemInsert">수정할 직급 선택</label>
		<select class="form-control docInputSelect" name="mem_position_number">
			<c:forEach items="${posList}" var="posList" begin="1" end="${posList.size()}">
				<option value="${posList.position_number}">${posList.position_name}</option>
			</c:forEach>
		</select>
	</div>
	<br>
	<div class="form-group">
		<label for="mem_zip" class="labelMemInsert">우편번호</label>
		<input type="text" class="form-control inputTypeMemInsert" id="mem_zip" name="mem_zip" value="${member.mem_zip }" readonly />
	</div>
	<br/>
	<div class="form-group">
		<label for="mem_addr1" class="labelMemInsert">주소</label>
		<input type="text" class="form-control inputTypeMemInsert" id="mem_addr1" name="mem_addr1" value="${member.mem_addr1 }" readonly /> 
	</div>
	<br>
	<div class="form-group">
		<label for="mem_addr2" class="labelMemInsert">상세주소</label>
		<input type="text" class="form-control inputTypeMemInsert" id="mem_addr2" name="mem_addr2" value="${member.mem_addr2 }" readonly />
	</div>
	<br> 
	<div class="form-group">
		<label for="mem_email" class="labelMemInsert">이메일</label>
		<input type="email" id="mem_email" name="mem_email" class="form-control inputTypeMemInsert" value="${member.mem_email }" readonly /> 
	</div>
	<br>
	<div class="form-group">
		<label for="mdi_bank" class="labelMemInsert">거래은행</label>
		<input type="text" id="mdi_bank" name="mdi_bank" class="form-control inputTypeMemInsert" value="${member.mdi_bank }" readonly/> 
	</div>
	<br/>
	<div class="form-group">
		<label for="mdi_bank_account" class="labelMemInsert">게좌번호</label>
		<input type="text" id="mdi_bank_account" name="mdi_bank_account" class="form-control inputTypeMemInsert" value="${member.mdi_bank_account }" readonly/> 
	</div>
	<br>
	<div class="insertJoinBtnWrap textCenter">
		<button onclick="modifyMember();" class="btn btn-default">사원 수정</button>
		<button onclick="modifyDeleteMember();" class="btn btn-default">퇴직 처리</button>
	</div>
</form>
