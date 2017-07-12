<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- 환경설정관리 -> 기획홍보부 설정 ->회사 부서 설정
에 대한 화면 -->

<h2>회사 부서 설정</h2>
<p>
	회사의 부서를 등록하는 메뉴입니다.
	부서를 만들때 자동으로 부서 메뉴도 생성됩니다.
</p>
<div class="row">
	<h4>부서 만들기</h4>
	<table class="table">
		<form action="#" method="post">
			<tr>
				<th>부서 이름</th>
				<td>
					<div class="form-group">
						<input type="text" id="dept_name" name="dept_name"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>부서장 사원번호</th>
				<td>
					<div class="form-group">
						<input type="text" id="dept_name" name="dept_name"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>부서 연락처</th>
				<td>
					<div class="form-group">
						<input type="text" id="dept_name" name="dept_name"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>부서 위치</th>
				<td>
					<div class="form-group">
						<input type="text" id="dept_name" name="dept_name"/>
					</div>
				</td>
			</tr>
			<tr>
				<th>상위 부서 선택</th>
				<td>
					<div class="form-group">
						<select>
							<option>사장</option>
							<option>부사장</option>
							<option>인사부</option>
							<option>기획홍보부</option>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<th>부서 등급</th>
				<td>
					<div class="form-group">
						<input type="text" id="dept_name" name="dept_name"/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="부서 등록"/>
				</td>
			</tr>
		</form>
	</table>
</div>

<div class="row">
	<h4>부서 리스트 (총 n 개의 부서가 등록되어 있습니다.)</h4>
	<div class="row">
		<div class="col-md-3">사장</div>
		<div class="col-md-9">
			<input type="button" onclick="updateDept_go();" value="수정" class="btn btn-default"/>
			<input type="button" onclick="deleteDept_go();" value="삭제" class="btn btn-default"/>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3">&nbsp;&nbsp;&nbsp;└ 부사장</div>
		<div class="col-md-9">
			<input type="button" onclick="updateDept_go();" value="수정" class="btn btn-default"/>
			<input type="button" onclick="deleteDept_go();" value="삭제" class="btn btn-default"/>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└ 인사부</div>
		<div class="col-md-9">
			<input type="button" onclick="updateDept_go();" value="수정" class="btn btn-default"/>
			<input type="button" onclick="deleteDept_go();" value="삭제" class="btn btn-default"/>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└ 기획홍보부</div>
		<div class="col-md-9">
			<input type="button" onclick="updateDept_go();" value="수정" class="btn btn-default"/>
			<input type="button" onclick="deleteDept_go();" value="삭제" class="btn btn-default"/>
		</div>
	</div>
</div>

<script>
	function updateDept_go(){
		alert("부서수정 메서드");
		var url = "/enovironmentSetting/deptPopupForm";
	    
	    cw=screen.availWidth;     //화면 넓이
	    ch=screen.availHeight;    //화면 높이
	
	    sw=600;    //띄울 창의 넓이
	    sh=650;    //띄울 창의 높이
	
	    ml=(cw-sw)/2;        //가운데 띄우기위한 창의 x위치
	    mt=(ch-sh)/2;         //가운데 띄우기위한 창의 y위치
	
	    window.open(
	       url,
	       "_blank_1",
	       "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width="
	       +sw+",height="+sh+",top="+mt+",left="+ml
	    );
	}
	function deleteDept_go(){
		alert("부서삭제 메서드");
	}
</script>