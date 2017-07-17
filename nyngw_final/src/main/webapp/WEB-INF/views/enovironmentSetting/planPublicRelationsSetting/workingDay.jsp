<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

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
		        var options = {
		            success : function(data) {
		                alert("모든 데이터가 업로드 되었습니다.");
		            },
		            type : "POST"
		        };
		        $("#excelUploadForm").ajaxSubmit(options);
		
		    }
			
		})

	}
	$(function(){
		
		
	})

	function checkFileType(filePath) {
	    var fileFormat = filePath.split(".");
	    if (fileFormat.indexOf("xlsx") > -1||fileFormat.indexOf("xls") > -1) {
	        return true;
	    } else {
	        return false;
	    }
	
	}


	
	
	function workingTimeSetting_go(){
		
	}
</script>

<h2>근무일 및 출결정보 등록시간 설정</h2>
<table class="table">
	<form class="form-inline" name="workingDaySettingForm">
		<tr>
			<th>업무 시간</th>
			<td>
				<div class="form-group">
					매일 
					<input type="text" name="dal_attend_time" id="dal_attend_time"/> 시 
					<input type="text" name="dal_off_time" id="dal_off_time"/> 분
				</div>
			</td>
		</tr>
		<tr>
			<th>특근 시간</th>
			<td>
				<div class="form-group">
					주말 
					<input type="text" name="dal_attend_time" id="dal_attend_time"/> 시 
					<input type="text" name="dal_off_time" id="dal_off_time"/> 분
				</div>
			</td>
		</tr>
		<tr>
			<th>야근 시간</th>
			<td>
				<div class="form-group">
					<input type="text" name="dal_attend_time" id="dal_attend_time"/> 시 
					<input type="text" name="dal_off_time" id="dal_off_time"/> 분
				</div>
			</td>
		</tr>
	</form>
</table>

<button action="workingTimeSetting_go();" class="btn btn-default">시간 설정</button>

<h2>출결정보 수동 등록</h2>
<table class="table">
	<form name="workingDayForm" class="form-inline" enctype="multipart/form-data" 
		action= "<%=request.getContextPath()%>/enovironmentSetting/planPublicRelationsSetting/updateExcelFile" 
		method="post">
		<tr>
			<td colspan="2">
				출결정보는 기본적으로 매일 오전 12시에 자동 업데이트가 됩니다.<br/>
				만약에 수동으로 출결정보를 업데이트 하려면 출결정보 엑셀파일을 선택하여 추가하세요. 
			</td>
		</tr>
		<tr>
			<th>등록할 엑셀 파일</th>
			<td><input type="file" name="excelFile" id="excelFile"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" id="addExcelImportBtn" class="btn btn-default" onclick="workingDaySetting_go();" value="수동 등록"></td>
		</tr>

	</form>

</table>
