<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>

<script>
	function readonlyfalse(){
		
	}
	function goPopup(){
		var pop = window.open("/humanResource/memberJoin/mjm","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	}
</script>

<table>
	<tr>
		<td><input type="radio" name="member" value="onAndOff" checked/></td>
		<td>사원번호</td>
		<td>사원아이디</td>
		<td>사원이름</td>
		<td>부서명</td>
		<td>직급</td>
		<td>사원생년월일</td>
		<td>사원주소</td>
		<td>사원메일</td>
		<td>사원연락처</td>
		<td>거래은행</td>
		<td>계좌번호</td>
		<td>퇴사여부</td>
	</tr>
	<c:forEach items="${joinMemberList}" var="member">
		<tr>
			<td><input type="radio" name="member" value="${member.mem_id }"/></td>
			<td><input type="text" value="${member.mem_number }" name="${member.mem_number }" readonly/></td>
			<td><input type="text" value="${member.mem_id }" name="${member.mem_id }" readonly/></td>
			<td><input type="text" value="${member.mem_name }" name="${member.mem_name }" readonly/></td>
			<td><input type="text" value="${member.mem_dept_number }" name="${member.mem_dept_number }" readonly/></td>
			<td><input type="text" value="${member.mem_position_number }" name="${member.mem_position_number }" readonly/></td>
			<td><input type="text" value="${member.mem_reg }" name="${member.mem_reg }" readonly/></td>
			<td><input type="text" value="${member.mem_addr1 }" name="${member.mem_addr1 }" readonly/></td>
			<td><input type="text" value="${member.mem_email }" name="${member.mem_email }" readonly/></td>
			<td><input type="text" value="${member.mem_tel }" name="${member.mem_tel }" readonly/></td>
			<td><input type="text" value="${member.mdi_bank }" name="${member.mdi_bank }" readonly/></td>
			<td><input type="text" value="${member.mdi_bank_account }" name="${member.mdi_bank_account }"/></td>
			<c:if test="${member.mem_retirement  == 1}">
				<td><input type="checkbox" name="${member.mem_id }"/></td>
			</c:if>
		</tr>
	</c:forEach>
</table>

<form action="excelMemberRank">
	<button class="btn btn-default">엑셀출력</button>
</form>
<input type="button" onclick="goPopup();" value="사원등록 "/>



