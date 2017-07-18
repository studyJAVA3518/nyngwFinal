<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 환경설정관리 -> 기획홍보부 설정 -> 근무일 및 출결정보 등록시간 설정
에 대한 화면 -->

<script>
	function workingDaySetting_go(){
		
		$(function(){
		
			var file = $("#excelFile").val();
		    
			if (file == "" || file == null) {
		        alert("파일을 선택해주세요.");
		        return false;
		    } else if (!checkFileType(file)) {
		        alert("엑셀 파일만 업로드 가능합니다.");
		        return false;
		    }
			
		    if (confirm("업로드 하시겠습니까?")) {
		    	document.workingDayForm.method="post";
				document.workingDayForm.action="<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/excelUpload";
				document.workingDayForm.submit();
		    }
		})
	}

	function checkFileType(filePath) {
	    var fileFormat = filePath.split(".");
	    if (fileFormat.indexOf("xlsx") > -1||fileFormat.indexOf("xls") > -1) {
	        return true;
	    } else {
	        return false;
	    }
	
	}

	function workingTimeSetting_go(){
    	document.workingTimeForm.method="post";
		document.workingTimeForm.action="<%=request.getContextPath() %>/enovironmentSetting/planPublicRelationsSetting/updateWorkingTime";
		document.workingTimeForm.submit();
		
	}
</script>

<h2>근무일 및 출결정보 등록시간 설정</h2>
<table class="table">
	<tr>
		<th>No</th>
		<th>시간 종류</th>
		<th>요일</th>
		<th colspan="2">시간 설정
			<p>시:0에서 23까지 입력 / 분:00~60까지 입력</p>
		</th>
	</tr>
	<form class="form-inline" name="workingTimeForm">
		<c:forEach items="${wtList}" var="working" varStatus="status">
		
		<tr>
			<th>${status.count}</th>
			<td>
				<c:choose>
					<c:when test="${empty working.wt_name}">
						${working.wt_redday} 
					</c:when>
					<c:otherwise>
						${working.wt_name} 시간
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:choose>
					<c:when test="${empty working.wt_day}">
						${working.wt_redday} 
					</c:when>
					<c:otherwise>
						${working.wt_day}
					</c:otherwise>
				</c:choose>
				 
			</td>
			<td>
				<div class="form-group">
					<input type="hidden" name="wt_number" id="wt_number" value="${working.wt_number}"/>
					<input type="text" name="wt_attend_time_hour" id="wt_attend_time_hour" class="form-control inlineText" value="${working.wt_start_time.substring(0,2)}"/> 시 
					<input type="text" name="wt_attend_time_minute" id="wt_attend_time_minute" class="form-control inlineText" value="${working.wt_start_time.substring(3,5)}"/> 분 ~  
					<input type="text" name="wt_end_time_hour" id="wt_end_time_hour" class="form-control inlineText" value="${working.wt_end_time.substring(0,2)}"/> 시 
					<input type="text" name="wt_end_time_minute" id="wt_end_time_minute" class="form-control inlineText" value="${working.wt_end_time.substring(3,5)}"/> 분
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</td>
			<td><input type="button" class="btn btn-default" onclick="workingTimeSetting_go();" value="수정"/></td>
		</tr>
		</c:forEach>
	</form>
</table>

<h2>출결정보 수동 등록</h2>
	<form name="workingDayForm" id="workingDayForm" class="form-inline" enctype="multipart/form-data">
		<table class="table">
			<tr>
				<td colspan="2">
				<!-- 출결정보는 기본적으로 매일 오전 12시에 자동 업데이트가 됩니다.<br/> -->
					<span class="areaBlock textLeft">수동으로 출결정보를 업데이트 하려면 출결정보 엑셀파일을 선택하여 추가하세요.</span>
					<span class="areaBlock textLeft">출결정보 양식을 다운로드받아 해당 양식에 맞게 입력해야 정보가 입력됩니다.</span>
					<span class"areaBlock textRight"><a href="<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/excelDownload" class="btn btn-default">출결정보 엑셀파일 다운로드</a></span>
				</td>
			</tr>
			<tr>
				<th>등록할 엑셀 파일</th>
				<td><input type="file" name="excelFile" id="excelFile"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" id="addExcelImportBtn" class="btn btn-default" onclick="workingDaySetting_go();" value="수동 등록"></td>
			</tr>
		
		
		</table>
	</form>
