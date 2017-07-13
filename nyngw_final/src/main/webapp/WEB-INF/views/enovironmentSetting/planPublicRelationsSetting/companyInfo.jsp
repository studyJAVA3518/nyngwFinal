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
		document.comInfo.action= "enovironmentSetting/planPublicRelationsSetting/companyInfo";
		document.comInfo.method = "post";
		document.comInfo.submit();
	} 
	 
</script>

<h2>회사 정보 설정</h2>
<h4>회사 로고 등록 및 변경</h4>
<table class="table">
	<form class="form-inline" method="post" id="form">
		<tr>
			<td colspan="2">
				<label for="basic">기본 이미지 사용</label>
				<input type="radio" name="logo" value="basic" id="basic"/> 
				<label for="custom">업로드 로고(CI) 사용</label>
				<input type="radio" name="logo" value="custom" id="basic"/> 
				<span class="logoDesc">※ 업로드 로고(CI)를 사용할 경우 업로드할 로고화면의 배경부분을 투명하게 처리해야 합니다.</span>
			<td>
		</tr>
		<tr>
			<th>로고(CI) 변경</th>
			<td><input type="file" value="첨부하기" id="logoFile"/>
				<span class="logoDesc">(최적 사이즈 220*80)</span>
			</td>
		</tr>
		<tr>
			<th>미리보기</th>
			<td>들어갈것인가???...생각해보자</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="로고 수정" class="btn btn-default"/>
			</td>
		</tr>
	</form>
</table>
	
<h4>회사 정보 등록</h4>
<div id="callBackDiv">
<table class="table">
	<form class="form-inline" id="form" method="post" name="comInfo">
		<tr>
			<th>회사 이름</th>
			<td>
				<div class="form-group">
					<input type="text" id="company_name" name="company_name" class="form-control" placeholder="정보 없음" value="${companyInfo.company_name}"/>
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
					<input type="text" style="width:500px;" id="zipNo" name="zipNo" readonly placeholder="정보 없음" value="${companyInfo.company_zip}"/> <input type="button" onClick="goPopup();" value="주소검색" class="btn btn-default"/>
					기본 주소 <input type="text" style="width:500px;" id="roadAddrPart1" name="roadAddrPart1" readonly placeholder="정보 없음"  value="${companyInfo.company_addr1}"/>
					상세 주소 <input type="text" style="width:500px;" id="addrDetail" name="addrDetail" placeholder="정보 없음"  value="${companyInfo.company_addr2}"/>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="회사 정보 수정" class="btn btn-default"/>
			</td>
		</tr>
	</form>
</table>
</div>
