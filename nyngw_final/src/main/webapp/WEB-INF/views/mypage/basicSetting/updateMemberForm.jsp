<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.userdetails.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta charset="UTF-8">
<script>
function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("<%=request.getContextPath()%>/enovironmentSetting/jusoPopupForm","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 필요없지만..
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadAddrPart1, addrDetail, zipNo){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	document.getElementById("mem_addr1").value = roadAddrPart1;
	document.getElementById("mem_addr2").value = addrDetail;
	document.getElementById("mem_zip").value = zipNo;
}

	function chk(){
		var pwd = ${MemberVoDetail.mem_pwd};
		if(pwd==document.getElementsByName("mem_pwd")[0].value){
			alert("수정에 성공하였습니다.");
		}else{
			alert("기존 비밀번호가 일치하지 않습니다.");
		}
	} 

$(function() {
	$("#imgPre").css('visibility','hidden');
    $("#faceFile").on('change', function(){
    	$("#imgPre").css('visibility','visible');
    	readURL(this);
    });
});

function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
            $('#imgPre').attr('src', e.target.result);
        }
      reader.readAsDataURL(input.files[0]);
    }
}

$(function() {
	$("#imgPre1").css('visibility','hidden');
    $("#signFile").on('change', function(){
    	$("#imgPre1").css('visibility','visible');
    	readURL2(this);
    });
});

function readURL2(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
            $('#imgPre1').attr('src', e.target.result);
        }
      reader.readAsDataURL(input.files[0]);
    }
}
</script>
<script type="text/javascript">
   function file_change(file){
   var str=file.lastIndexOf("\\")+1;   //파일 마지막 "\" 루트의 길이 이후부터 글자를 잘라 파일명만 가져온다.
   file = file.substring(str, file.length);
   document.getElementsByName('mem_img')[0].value=file;
}
</script>

<h2>개인정보 수정 페이지</h2>
<p class="docTitleDescription">
	개인정보를 수정할 수 있습니다.
</p>

<form action="/mypage/basicSetting/updateMember" enctype="multipart/form-data" method="POST">
	<table class="table table-bordered">
		<tr>
			<th>사진</th>
			<td><img id="imgPre" src="#" />
				<input type="file" id="faceFile" name="mem_imgup" onchange="javascript:file_change(this.value);"/>
			</td>
		</tr>
		<tr>
			<th>사원이름</th>
			<td><input class="form-control inputTypeMemInsert" type="text" name="mem_name" value="${MemberVoDetail.mem_name}"/></td>
		</tr>
		<tr>
			<th>현재비밀번호</th>
			<td><input class="form-control inputTypeMemInsert" type="password" name="mem_pwd" value=""/><label>필수입력사항입니다.</label> </td>
		</tr>
		<tr>
			<th>새 비밀번호</th>
			<td><input class="form-control inputTypeMemInsert" type="password" name="mem_npwd" value=""/></td>
		</tr>
		<tr>
			<th>연락처</th>
			<td><input class="form-control inputTypeMemInsert" type="text" name="mem_tel" value="${MemberVoDetail.mem_tel}"/></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input class="form-control inputTypeMemInsert" type="email" name="mem_email" value="${MemberVoDetail.mem_email}"/></td>
		</tr>
		<tr>
			<th>우편 번호</th>
			<td><input class="form-control inputTypeMemInsert" type="text" name="mem_zip" id="mem_zip" value="${MemberVoDetail.mem_zip}"/></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" class="form-control inputTypeMemInsert" name="mem_addr1" id="mem_addr1" value="${MemberVoDetail.mem_addr1}"/>
			<input type="button" class="btn btn-default" value="주소찾기" onclick="goPopup()"></td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td><input type="text" class="form-control inputTypeMemInsert" name="mem_addr2" id="mem_addr2" value="${MemberVoDetail.mem_addr2}"/></td>
		</tr>
	</table>
	<table class="table table-bordered">
		<tr>
			<th>서명 이미지</th>
			<td><img id="imgPre1" src="#" />
				<input type="file" id="signFile" name="mem_signup"/>
			</td>
		</tr>
	</table>

	<div class="insertJoinBtnWrap textCenter" >
		<input onclick="chk();" type="submit" class="btn btn-default" value="수정" />
	</div>
</form>
