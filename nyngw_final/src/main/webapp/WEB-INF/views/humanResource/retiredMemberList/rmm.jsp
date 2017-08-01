<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script language="javascript">
	function saveMember() {

		var items = [];
		$('input[name="mem_chk"]:checkbox:checked').each(function() {
			items.push($(this).val());
		});

		$.ajax({
			url : '/humanResource/retiredMemberList/save',
			type : 'post',
			data : {
				'mem_chk' : items
			},
			success : function(res) {
				alert('복직 처리되었습니다.');
				location.href="/humanResource/retiredMemberList/rmm";
			},
			error : function() {

			},
			dataType : 'json'
		})

	}
	
	 $(function(){  
	       $('#mem_dept_number option[value=${dept_number}]').prop('selected',true);
	   });
	
</script>
<h2>퇴사자 정보</h2>
<p class="docTitleDescription">
	퇴사자의 정보를 확인하고 복직처리를 할 수 있습니다.
</p>

<form action="rmm" method="post">
	
	<div class="retirementWrap">
	부서 선택 
		<select id="mem_dept_number" name="mem_dept_number" class="form-control docInputSelect">
			<option value="all" selected="selected">모든부서</option>
			
			<c:forEach items="${depList}" var="depList" begin="0" end="${depList.size()}">
				<option value="${depList.dept_number}">${depList.dept_name}</option>
			</c:forEach>
		</select> 
		<input type="text" name="mem_name" class="form-control docInputSearch"/>
		<button class="btn btn-default">검색</button></td>
	</div>
	<table class="table table-bordered textCenter">
		<tr>
			<th>퇴사</th>
			<th>부서</th>
			<th>이름</th>
			<th>직책</th>
			<th>연락처</th>
		</tr>
		<c:forEach items="${memberList}" var="member" varStatus="status">
			<tr>
				<td><input type="checkbox" id="${status.count}" name="mem_chk"
					value="${member.mem_id}" /></td>
				<td>${member.dept_name }</td>
				<td>${member.mem_name}</td>
				<td>${member.position_name }</td>
				<td>${member.mem_tel }</td>
			</tr>
		</c:forEach>
	</table>
	<div class="textCenter bottomBtnWrap">
		<c:if
			test="${page.currentPageNo >= page.firstPageNo + page.sizeOfPage}">
			<a href="/humanResource/retiredMemberList/rmm?page=${i}">이전</a>
		</c:if>
		<c:forEach begin="${page.startPageNo}" end="${page.endPageNo}" step="1"
			var="i">
			<a href="/humanResource/retiredMemberList/rmm?page=${i}">[${i}]</a>
		</c:forEach>
		<c:if test="${page.startPageNo+page.sizeOfPage < page.finalPageNo}">
			<a href="/humanResource/retiredMemberList/rmm?page=${i}">다음</a>
		</c:if>
	</div>
	<div class="textCenter">
		<input type="button" value="복직" onclick="saveMember();" class="btn btn-default" />
		<input type="button" value="퇴사" name="" class="btn btn-default" />
	</div>
</form>