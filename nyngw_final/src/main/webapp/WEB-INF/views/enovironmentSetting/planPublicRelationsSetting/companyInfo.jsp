<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- 환경설정관리 -> 기획홍보부 설정 -> 회사정보 설정에 대한 화면 -->

<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
// 	document.domain = "enovironmentSetting/planPublicRelationsSetting/companyInfo";

function zipNum_go(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	alert("!!!!!");
	var pop = window.open("enovironmentSetting/planPublicRelationsSetting/jusoPopupForm","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
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

</script>

<h2>회사 정보 설정</h2>
<h4>회사 로고 등록 및 변경</h4>
<table class="table">
	<form class="form-inline" action="" method="post">
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
<table class="table">
	<form class="form-inline" action="" method="post">
		<tr>
			<th>회사 이름</th>
			<td>
				<div class="form-group">
					<input type="text" id="company_name" name="company_name" class="form-control"/>
				</div>
			</td>
		</tr>
		<tr>
			<th>회사 전화번호</th>
			<td>
				<div class="form-group">
					<input type="text" id="company_tel" name="company_tel" class="form-control"/>
				</div>
			</td>
		</tr>
		<tr>
			<th>회사 주소</th>
			<td>
				<div class="form-group">
					<input type="text" id="company_zip" name="company_zip"/> <a class="btn btn-default" onclick="zipNum_go">우편번호 검색</a><br/>
					기본 주소 <input type="text"  style="width:500px;" id="roadAddrPart1"  name="roadAddrPart1" /> 
					<input type="text" id="company_addr1" name="company_addr1" class="form-control"/>
					상세 주소 <input type="text"  style="width:500px;" id="addrDetail"  name="addrDetail" />
					<input type="text" id="company_addr2" name="company_addr2" class="form-control"/>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="회사 정보 수정" class="btn btn-default"/>
			</td>
		</tr>
	</form>
</table>
