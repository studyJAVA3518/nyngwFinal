<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script language="javascript">
	function saveMember() {

		//alert($('#1').is(":checked"));

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

퇴사자 리스트
<form action="rmm" method="post">
	<table>
		<tr>
			<td><select id="mem_dept_number" name="mem_dept_number" class="btn btn-default">
					<option value="all" selected="selected">모든부서</option>
					<option value="dept3">인사부</option>
					<option value="dept4">기획홍보부</option>
					<option value="dept5">영업부</option>
					<option value="dept6">생산부</option>
					<option value="dept7">인사1팀</option>
					<option value="dept8">인사2팀</option>
					<option value="dept9">기획1팀</option>
					<option value="dept10">기획2팀</option>
			</select> <input type="text" name="mem_name" />
				<button class="btn btn-default">검색</button></td>
		</tr>
	</table>
	<table class="table table-bordered">
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

	<input type="button" value="복직" onclick="saveMember();"
		class="btn btn-default" /> <input type="button" value="퇴사" name=""
		class="btn btn-default" />
</form>