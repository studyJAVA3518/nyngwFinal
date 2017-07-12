<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>					
				
	<script>
    	$(function(){
    		
	    	
    		//아코디언
    		$("#accordion>ul>li").click(function(){
    			if($(this).children("ul").attr("style")=="display: block;"){
	    			$(this).children("ul").slideUp();
    			}else{
	    			$(this).children().slideDown();
    			}
    		
    		})
	    	
	    })
	    
	</script>
					
					<h2 class="blind">전자결재</h2>
					<article>
						<div class="lst_snb" id="accordion">
							<ul>
								<li>
									<a href="/electronicApproval/draft/draft"><h3>기안하기</h3></a>
								</li>
							
								<li>
									<h3>결재진행</h3>
									<ul>
										<li><a href="/electronicApproval/approvalProgress/waitingApproval">&nbsp;&nbsp;&nbsp;└ 미결재문서</a></li>
										<li><a href="/electronicApproval/approvalProgress/completeApproval">&nbsp;&nbsp;&nbsp;└ 결재완료문서</a></li>
										<li><a href="/electronicApproval/approvalProgress/refusedApproval">&nbsp;&nbsp;&nbsp;└ 반려문서</a></li>
									</ul>
								</li>
							
								<li>
									<h3>개인문서함</h3>                       
									<ul>                                 
										<li><a href="/electronicApproval/individualDocumentBox/submitApprovalBox">&nbsp;&nbsp;&nbsp;└ 상신문서함</a></li>  
										<li><a href="/electronicApproval/individualDocumentBox/outbox">&nbsp;&nbsp;&nbsp;└ 임시보관함</a></li>  
										<li><a href="/electronicApproval/individualDocumentBox/completeApprovalBox">&nbsp;&nbsp;&nbsp;└ 결재완료문서함</a></li>  
										<li><a href="/electronicApproval/individualDocumentBox/refusedApprovalBox">&nbsp;&nbsp;&nbsp;└ 반려문서함</a></li>  
									</ul>                                	
								</li>
								
								<li>
									<h3>그 외 문서함</h3>                       
									<ul>                                 
										<li><a href="/electronicApproval/theRestDocumentBox/implementDocumentBox">&nbsp;&nbsp;&nbsp;└ 시행문서함</a></li>  
										<li><a href="/electronicApproval/theRestDocumentBox/referenceDocumentBox">&nbsp;&nbsp;&nbsp;└ 참조문서함</a></li>  
										<li><a href="/electronicApproval/theRestDocumentBox/overallDocumentBox">&nbsp;&nbsp;&nbsp;└ 전체문서함</a></li>  
									</ul>                                	
								</li>
								<li>
									<a href="/electronicApproval/approvalLineManagement/approvalLineManagement"><h3>결재라인관리</h3></a>               
								</li>
							</ul>
						</div><!-- side_list -->
					</article>
				<!-- side -->