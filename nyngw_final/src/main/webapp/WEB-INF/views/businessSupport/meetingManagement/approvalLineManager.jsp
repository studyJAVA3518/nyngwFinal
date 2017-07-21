<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>
<style type="text/css">
a:link {
	text-decoration: none;
	color: black
}

a:visited {
	text-decoration: none;
	color: black
}

a:hover {
	text-decoration: underline;
	color: black
}

a:active {
	text-decoration: underline;
	color: black
}

.readonly {
	background-color: #B9B9B9;
}

table {
	width: 150px;
	border: 1px solid #86B7FF;
}

th {
	background-color: #D2E0FF;
	font-size: 10pt;
	font-family: 굴림;
	height: 25px;
	border: 1px solid #0099CC;
}

td {
	font-size: 10pt;
	font-family: 굴림;
	height: 25px;
	border: 1px none #0099CC;
	background-color: #DDE7FF;
}
</style>

<script>
	var goThereCall=null;
	var goThereCall2=null;
	
	$(function(){
		//부서 선택시 회원 띄워주기//
		function goThereAjax(str){
			$.ajax({
				url: 'findMemberByDepartment',
				data: 'dept_number='+str,
				type: 'get',
				success : function(result){
					var code="";
					code+="";
				},
				success:function(result){
					var code='<tr><th><input type="checkbox" onclick="$.allCheck();" id="allCheck"></th><th>부서</th><th>직급(직책)</th><th>사원명</th></tr>';
					var codeInput='';
					$.each(result, function(i, value){
// 						if (i==0){
// 							code="";
// 						}
						code+='<tr><td><input type="checkbox" id="'+value.mem_number+'" value="'+value.mem_name+'"></td>';
						code+='<td>'+value.mem_dept_name+'</td>';
						code+='<td>'+value.mem_position_number+'</td>';
						code+='<td>'+value.mem_name+'</td></tr>';
						codeInput = value.mem_dept_number;
					});
					$("#memberTable").html(code);
					$("#searchInput").attr("value",codeInput);
				},
				dataType : 'json'
			});
		};
		goThereCall = goThereAjax;
		
		//회원 검색하기//
		function goThereAjax2(){
			$.ajax({
				url: '/electronicApproval/draft/searchMember',
				data: $('form#search').serialize(),
				type: 'get',
				success:function(result){
					var code='<tr><th><input type="checkbox" onclick="$.allCheck();" id="allCheck"></th><th>부서</th><th>직급(직책)</th><th>사원명</th></tr>';
					var codeInput='';
					$.each(result, function(i, value){
						code+='<tr><td><input type="checkbox" id="'+value.mem_number+'" value="'+value.mem_name+'"></td>';
						code+='<td>'+value.mem_dept_name+'</td>';
						code+='<td>'+value.mem_position_number+'</td>';
						code+='<td>'+value.mem_name+'</td></tr>';
						codeInput = value.mem_dept_number;
					});
					$("#memberTable").html(code);
					$("#searchInput").attr("value",codeInput);
				},
				dataType : 'json'
			});
		}	
		goThereCall2 = goThereAjax2;
		
		//모두 체크하기//
		$.allCheck = function(){
			//만약 전체 선택 체크박스가 체크된상태일경우 
			if ($("#allCheck").prop("checked")) {
			   //해당화면에 전체 checkbox들을 체크해준다 
			   $("input[type=checkbox]").prop("checked", true);
			   // 전체선택 체크박스가 해제된 경우
			} else {
			   //해당화면에 모든 checkbox들의 체크를해제시킨다. 
			   $("input[type=checkbox]").prop("checked", false);
			}
		} 
		
		//라인으로 이동시키기//
		/////////////////////////////////////////////////////////
		$("#addToApproval").click(function(){	
			$('input[type=checkbox]:checked').each(function(){
				if($(this).attr('id')!='allCheck'){
					if($("#approval").find("#"+$(this).attr('id')+"").val()===undefined){
						$('#approval').append('<option id="'+ $(this).attr('id')+'" value="'+$(this).val()+'">'+$(this).val()+'</option>');
					}else{
						alert("이미 추가되었습니다.");
					}
				}
				$(this).prop("checked",false);
			});
		});
		$("#addToAgreement").click(function(){	
			$('input[type=checkbox]:checked').each(function(){
				if($(this).attr('id')!='allCheck'){
					if($("#agreement").find("#"+$(this).attr('id')+"").val()===undefined){
						$('#agreement').append('<option id="'+ $(this).attr('id')+'" value="'+$(this).val()+'">'+$(this).val()+'</option>');
					}else{
						alert("이미 추가되었습니다.");
					}
				}
				$(this).prop("checked",false);
			});
		});
		$("#addToImplement").click(function(){	
			$('input[type=checkbox]:checked').each(function(){
				if($(this).attr('id')!='allCheck'){
					if($("#implement").find("#"+$(this).attr('id')+"").val()===undefined){
						$('#implement').append('<option id="'+ $(this).attr('id')+'" value="'+$(this).val()+'">'+$(this).val()+'</option>');
					}else{
						alert("이미 추가되었습니다.");
					}
				}
				$(this).prop("checked",false);
			});
		});
		$("#addToReference").click(function(){	
			$('input[type=checkbox]:checked').each(function(){
				if($(this).attr('id')!='allCheck'){
					if($("#reference").find("#"+$(this).attr('id')+"").val()===undefined){
						$('#reference').append('<option id="'+ $(this).attr('id')+'" value="'+$(this).val()+'">'+$(this).val()+'</option>');
					}else{
						alert("이미 추가되었습니다.");
					}
				}
				$(this).prop("checked",false);
			});
		});
		
		
		
		/////////////////////////////////////////////////////////
		
		//결재라인 등록하기//
		$("#submitLine").click(function(){
	         $('#approval option').each(function(i){
	        	 alert($('#approvalMember'+(i+1)).html($(this).val()+'<input type="hidden" name="mt_members'+(i+1)+'" value="'+$(this).attr('id')+'">'));
	            $('#approvalMember'+(i+1)).html($(this).val()+'<input type="hidden" name="mt_members'+(i+1)+'" value="'+$(this).attr('id')+'">');
	         });
	         $('#linePopup').dialog('close');
		})
	});
	
	
	var PrivSeq = 0;
	var fldblue = '000000';
	var fldred = 'cf102f';

	function togglesub(sg) {
		var oDiv = document.all["PG" + sg];
		var oImg = document.all["IMG" + sg];

		if (oDiv != null) {
			if (oDiv.style.display == "none") {
				oDiv.style.display = ""
				oImg.src = "/resources/images/tree/minus.gif";
			} else {
				oDiv.style.display = "none"
				oImg.src = "/resources/images/tree/plus.gif";
			}
		}
	}

	function expandsub(sg) {
		var oDiv = document.all["PG" + sg];
		var oImg = document.all["IMG" + sg];
		if (oDiv != null) {
			if (oDiv.style.display == "none") {
				oDiv.style.display = ""
				oImg.src = "/resources/images/tree/plus.gif";
			}
		}
		changecolor(sg);
	}

	function changecolor(sg) {
		document.all["A" + sg].style.color = fldred; // selected color
		if (PrivSeq != 0 && PrivSeq != sg)
			document.all["A" + PrivSeq].style.color = fldblue; //unselected color
		PrivSeq = sg;
	}

	function goThere(str) {
		if (str != '') {
			goThereCall(str);
		}
	}

	function goThere2() {
		goThereCall2();
	}
	
