<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>                                                                                                               
<%@page import="org.springframework.security.core.userdetails.User"%>                                                                                                                            
<%@page import="java.security.Principal"%>                                                                                                                                                       
<%@ page language="java" contentType="text/html; charset=UTF-8"                                                                                                                                  
    pageEncoding="UTF-8"%>                                                                                                                                                                       
<%@ page trimDirectiveWhitespaces="true" %>                                                                                                                                                      
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>                                                                                                                                 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>                                                                                                                            
                                                                                                                                                                                                 
<style>                                                                                                                                                                                          
	.tableTd{                                                                                                                                                                                    
 		width: 17%;                                                                                                                                                                              
 		height: 50px;                                                                                                                                                                            
	}                                                                                                                                                                                            
	.tableSign{                                                                                                                                                                                  
 		height: 80px;                                                                                                                                                                            
	}                                                                                                                                                                                            
	.memSign {                                                                                                                                                                                   
		display : block;                                                                                                                                                                         
		width : 80px;                                                                                                                                                                            
		height : 60px;                                                                                                                                                                           
		margin :0 auto;                                                                                                                                                                          
		background-size: contain;                                                                                                                                                                
		background-repeat: no-repeat;                                                                                                                                                            
		background-position : top center;                                                                                                                                                        
	}     
	.txc-table{
		margin :0 auto;
	}    
	
	.tableTd1{
		 width:120px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd2{
		 width:150zpx;
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd3{
		 width:130px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd4{
		 width:100px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
	.tableTd5{
		 width:160px; 
		 height:37px;
		 border-bottom: 1px solid #ddd;
		 vertical-align: middle;
	}
</style>                        
                                                                                                                                                                 
<script type="text/javascript">                                                                                                                                                                  
$(function(){                                                                                                                                                                                    
	//기본 설정 dialog 보이지 않게                                                                                                                                                                        
	$('#approvalHistoryDialog').css('display', 'none');                                                                                                                                          
	                                                                                                                                                                                             
	/////////////////////////////////////////////////////////////////////////                                                                                                                    
    //결재 이력                                                                                                                                                                                      
	$("#approvalHistory_go").click(function(){                                                                                                                                                   
		$.ajax({                                                                                                                                                                                 
			url:'/electronicApproval/individualDocumentBox/completeAllrovalDetail',                                                                                                              
			type:'get',                                                                                                                                                                          
			data: $("#buttonForm").serialize(),                                                                                                                                                  
			success : function(res){                                                                                                                                                             
				var code = "";                                                                                                                                                                   
				$.each(res, function (i,value){                                                                                                                                              
					code+='<tr><td class="tableTd1">'+value.dept_name+'</td>';                                                                                                                                    
					code+='<td class="tableTd2">'+value.position_name+'</td>';                                                                                                                                    
					code+='<td class="tableTd3">'+value.mem_name+'</td>';                                                                                                                                         
					code+='<td class="tableTd4">'+value.ah_status+'</td>';                                                                                                                                        
					code+='<td class="tableTd5">'+value.ah_time+'</td></tr>';                                                                                                                                     
				});                                                                                                                                                                              
				$("#historyList").append(code);                                                                                                                                                  
			},                                                                                                                                                                                   
			dataType : 'json'                                                                                                                                                                    
		})                                                                                                                                                                                       
		                                                                                                                                                                                         
		$('#approvalHistoryDialog').dialog({                                                                                                                                                     
			width: 700,                                                                                                                                                                          
			height: 500,                                                                                                                                                                         
			modal: true,                                                                                                                                                                         
			buttons: {                                                                                                                                                                           
			   "확인": function() {                                                                                                                                                                
					$(this).dialog("close");                                                                                                                                                     
				}                                                                                                                                                                                
			},                                                                                                                                                                                   
			close: function() {                                                                                                                                                                  
				$('#historyList').html('<tr><th class="tableTd1">부서</th><th class="tableTd2">직급</th><th class="tableTd3">이름</th><th class="tableTd4">결재종류</th><th class="tableTd5">결재시간</th></tr>'); 
			}                                                                                                                                                                                    
		});                                                                                                                                                                                      
                                                                                                                                                                                                 
	})	                                                                                                                                                                                         
})                                                                                                                                                                                               
</script>     
<h2>결재완료문서 디테일</h2>                                                                                                                                                                                   
<!-- 선택한 결재문서의 정보들을 가지고 들어와야하고 이 때 필요한 정보를 hidden에 담아 주자 -->                                                                                                                                     
<%-- <input type="hidden" name="ea_number" value="${ea_number}"> --%>                                                                                                                            
<form name="hiddenForm" id="buttonForm">                                                                                                                                                         
	<input type="hidden" name="ea_number" value="${eaVO.ea_number }">                                                                                                                            
	<button type="button" id="approvalHistory_go"  class="btn btn-default divALM">결재이력</button>                                                                                                                                  
</form>                                                                                                                                                                                          
                                                                                                                                                                                                 
<div id="approvalHistoryDialog" class="textCenter">                                                                                                                                                                 
	<h2>결재상태 이력보기</h2>                                                                                                                                                                                    
	<table class="table tableGray" id="historyList">                                                                                                                                                       
		<tr>                                                                                                                                                                                     
			<th class="tableTd1">부서</th>                                                                                                                                                                          
			<th class="tableTd2">직급</th>                                                                                                                                                                          
			<th class="tableTd3">이름</th>                                                                                                                                                                          
			<th class="tableTd4">결재종류</th>                                                                                                                                                                        
			<th class="tableTd5">결재시간</th>                                                                                                                                                                        
		</tr>                                                                                                                                                                                    
	</table>                                                                                                                                                                                     
</div>                                                                                                                                                                                           
                                                                                                                                                                                                 
<div>                                                                                                                                                                                            
	<input type="hidden" name="ea_doc_number" value="${eaVO.ea_doc_number }">                                                                                                                    
	<table class="table table-bordered tableGray">                                                                                                                                                         
		<tr>                                                                                                                                                                                     
			<th class="tableTh">품의번호</th>                                                                                                                                                        
			<td colspan="5">${eaVO.ea_number }</td>                                                                                                                                              
			<input type="hidden" name="ea_number" value="${eaVO.ea_number }">                                                                                                                    
		</tr>                                                                                                                                                                                    
		<tr>                                                                                                                                                                                     
			<th class="tableTh">작성일자</th>                                                                                                                                                        
			<td colspan="5">${eaVO.ea_writedate }</td>                                                                                                                                           
		</tr>                                                                                                                                                                                    
		<tr>                                                                                                                                                                                     
			<th class="tableTh">기안자</th>                                                                                                                                                         
			<td colspan="5">${mem_name }</td>                                                                                                                                                    
		</tr>                                                                                                                                                                                    
		<!-- 결재자 이름-->                                                                                                                                                                           
		<tr id="approvalMember">                                                                                                                                                                 
			<th rowspan="2">결재</th>                                                                                                                                                              
			<c:forEach items="${approvalMember}" var="member">                                                                                                                                   
				<th class="tableTd">${member.mem_name }</th>                                                                                                                                     
			</c:forEach>                                                                                                                                                                         
			<c:forEach var="i" begin="1" end="${lastAstPriorityOfA }" step="1">                                                                                                                  
				<th class="tableTd"></th>                                                                                                                                                        
			</c:forEach>                                                                                                                                                                         
		</tr>                                                                                                                                                                                    
		<!-- 결재자 서명 -->                                                                                                                                                                          
		<tr id="approvalStatus">                                                                                                                                                                 
			<c:forEach items="${approvalMem_sign}" var="sign" varStatus="index">                                                                                                                 
				<td id="approvalStatus${index.count}"><div class="tableTd tableSign memSign" style="background-image:url('/resources/memsign/${sign }')"></div></td>                             
			</c:forEach>                                                                                                                                                                         
			<c:forEach var="i" begin="${noStartA }" end="${noEndA }" step="1">                                                                                                                   
				<td id="approvalStatus${i}" class="tableTd tableSign"></td>                                                                                                                      
			</c:forEach>                                                                                                                                                                         
			<c:forEach var="i" begin="${emptyStartA }" end="5" step="1">                                                                                                                         
				<td id="approvalStatus${i}" class="tableTd tableSign"></td>                                                                                                                      
			</c:forEach>                                                                                                                                                                         
		</tr>                                                                                                                                                                                    
		<!-- 합의자 이름 -->                                                                                                                                                                          
		<tr id="agreementMember">                                                                                                                                                                
			<th rowspan="2">합의</th>                                                                                                                                                              
			<c:forEach items="${agreementMember}" var="member">                                                                                                                                  
				<th class="tableTd">${member.mem_name }</th>                                                                                                                                     
			</c:forEach>                                                                                                                                                                         
			<c:forEach var="i" begin="1" end="${lastAstPriorityOfB}" step="1">                                                                                                                   
				<th class="tableTd"></th>                                                                                                                                                        
			</c:forEach>                                                                                                                                                                         
		</tr>                                                                                                                                                                                    
		<!-- 합의자 서명 -->                                                                                                                                                                          
		<tr id="agreementStatus">                                                                                                                                                                
			<c:forEach items="${agreementMem_sign}" var="sign" varStatus="index">                                                                                                                
				<td id="agreementStatus${index.count}"><div class="memSign tableTd tableSign" style="background-image:url('/resources/memsign/${sign }')"></div></td>                            
			</c:forEach>                                                                                                                                                                         
			<c:forEach var="i" begin="${noStartB }" end="${noEndB}" step="1">                                                                                                                    
				<td id="agreementStatus${i}"  class="tableTd tableSign"></td>                                                                                                                    
			</c:forEach>                                                                                                                                                                         
			<c:forEach var="i" begin="${emptyStartB }" end="5" step="1">                                                                                                                         
				<td id="agreementStatus${i}"  class="tableTd tableSign"></td>                                                                                                                    
			</c:forEach>                                                                                                                                                                         
		</tr>                                                                                                                                                                                    
		<tr>                                                                                                                                                                                     
			<th class="tableTh">시행자</th>                                                                                                                                                         
			<td colspan="5">${implementMemberName}</td>                                                                                                                                          
		</tr>                                                                                                                                                                                    
		<tr>                                                                                                                                                                                     
			<th class="tableTh">수신 및 참조</th>                                                                                                                                                     
			<td colspan="5">${referenceMemberName}</td>                                                                                                                                          
		</tr>                                                                                                                                                                                    
		<tr>                                                                                                                                                                                     
			<th class="tableTh">시행일자</th>                                                                                                                                                        
			<td colspan="5">${eaVO.ea_startdate } ~ ${eaVO.ea_enddate}</td>                                                                                                                      
		</tr>                                                                                                                                                                                    
		<tr>                                                                                                                                                                                     
			<th class="tableTh">제목</th>                                                                                                                                                          
			<td colspan="5">${eaVO.ea_title }</td>                                                                                                                                               
		</tr>                                                                                                                                                                                    
		<tr>                                                                                                                                                                                     
			<th colspan="6">내용</th>                                                                                                                                                              
		</tr>                                                                                                                                                                                    
		<tr>                                                                                                                                                                                     
			<td colspan="6">                    
				<div class="textCenter" style="margin: 0 auto;">                                                                                                                                                 
					${eaVO.ea_content }
				</div>                                                                                                                                                              
			</td>	                                                                                                                                                                             
		</tr>                                                                                                                                                                                    
	</table>      
	<div class="textCenter">   
	<c:choose>
		<c:when test="${checkBox eq 'ap' }">
			<a href="/electronicApproval/approvalProgress/completeApproval"><button type="button" class="btn btn-default">뒤로</button></a>
		</c:when>
		<c:otherwise>
			<a href="/electronicApproval/individualDocumentBox/completeApprovalBox"><button type="button" class="btn btn-default">뒤로</button></a>
		</c:otherwise>
	</c:choose>                                                                                                                                                                            
	</div>                                                                                         
</div>                                                                                                                                                                                           