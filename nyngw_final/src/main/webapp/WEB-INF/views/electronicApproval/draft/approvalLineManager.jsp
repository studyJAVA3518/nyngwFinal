<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>
기안하기>결재라인설정
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

<script language="JavaScript">
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

			eval("document.codeform.action='org_code.jsp'");
			eval("document.codeform.code.value=str");
			eval("document.codeform.target='bottom'");
			eval("document.codeform.submit()");

			eval("document.codeform.action='org_base_inform.jsp'");
			eval("document.codeform.code.value=str");
			eval("document.codeform.target='top'");
			eval("document.codeform.submit()");
			alert(str);
		}
	}
</script>
<div id="treeWrap" class="row" style="height:100%;">

	<div id="treeSide" class="col-md-3" style="overflow:auto;">
		<form method='post' name='codeform'>
			<input name='code' type='hidden' />
		</form>
	
		${sb }
	
	</div>
	
	<div id="treeMain" class="col-md-9">
		<div id="treeMainTop" class="row" style="height:200px;overflow:auto;border:1px solid black;">
			<form>
				사원명 <input name="searchText"> <button type="button" onclick="searchMember_go(this.form)">검색</button>
			</form>
			<table class="table">
				<tr>
					<th><input type="checkbox" id="selectAll"></th>
					<th>회사</th>
					<th>부서</th>
					<th>직급(직책)</th>
					<th>사원명</th>
				</tr>
				<tr>
					<td><input type="checkbox" id="아이디" value="값"></td>
					<td>회사이름</td>
					<td>부서이름</td>
					<td>직책이름</td>
					<td>사원이름</td>
				</tr>
			</table>
		</div>
		<div id="treeMainBottom" class="row" style="border:1px solid black;">
			<div id="treeMainBottomTop" class="row">
				<div id="treeMainBottomA" class="col-md-6" style="height:150px;">
					결재자
					<select id="결재자" size="5" style="width:300px;height:90px;">
						<option>최시영</option>
						<option>송지효</option>
						<option>김병현</option>
					</select>
				</div>
				<div id="treeMainBottomB" class="col-md-6" style="height:150px;">
					합의자
					<select id="합의자" size="5" style="width:300px;height:90px;">
						<option>최시영</option>
						<option>송지효</option>
						<option>김병현</option>
					</select>
				</div>
			</div>
			<div id="treeMainBottomBottom" class="row">
				<div id="treeMainBottomC" class="col-md-6" style="height:150px;">
					시행자
					<select id="시행자" size="5" style="width:300px;height:90px;">
						<option>최시영</option>
						<option>송지효</option>
						<option>김병현</option>
					</select>
				</div>
				<div id="treeMainBottomD" class="col-md-6" style="height:150px;">
					수신참조자
					<select id="수신참조자" size="5" style="width:300px;height:90px;">
						<option>최시영</option>
						<option>송지효</option>
						<option>김병현</option>
					</select>
				</div>
			</div>
		</div>
	</div>

</div>

<script>
	function searchMember_go(form){
		form.method="get";
		form.action="/electronicApproval/draft/searchMember";
		form.submit();
	} 
</script>
