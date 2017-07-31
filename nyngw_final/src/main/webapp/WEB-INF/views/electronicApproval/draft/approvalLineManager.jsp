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
}

th {
	background-color: #D2E0FF;
	font-size: 10pt;
	font-family: 굴림;
	height: 25px;
}

td {
	font-size: 10pt;
	font-family: 굴림;
	height: 30px;
	vertical-align:middle;
	border: 1px none #0099CC;
}

.td1{
	width:95px;
	height:38px;
}
.td2{
	width:145px;
	height:38px;
}
.td3{
	width:268px;
	height:38px;
}
.td4{
	width:189px;
	height:38px;
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
					var code='<tr><th class="td1" style="vertical-align:middle;"><input type="checkbox" onclick="$.allCheck();" id="allCheck"></th><th class="td2" style="vertical-align:middle;">부서</th><th class="td3" style="vertical-align:middle;">직급(직책)</th><th class="td4" style="vertical-align:middle;">사원명</th></tr>';
					var codeInput='';
					$.each(result, function(i, value){
// 						if (i==0){
// 							code="";
// 						}
						code+='<tr><td class="td1"><input type="checkbox" id="'+value.mem_number+'" value="'+value.mem_name+'"></td>';
						code+='<td class="td2">'+value.mem_dept_name+'</td>';
						code+='<td class="td3">'+value.mem_position_number+'</td>';
						code+='<td class="td4">'+value.mem_name+'</td></tr>';
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
						code+='<tr><td class="td1"><input type="checkbox" id="'+value.mem_number+'" value="'+value.mem_name+'"></td>';
						code+='<td class="td2">'+value.mem_dept_name+'</td>';
						code+='<td class="td3">'+value.mem_position_number+'</td>';
						code+='<td class="td4">'+value.mem_name+'</td></tr>';
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
				}
				if(index>5){
					alert("결재자는 5명까지만 등록할 수 있습니다. 상위 5명만 등록 됩니다.");
				}else{
					if($(this).attr('id')!='allCheck'){
						if(!($("#agreement").find("#"+$(this).attr('id')).val()===undefined)){
							alert($(this).val()+"님은 이미 합의자 리스트에 추가되었습니다.");
						}else if($("#approval").find("#"+$(this).attr('id')).val()===undefined){
							$('#approval').append('<option name="approvalMember'+index+'" id="'+ $(this).attr('id')+'" value="'+$(this).val()+'">'+$(this).val()+'</option>');
						}else{
// 							alert($(this).val()+"님은 이미 추가되었습니다.");
							swal({
								text: $(this).val()+' 님은 이미 추가되었습니다.',
								confirmButtonText: '확인'
							});
						}
						
					}	
				}
				$(this).prop("checked",false);
			});
		});
		//합의자 등록
		$("#addToAgreement").click(function(){
			var index = 1;
			$('input[type=checkbox]:checked').each(function(){
				if(!($("option:last","#agreement").attr("name")===undefined)){
					index = parseInt($("option:last","#agreement").attr("name").substring(15))+1;
				}
				if(index>5){
					alert("결재자는 5명까지만 등록할 수 있습니다. 상위 5명만 등록 됩니다.");
				}else{
					if($(this).attr('id')!='allCheck'){
						if(!($("#approval").find("#"+$(this).attr('id')).val()===undefined)){
							alert($(this).val()+"님은 이미 결재자 리스트에 추가되었습니다.");
						}else if($("#agreement").find("#"+$(this).attr('id')).val()===undefined){
							$('#agreement').append('<option name="agreementMember'+index+'" id="'+ $(this).attr('id')+'" value="'+$(this).val()+'">'+$(this).val()+'</option>');
						}else{
							alert($(this).val()+"님은 이미 추가되었습니다.");
						}
					}	
				}	
				$(this).prop("checked",false);
			});
		});
		//시행자 등록
		$("#addToImplement").click(function(){
			var index = 1;
			$('input[type=checkbox]:checked').each(function(){
				if(!($("option:last","#implement").attr("name")===undefined)){
					index = parseInt($("option:last","#implement").attr("name").substring(15))+1;
				}
				if($(this).attr('id')!='allCheck'){
					if($("#implement").find("#"+$(this).attr('id')).val()===undefined){
						$('#implement').append('<option name="implementMember'+index+'" id="'+ $(this).attr('id')+'" value="'+$(this).val()+'">'+$(this).val()+'</option>');
					}else{
						alert($(this).val()+"님은 이미 추가되었습니다.");
					}
				}	
				$(this).prop("checked",false);
			});
		});
		//참조자 등록
		$("#addToReference").click(function(){
			var index = 1;
			$('input[type=checkbox]:checked').each(function(){
				if(!($("option:last","#reference").attr("name")===undefined)){
					index = parseInt($("option:last","#reference").attr("name").substring(15))+1;
				}
				if($(this).attr('id')!='allCheck'){
					if($("#reference").find("#"+$(this).attr('id')).val()===undefined){
						$('#reference').append('<option name="referenceMember'+index+'" id="'+ $(this).attr('id')+'" value="'+$(this).val()+'">'+$(this).val()+'</option>');
					}else{
						alert($(this).val()+"님은 이미 추가되었습니다.");
					}
				}	
				$(this).prop("checked",false);
			});
		});
		/////////////////////////////////////////////////////////
		
		//위로 올리기//
		/////////////////////////////////////////////////////////
		$('#moveUpA').click(function(){
			moveUpItemA();
		});
		function moveUpItemA(){
			$('#approval option:selected').each(function(){
				var prevName = $(this).prev().attr('name');
				var thisName = $(this).attr('name');
				$(this).prev().attr('name', thisName);
				$(this).attr('name', prevName);
				$(this).insertBefore($(this).prev());
			});
		}
		$('#moveUpB').click(function(){
			moveUpItemB();
		});
		function moveUpItemB(){
			$('#agreement option:selected').each(function(){
				var prevName = $(this).prev().attr('name');
				var thisName = $(this).attr('name');
				$(this).prev().attr('name', thisName);
				$(this).attr('name', prevName);
				$(this).insertBefore($(this).prev());
			});
		}
		$('#moveUpC').click(function(){
			moveUpItemC();
		});
		function moveUpItemC(){
			$('#implement option:selected').each(function(){
				var prevName = $(this).prev().attr('name');
				var thisName = $(this).attr('name');
				$(this).prev().attr('name', thisName);
				$(this).attr('name', prevName);
				$(this).insertBefore($(this).prev());
			});
		}
		$('#moveUpD').click(function(){
			moveUpItemD();
		});
		function moveUpItemD(){
			$('#reference option:selected').each(function(){
				var prevName = $(this).prev().attr('name');
				var thisName = $(this).attr('name');
				$(this).prev().attr('name', thisName);
				$(this).attr('name', prevName);
				$(this).insertBefore($(this).prev());
			});
		}
		/////////////////////////////////////////////////////////
		
		//아래로 내리기//
		/////////////////////////////////////////////////////////
		$('#moveDownA').click(function(){
			moveDownItemA();
		});
		function moveDownItemA(){
			$('#approval option:selected').each(function(){
				var nextName = $(this).next().attr('name');
				var thisName = $(this).attr('name');
				$(this).next().attr('name', thisName);
				$(this).attr('name', nextName);
				$(this).insertAfter($(this).next());
			});
		}
		$('#moveDownB').click(function(){
			moveDownItemB();
		});
		function moveDownItemB(){
			$('#agreement option:selected').each(function(){
				var nextName = $(this).next().attr('name');
				var thisName = $(this).attr('name');
				$(this).next().attr('name', thisName);
				$(this).attr('name', nextName);
				$(this).insertAfter($(this).next());
			});
		}
		$('#moveDownC').click(function(){
			moveDownItemC();
		});
		function moveDownItemC(){
			$('#implement option:selected').each(function(){
				var nextName = $(this).next().attr('name');
				var thisName = $(this).attr('name');
				$(this).next().attr('name', thisName);
				$(this).attr('name', nextName);
				$(this).insertAfter($(this).next());
			});
		}
		$('#moveDownD').click(function(){
			moveDownItemD();
		});
		function moveDownItemD(){
			$('#reference option:selected').each(function(){
				var nextName = $(this).next().attr('name');
				var thisName = $(this).attr('name');
				$(this).next().attr('name', thisName);
				$(this).attr('name', nextName);
				$(this).insertAfter($(this).next());
			});
		}
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
		$('#deleteB').click(function(){
			deleteItemB();
		});
		function deleteItemB(){
			$('#agreement option:selected').each(function(){
				var last = parseInt($("option:last","#agreement").attr("name").substring(15));
				var current = parseInt($(this).attr("name").substring(15)); 
				var times = last-current;
				for(var i = 0 ; i<times ; i++){
					$("option:nth-child("+last+")","#agreement").attr("name","agreementMember"+(last-1));
					last -= 1;
				}
				$(this).remove();
			});
		}
		$('#deleteC').click(function(){
			deleteItemC();
		});
		function deleteItemC(){
			$('#implement option:selected').each(function(){
				var last = parseInt($("option:last","#implement").attr("name").substring(15));
				var current = parseInt($(this).attr("name").substring(15)); 
				var times = last-current;
				for(var i = 0 ; i<times ; i++){
					$("option:nth-child("+last+")","#implement").attr("name","implementMember"+(last-1));
					last -= 1;
				}
				$(this).remove();
			});
		}
		$('#deleteD').click(function(){
			deleteItemD();
		});
		function deleteItemD(){
			$('#reference option:selected').each(function(){
				var last = parseInt($("option:last","#reference").attr("name").substring(15));
				var current = parseInt($(this).attr("name").substring(15)); 
				var times = last-current;
				for(var i = 0 ; i<times ; i++){
					$("option:nth-child("+last+")","#reference").attr("name","referenceMember"+(last-1));
					last -= 1;
				}
				$(this).remove();
			});
		}
		/////////////////////////////////////////////////////////
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
	<h5>결재라인설정</h5>
	<br>
	
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
				<div class="divALM">
					<form id="search" onsubmit="javascript:goThere2(); return false;">
						<a style="margin: 0px 10px; font-weight: bold;">사원명</a> <input name="searchText" class="form-control eaInputSearch"><button type="button" onclick="javascript:goThere2()" class="btn btn-default" style="margin-left: 10px;">검색</button>
						<input type="hidden" id="searchInput" name="dept_number" value="${dept_number }">
					</form>
				</div>
			<div id="treeMainTop" class="row" style="height:200px;overflow:auto; margin-bottom:10px; border-bottom: 1px solid gray;">
				<table class="table tableGray textCenter" id="memberTable">
					<tr>
						<th><input type="checkbox" id="allCheck"></th>
						<th>부서</th>
						<th>직급(직책)</th>
						<th>사원명</th>
					</tr>
				</table>
			</div>
			
			<!-- 2.2 트리 메인 미들 -->
			<div class="textCenter">
				<div id="treeMainMiddle">
					<button type="button" id="addToApproval" class="btn btn-default">결재</button>
					<button type="button" id="addToAgreement" class="btn btn-default">합의</button>
					<button type="button" id="addToImplement" class="btn btn-default">시행</button>
					<button type="button" id="addToReference" class="btn btn-default">참조</button>
				</div>	
			</div>
			<!-- 2.3 트리 메인 바텀 -->
			<div id="treeMainBottom" class="row" style="margin-top: 10px;">
				<div id="treeMainBottomTop" class="row">
					<div id="treeMainBottomA" class="col-md-6" style="height:150px;">
						<p class="docTitleDescription" style="font-weight: bold; margin-left: 120px;">결재자</p>
						<div style="float:left;width:280px;">
							<select id="approval" name="approval" size="5" style="width:280px;height:93px;">
							</select>
						</div>
						<div id ="but" style="float:left;width:40px">
							<p><input type = "button" value="▲" id="moveUpA" class="btn btn-default eabtn"></p>
							<p><input type = "button" value="▼" id="moveDownA" class="btn btn-default eabtn"></p>
							<p><input type = "button" value="ⓧ" id="deleteA" class="btn btn-default eabtn"></p>
						</div>
					</div>
					<div id="treeMainBottomB" class="col-md-6" style="height:150px;">
							<p class="docTitleDescription"style="font-weight: bold; margin-left: 120px;">합의자</p>
						<div style="float:left;width:280px;">
							<select id="agreement" size="5" style="width:280px;height:93px;">
							</select>
						</div>
						<div id ="but" style="float:left;width:40px">
							<p><input type = "button" value="▲" id="moveUpB" class="btn btn-default eabtn"></p>
							<p><input type = "button" value="▼" id="moveDownB" class="btn btn-default eabtn"></p>
							<p><input type = "button" value="ⓧ" id="deleteB" class="btn btn-default eabtn"></p>
						</div>	
					</div>
				</div>
				<div id="treeMainBottomBottom" class="row">
					<div id="treeMainBottomC" class="col-md-6" style="height:150px;">
							<p class="docTitleDescription"style="font-weight: bold; margin-left: 120px;">시행자</p>
						<div style="float:left;width:280px;">
							<select id="implement" size="5" style="width:280px;height:93px;">
							</select>
						</div>
						<div id ="but" style="float:left;width:40px">
							<p><input type = "button" value="▲" id="moveUpC" class="btn btn-default eabtn"></p>
							<p><input type = "button" value="▼" id="moveDownC" class="btn btn-default eabtn"></p>
							<p><input type = "button" value="ⓧ" id=deleteC class="btn btn-default eabtn"></p>
						</div>	
					</div>
					<div id="treeMainBottomD" class="col-md-6" style="height:150px; width : 350px;">
							<p class="docTitleDescription"style="font-weight: bold; margin-left: 120px;">수신참조자</p>
						<div style="float:left;width:280px;">
							<select id="reference" size="5" style="width:280px;height:93px;">
							</select>
						</div>
						<div id ="but" style="float:left;width:40px">
							<input type = "button" value="▲" id="moveUpD" class="btn btn-default eabtn">
							<input type = "button" value="▼" id="moveDownD" class="btn btn-default eabtn">
							<input type = "button" value="ⓧ" id="deleteD" class="btn btn-default eabtn">
						</div>	
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
