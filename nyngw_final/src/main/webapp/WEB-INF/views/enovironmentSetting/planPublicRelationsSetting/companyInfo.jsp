<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- 환경설정관리 -> 기획홍보부 설정 -> 회사정보 설정에 대한 화면 -->

<script language="javascript">
	// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
<%-- 	document.domain = "<%=request.getContextPath()%>/enovironmentSetting/jusoPopupForm"; --%>
	
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(enovironmentSetting/jusoPopupForm)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("<%=request.getContextPath()%>/enovironmentSetting/jusoPopupForm","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		
		// 모바일 필요없지만..
		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}
	
	function jusoCallBack(roadAddrPart1, addrDetail, zipNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.getElementById("roadAddrPart1").value = roadAddrPart1;
		document.getElementById("addrDetail").value = addrDetail;
		document.getElementById("zipNo").value = zipNo;
	}
	
	function comInfoUpdate(){
		
		if(!document.getElementById("company_name").value){
			alert("회사 이름을 입력해야 합니다.");
			return;
		}
		if(!document.getElementById("company_tel").value){
			alert("회사 전화번호를 입력해야 합니다.");
			return;
		}
		if(!document.getElementById("roadAddrPart1").value){
			alert("회사 주소를 입력해야 합니다.");
			return;
		}
		if(!document.getElementById("addrDetail").value){
			alert("회사 상세주소를 입력해야 합니다.");
			return;
		}
		
		document.comInfo.action= "<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/companyInfo";
		document.comInfo.method = "post";
		document.comInfo.submit();
	}
	
	function updateLogo_go(){
		document.comLogo.action= "<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/updateLogoFile";
		document.comLogo.method = "post";
		document.comLogo.submit();
	}
	
	/*  FileReader 라는 Javascript 객체가 생기면서 최신 브라우저에서는 서버에 이미지를 업로드 안해도 이미지 미리보기 기능을 구현할 수 있다.*/

	$(function() {
		$("#logoPre").css('visibility','hidden');
	    $("#logoFile").on('change', function(){
	    	$("#logoPre").css('visibility','visible');
	    	readURL(this);
	    });
	});
	
	function readURL(input) {
	    if (input.files && input.files[0]) {
	    var reader = new FileReader();
	
	    reader.onload = function (e) {
	            $('#logoPre').attr('src', e.target.result);
	        }
	
	      reader.readAsDataURL(input.files[0]);
	    }
	}
	 
</script>
<style>
	#logoPre {
		height : 100px;
	}
</style>

<h2>회사 정보 설정</h2>
<h4>회사 로고 등록 및 변경</h4>
<table class="table textCenter tableGray">
	<form class="form-inline" method="post" name="comLogo" enctype="multipart/form-data">
		<tr>
			<td colspan="2">
				<label for="basic">기본 이미지 사용</label>
				<input type="radio" name="logo" value="basic" id="basic" checked/>  &nbsp;&nbsp;&nbsp;
				<label for="custom">업로드 로고(CI) 사용</label>
				<input type="radio" name="logo" value="custom" id="basic"/> 
				<span class="logoDesc">※ 업로드 로고(CI)를 사용할 경우 업로드할 로고화면의 배경부분을 투명하게 처리해야 합니다.</span>
			<td>
		</tr>
		<tr>
			<th>로고(CI) 변경</th>
			<td><input class="form-control" type="file" id="logoFile" name="logoFile"/>
				<input class="form-control" type="hidden" id="company_number2" name="company_number2" value="${companyInfo.company_number}"/>
				<span class="logoDesc">(최적 사이즈 220*80)</span>
			</td>
		</tr>
		<tr>
			<th>미리보기</th>
			<td><img id="logoPre" src="#" alt="your image" width="50%" height="50%"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" class="btn btn-default" value="로고 수정" class="btn btn-default" onclick="updateLogo_go();"/>
			</td>
		</tr>
	</form>
</table>
<br>
<h4>회사 정보 등록</h4>
<div id="callBackDiv">
<table class="table tableGray">
	<form class="form-inline" id="form" method="post" name="comInfo">
		<tr>
			<th>회사 이름</th>
			<td>
				<div class="form-group">
					<input type="text" id="company_name" name="company_name" class="form-control" placeholder="정보 없음" value="${companyInfo.company_name}"/>
					<input type="hidden" id="company_number" name="company_number" value="${companyInfo.company_number}"/>
				</div>
			</td>
		</tr>
		<tr>
			<th>회사 전화번호</th>
			<td>
				<div class="form-group">
					<input type="text" id="company_tel" name="company_tel" class="form-control" placeholder="정보 없음" value="${companyInfo.company_tel}"/>
				</div>
			</td>
		</tr>
		<tr>
			<th>회사 주소</th>
			<td>
				<div class="form-group">
					<div id="list"></div>
					<div class="form-group">
						<label for="mem_zip" class="labelMemInsert textCenter">우편번호</label>
						<input type="text"  class="form-control inputTypeMemInsertNarrow" id="zipNo"  name="zipNo"  placeholder="우편번호 "  readonly  value="${companyInfo.company_zip}"/>
						<input type="button" onClick="goPopup();" class="btn btn-default" value="주소검색"/>
					</div>
					<br>
					<div class="form-group">
						<label for="mem_addr1" class="labelMemInsert textCenter">주소</label>
						<input type="text" class="form-control inputTypeMemInsert" id="roadAddrPart1"  name="roadAddrPart1"  placeholder="도로명주소" readonly value="${companyInfo.company_addr1}" />
					</div>
				    <br>
				    <div class="form-group">   
						<label for="mem_addr2" class="labelMemInsert textCenter">상세 주소</label>
						<input type="text" class="form-control inputTypeMemInsert" id="addrDetail"  name="addrDetail"  placeholder="상세주소" value="${companyInfo.company_addr2}" />
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="회사 정보 등록/수정" class="btn btn-default" onclick="comInfoUpdate();"/>
			</td>
		</tr>
	</form>
</table>
</div>
