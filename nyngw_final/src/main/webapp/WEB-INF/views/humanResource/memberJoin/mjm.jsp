<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script language="javascript">

	//주소 팝업창 열기
	function goPopup(){
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("<%=request.getContextPath()%>/enovironmentSetting/jusoPopupForm","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	}
	
	//주소 팝업창에서 상세주소까지 가져오기
	function jusoCallBack(roadAddrPart1, addrDetail, zipNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.getElementById("mem_addr1").value = roadAddrPart1;
		document.getElementById("mem_addr2").value = addrDetail;
		document.getElementById("mem_zip").value = zipNo;
	}
	
	//id중복체크
	var idch=false;
	var lastKeyword = '';
	function idCheck(){
			var id = $('#mem_id').val();
			$.ajax({
			url : '/humanResource/memberJoin/idCheck',
			type : 'post',
			data : {'id' : id},
			success : function(res){
				var code ="";
				if(res.status =="ok"){
					code+=res.id+"사용 가능합니다.";
					idch=true;
					$('#check').html(code).css('color','blue');
				}else{
					idch=false;
					code+=res.id+"사용 불가능합니다.";
					$('#check').html(code).css('color','red');
				}
			},
			dataType :'json'
		})
	} 

	//회원가입
	function joinMember(){
		//빈칸 체크
		if(!document.getElementById("mem_id").value){
			alert("아이디를 입력해 주세요.");
			return;
		} 
		if(!idch){
			alert("아이디 중복확인을 해주세요");
			return;
		}
		if(!$("#mem_pwd").val()){
			alert("비밀번호를 입력해 주세요.");
			return;
		} 
		if ($('#mem_pwd').val() != $('#mem_pwd_ok').val()) {
			alert("비밀번호가 다릅니다.");
		}
		if(!($("#mem_name").val()&&$("#mem_reg").val()&&$("#mem_tel").val())){
			alert("인적사항을 입력해주세요.");
			return;
		} 
		if(!$("#mem_email").val()){
			alert("이메일을 입력해 주세요.");
			return;
		} 
		if(!($("#mdi_bank").val()&&$("#mdi_bank_account").val())){
			alert("계좌번호를 입력해 주세요.");
			return;
		} 
		
		var memdata = $('#memberForm').serialize();
		$.ajax({
			url : '/humanResource/memberJoin/joinMember',
			type : 'post',
			data : memdata,
			success :function(res){
				var code ="";
				if(res.status == "ok" ){
					alert("사원이 가입되었습니다.");
				}else{
					alert("가입이 실패하였습니다.");
				}
			},
			error : function(res){
				alert("실패");
			},
			dataType :'json'
		})
	} 
	
	$(function() {
		$('#mem_pwd').keyup(function() {
			$('font[name=pwd_check]').text('');
		}); //#user_pass.keyup

		$('#mem_pwd_ok').keyup(function() {
			if ($('#mem_pwd').val() != $('#mem_pwd_ok').val()) {
				$('font[name=pwd_check]').text('');
				$('font[name=pwd_check]').html("암호가 틀렸습니다.");
			} else {
				$('font[name=pwd_check]').text('');
				$('font[name=pwd_check]').html("암호가 맞았습니다.");
			}
		}); //#chpass.keyup
	});
</script>

<h2>사원 등록 페이지</h2>
<p class="docTitleDescription">
	사원을 등록할 수 있습니다.
</p>

<form id="memberForm" method="post">
    <div class="form-group">
		<label for="mem_id" class="labelMemInsert">사원 아이디</label>
		<input type="text" class="form-control inputTypeMemInsertNarrow" id="mem_id" name="mem_id" placeholder="아이디를 입력하세요.">
	  	<input type="button" class="btn btn-default" value="중복확인" onclick="idCheck();"/>
		<span id="check">ID중복확인을 해주세요.</span>
    </div>
    <br/>
    <div class="form-group">
    	<label for="mem_pwd" class="labelMemInsert">패스워드</label>
    	<input type="password" id="mem_pwd" name="mem_pwd" class="form-control inputTypeMemInsert" placeholder="패스워드 입력" />
    </div>
    <br>
    <div class="form-group">
    	<label for="mem_pwd_ok" class="labelMemInsert">패스워드 확인</label>
    	<input type="password" id="mem_pwd_ok" name="mem_pwd_ok" class="form-control inputTypeMemInsert" placeholder="패스워드 확인" />
	<font name="pwd_check" size="2" color="red"></font>
	</div>
	<br>
	<div class="form-group">
    	<label for="mem_name" class="labelMemInsert">이름</label>
		<input type="text" id="mem_name" name="mem_name" class="form-control inputTypeMemInsert" placeholder="이름 입력" />
	</div>
	<br>
	<div class="form-group">
    	<label for="mem_reg" class="labelMemInsert">생년월일</label>
		<input type="text" id="mem_reg" name="mem_reg" class="form-control inputTypeMemInsert" placeholder="생년월일 입력  ex> 800101" />
	</div>
	<br>
	<div class="form-group">
    	<label for="mem_tel" class="labelMemInsert">연락처</label>
		<input type="tel" id="mem_tel"	name="mem_tel" class="form-control inputTypeMemInsert" placeholder="연락처 입력  ex> 010-1234-1234" />
	</div>
	<br>
	<div class="form-group">
    	<label for="mem_dept_number" class="labelMemInsert">부서</label>
		<select name="mem_dept_number" class="form-control docInputSelect">
			<c:forEach items="${depList}" var="depList" begin="2" end="${depList.size()}">
				<option value="${depList.dept_number}">${depList.dept_name}</option>
			</c:forEach>
		</select>
	</div>
	<br>
	<div class="form-group">
		<label for="mem_position_number" class="labelMemInsert">직책</label>
		<select name="mem_position_number" class="btn btn-default docInputSelect">
			<c:forEach items="${posList}" var="posList" begin="3" end="${posList.size()}">
				<option value="${posList.position_number}">${posList.position_name}</option>
			</c:forEach>		
		</select>
	</div>
	<br>
	<div class="form-group">
    	<label for="mem_zip" class="labelMemInsert">우편번호</label>
		<input type="text"  class="form-control inputTypeMemInsertNarrow" id="mem_zip"  name="mem_zip"  placeholder="우편번호 "  readonly />
		<input type="button" onClick="goPopup();" class="btn btn-default" value="주소검색"/>
	</div>
	<br>
	<div class="form-group">
    	<label for="mem_addr1" class="labelMemInsert">주소</label>
		<input type="text" class="form-control inputTypeMemInsert" id="mem_addr1"  name="mem_addr1"  placeholder="도로명주소" readonly />
    </div>
    <br>
    <div class="form-group">	
    	<label for="mem_addr2" class="labelMemInsert">상세 주소</label>
		<input type="text" class="form-control inputTypeMemInsert" id="mem_addr2"  name="mem_addr2"  placeholder="상세주소" />
	</div>
	<br>
	<div class="form-group">
    	<label for="mem_email" class="labelMemInsert">이메일</label>
		<input type="email" id="mem_email" name="mem_email" class="form-control inputTypeMemInsert" placeholder="이메일  입력" />
	</div>
	<br>
	<div class="form-group">
    	<label for="mdi_bank" class="labelMemInsert">은행 선택</label>
		<select id="mdi_bank" name="mdi_bank" class="form-control docInputSelect" >
			<option value="국민">국민</option>
			<option value="KEB">KEB하나</option>
			<option value="NH">NH농협</option>
			<option value="우리">우리</option>
			<option value="신한">신한</option>
			<option value="기업">기업</option>
			<option value="한국 SC">한국 SC</option>
			<option value="부산">부산</option>
			<option value="대구">대구</option>
			<option value="한국씨티">한국씨티</option>
			<option value="경남">경남</option>
			<option value="광주">광주</option>
			<option value="전북">전북</option>
			<option value="산업">산업</option>
			<option value="제주">제주</option>
			<option value="수협">수협</option>
		</select>
	</div>
	<br>
	<div class="form-group">
    	<label for="mdi_bank_account" class="labelMemInsert">계좌번호</label>
		<input type="text" id="mdi_bank_account" name="mdi_bank_account" class="form-control inputTypeMemInsert" placeholder="계좌번호 입력" />
	</div>
	<br>
	<div class="insertJoinBtnWrap textCenter">
		<button onclick="joinMember();" class="btn btn-default">사원가입</button>
	</div>
</form>