</script>

<!-- 다이얼로그 전체 -->
<div id="treeDialog" style="width:900px;height:600px;">
	기안하기>결재라인설정
	
	<!-- 트리 디브 전체 -->
	<div id="treeWrap" class="row">
		
		<!-- 1. 트리 사이드 -->
		<div id="treeSide" class="col-md-3" style="overflow:auto;">
			<form method='post' name='codeform'>
				<input name='code' type='hidden' />
				<div id="tree"></div>
			</form>
		</div>
		
		<!-- 2. 트리 메인 -->
		<div id="treeMain" class="col-md-9">
			<!-- 2.1 트리 메인 탑 -->
			<div id="treeMainTop" class="row" style="height:200px;overflow:auto;border:1px solid black;">
				<form id="search">
					사원명 <input name="searchText"> <a href="javascript:goThere2()"><button type="button">검색</button></a>
					<input type="hidden" id="searchInput" name="dept_number" value="${dept_number }">
				</form>
				<table class="table" id="memberTable">
					<tr>
						<th><input type="checkbox" id="allCheck"></th>
						<th>부서</th>
						<th>직급(직책)</th>
						<th>사원명</th>
					</tr>
				</table>
			</div>
			
			<!-- 2.2 트리 메인 미들 -->
			<div id="treeMainMiddle">
				<button type="button" id="addToApproval">선택 인원 회의 참여</button>
			</div>	
			
			<!-- 2.3 트리 메인 바텀 -->
			<div id="treeMainBottom" class="row" style="border:1px solid black;">
				<div id="treeMainBottomTop" class="row">
					<div id="treeMainBottomA" class="col-md-6" style="height:300px;">
						<label>회의 참여자</label>
						<div>
							<select id="approval" name="approval" size="5" style="width:300px;height:150px;">
							</select>
						</div>
					</div>
					
				</div>
				<button type="button" id="submitLine">등록</button>
				<button type="button">취소</button>
			</div>
		</div>
	</div>
</div>
