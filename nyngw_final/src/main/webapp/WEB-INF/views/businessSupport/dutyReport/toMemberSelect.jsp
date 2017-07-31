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
	width: 164px;
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
				url: '/electronicApproval/draft/findMemberByDepartment',
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
		//결재자에 등록
		$("#addToApproval").click(function(){
			var index = 1;
				$('input[type=checkbox]:checked').each(function(){
					if(!($("option:last","#approval").attr("name")===undefined)){
						index = parseInt($("option:last","#approval").attr("name").substring(14))+1;
					}else{
						if($(this).attr('id')!='allCheck'){
							if($("#approval").find("#"+$(this).attr('id')).val()===undefined){
								$('#approval').append('<option name="approvalMember'+index+'" id="'+ $(this).attr('id')+'" value="'+$(this).val()+'">'+$(this).val()+'</option>');
							}else{
								alert($(this).val()+"님은 이미 추가되었습니다.");
							}
						}	
						$(this).prop("checked",false);
					}
				});
		});
		});
		/////////////////////////////////////////////////////////
		
		
		//삭제하기//
		$('#deleteA').click(function(){
			deleteItemA();
		});
		function deleteItemA(){
			$('#approval option:selected').each(function(){
				var last = parseInt($("option:last","#approval").attr("name").substring(14));
				var current = parseInt($(this).attr("name").substring(14)); 
				var times = last-current;
				for(var i = 0 ; i<times ; i++){
					$("option:nth-child("+last+")","#approval").attr("name","approvalMember"+(last-1));
					last -= 1;
				}
				$(this).remove();
			});
		}
		/////////////////////////////////////////////////////////
	
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
<h2>보고 대상 설정</h2>
	
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
			<div id="treeMainTop" class="row" style="height:200px;overflow:auto;">
				<div class="divALM">
					<form id="search" onsubmit="javascript:goThere2(); return false;">
						<a style="font-weight: bold;">&nbsp;&nbsp;&nbsp;사원명&nbsp;&nbsp;</a> 
						<input name="searchText" class="form-control eaInputSearch"> &nbsp;<button type="button" onclick="javascript:goThere2()"  class="btn btn-default">검색</button>
						<input type="hidden" id="searchInput" name="dept_number" value="${dept_number }">
					</form>
				</div>
				<table class="table textCenter" id="memberTable">
					<tr>
						<th><input type="checkbox" id="allCheck"></th>
						<th>부서</th>
						<th>직급(직책)</th>
						<th>사원명</th>
					</tr>
				</table>
			</div>
			
			<!-- 2.2 트리 메인 미들 -->
			<div id="treeMainMiddle" style="margin: 10px auto;">
				<button type="button" id="addToApproval" class="btn btn-default">보고대상</button>
			</div>	
			
			<!-- 2.3 트리 메인 바텀 -->
			<div id="treeMainBottom" class="row">
				<div id="treeMainBottomTop" class="row">
					<div id="treeMainBottomA" class="col-md-6" style="height:150px;">
						<p class="docTitleDescription" style="font-weight: bold;">보고대상</p>
						<div style="float:left;width:280px;">
							<select id="approval" name="approval" size="5" style="width:280px;height:90px;">
							</select>
						</div>
						<div id ="but" style="float:left;width:40px">
							<p><input type = "button" value="ⓧ" id="deleteA" class="btn btn-default eabtn"></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
