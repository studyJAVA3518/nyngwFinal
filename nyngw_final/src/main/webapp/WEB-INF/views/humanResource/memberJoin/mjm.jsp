<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//	document.domain = "jlm.jsp";
function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("juso.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 필요없지만..
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.form.roadFullAddr.value = roadFullAddr;
		document.form.roadAddrPart1.value = roadAddrPart1;
		document.form.roadAddrPart2.value = roadAddrPart2;
		document.form.addrDetail.value = addrDetail;
		document.form.engAddr.value = engAddr;
		document.form.jibunAddr.value = jibunAddr;
		document.form.zipNo.value = zipNo;
		document.form.admCd.value = admCd;
		document.form.rnMgtSn.value = rnMgtSn;
		document.form.bdMgtSn.value = bdMgtSn;
		document.form.detBdNmList.value = detBdNmList;
		/** 2017년 2월 추가제공 **/
		document.form.bdNm.value = bdNm;
		document.form.bdKdcd.value = bdKdcd;
		document.form.siNm.value = siNm;
		document.form.sggNm.value = sggNm;
		document.form.emdNm.value = emdNm;
		document.form.liNm.value = liNm;
		document.form.rn.value = rn;
		document.form.udrtYn.value = udrtYn;
		document.form.buldMnnm.value = buldMnnm;
		document.form.buldSlno.value = buldSlno;
		document.form.mtYn.value = mtYn;
		document.form.lnbrMnnm.value = lnbrMnnm;
		document.form.lnbrSlno.value = lnbrSlno;
		/** 2017년 3월 추가제공 **/
		document.form.emdNo.value = emdNo;
		
}

function idCheck(){
		var id = $('#mem_id').val();
		$.ajax({
		url : '/humanResource/memberJoin/idCheck',
		type : 'post',
		data : {'id' : id},
		success : function(res){
			alert(res.id);
			var code ="";
			if(res.status =="ok"){
				code+=res.id+"사용 가능합니다.";
				$('span:first').html(code).css('color','blue');
			}else{
				code+=res.id+"사용 불가능합니다.";
				$('span:first').html(code).css('color','red');
			}
		},
		dataType :'json'
	})
} 

function joinMember(){
		var memdata = $('#memberForm').serialize();
		$.ajax({
		url : '/humanResource/memberJoin/joinMember',
		type : 'post',
		data : memdata,
		success :function(res){
			var code ="";
			if(res.status == "ok" ){
				code+= res.id+"님 가입 축하";
			}else{
				code+= "가입이 실패했습니다.";
			}
			$('span:last').html(code).css('color','orange');
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
				$('font[name=pwd_check]').html("암호틀림");
			} else {
				$('font[name=pwd_check]').text('');
				$('font[name=pwd_check]').html("암호맞음");
			}
		}); //#chpass.keyup
	});
</script>

<form id="memberForm" method="post">
	<table>
		<tr>
			<td>회원가입</td>
		</tr>
	</table>
	
	<table>
		<tr>
			<td><input type="text" id="mem_id" name="mem_id"
				class="form-control" placeholder="아이디 입력" /></td>
			<td><input type="button" id="idcheck" onclick="idCheck();"  value="중복검사"></td>
		</tr>
		<tr>
			<td><span>아이디 중복확인을 해주세요.</span></td>
		</tr>
		<tr>
			<td><input type="password" id="mem_pwd" name="mem_pwd"
				class="form-control" placeholder="패스워드 입력" /></td>
		</tr>
		<tr>
			<td><input type="password" id="mem_pwd_ok" name="mem_pwd_ok"
				class="form-control" placeholder="패스워드 확인" /></td>
				<td><font name="pwd_check" size="2" color="red"></font></td>
		</tr>
		<tr>
			<td><input type="text" id="mem_name" name="mem_name"
				class="form-control" placeholder="이름 입력" /></td>
		</tr>
		<tr>
			<td><input type="date" id="mem_reg" name="mem_reg"
				class="form-control" placeholder="생년월일 입력" /></td>
		</tr>
		<tr><td><input type="button" onClick="goPopup();" class="form-control" value="주소검색"/></td></tr>
		<tr><td><input type="text"   id="zipNo"  name="mem_zip"  placeholder="우편번호 " /></td></tr>
		<tr><td><input type="text"  id="roadAddrPart1"  name="mem_addr1"  placeholder="도로명주소"/></td></tr>
		<tr><td><input type="text"  id="addrDetail"  name="mem_addr2"  placeholder="상세주소"/></td></tr>
		<tr>
			<td><input type="email" id="mem_email" name="mem_email"
				class="form-control" placeholder="이메일  입력" /></td>
		</tr>
		<tr>
			<td><input type="text" id="mem_tel" name="mem_tel"
				class="form-control" placeholder="연락처 입력" /></td>
		</tr>
		<tr>
			<td><input type="text" id="mdi_bank" name="mdi_bank"
				class="form-control" placeholder="거래은행 입력" /></td>
		</tr>
		<tr>
			<td><input type="text" id="mdi_bank_account"
				name="mdi_bank_account" class="form-control" placeholder="계좌번호 입력" /></td>
		</tr>
		<tr>
			<td><input type="tel" id="mem_tel"
				name="mem_tel" class="form-control" placeholder="연락처 입력" /></td>
		</tr>
		<tr>
			<td><input type="text" id="mem_dept_number"
				name="mem_dept_number" class="form-control" placeholder="부서번호 입력" /></td>
		</tr>
		<tr>
			<td><input type="text" id="mem_position_number"
				name="mem_position_number" class="form-control"
				placeholder="직급번호 입력" /></td>
		</tr>
	</table>
	<button onclick="joinMember();" >사원가입</button>
</form>