<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script language="javascript">
//	document.domain = "jlm.jsp";
function goPopup(){
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/juso","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
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
			var code ="";
			if(res.status =="ok"){
				code+=res.id+"사용 가능합니다.";
				$('#check').html(code).css('color','blue');
			}else{
				alert("aaa");
				code+=res.id+"사용 불가능합니다.";
				$('#check').html(code).css('color','red');
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
	<table class="table table-bordered">
		<tr>
			<td>사원등록</td>
		</tr>
	</table>
    <div class="input-group">
      <input type="text" class="form-control" id="mem_id" name="mem_id" placeholder="아이디를 입력하세요.">
      <span class="input-group-btn">
        <input type="button" class="btn btn-default" value="중복확인" onclick="idCheck();"/>
   	  	<span id="check">중복확인을 해줘요</span>
      </span>
    </div>
    <br>
    <br>
    <input type="password" id="mem_pwd" name="mem_pwd" class="form-control" placeholder="패스워드 입력" />
    <br>
    <input type="password" id="mem_pwd_ok" name="mem_pwd_ok" class="form-control" placeholder="패스워드 확인" />
	<font name="pwd_check" size="2" color="red"></font>
	<br>
	<br>
	<input type="text" id="mem_name" name="mem_name" class="form-control" placeholder="이름 입력" />
	<br>
	<input type="text" id="mem_reg" name="mem_reg" class="form-control" placeholder="생년월일 입력  ex> 800101" />
	<br>
	<input type="tel" id="mem_tel"	name="mem_tel" class="form-control" placeholder="연락처 입력  ex> 010-1234-1234" />
	<br>
	<input type="text" id="mem_dept_number"	name="mem_dept_number" class="form-control" placeholder="부서번호 입력" />
	<br>
	<input type="text" id="mem_position_number"	name="mem_position_number" class="form-control"	placeholder="직급번호 입력" />
	<br>
	<div class="input-group">
		<input type="text"  class="form-control" id="zipNo"  name="mem_zip"  placeholder="우편번호 "  readonly />
		 <span class="input-group-btn">			
			<input type="button" onClick="goPopup();" class="form-control" value="주소검색"/>
		</span>
	</div>
		<input type="text" class="form-control" id="roadAddrPart1"  name="mem_addr1"  placeholder="도로명주소" readonly />
		<input type="text" class="form-control" id="addrDetail"  name="mem_addr2"  placeholder="상세주소" readonly />
	<br>
	<input type="email" id="mem_email" name="mem_email" class="form-control" placeholder="이메일  입력" />
	<br>
	<input type="text" id="mem_tel" name="mem_tel" class="form-control" placeholder="연락처 입력" />
	<br>
	<input type="text" id="mdi_bank" name="mdi_bank" class="form-control" placeholder="거래은행 입력" />
	<input type="text" id="mdi_bank_account" name="mdi_bank_account" class="form-control" placeholder="계좌번호 입력" />
	<br>
	
	<button onclick="joinMember();" class="btn btn-default">사원가입</button>
</form>