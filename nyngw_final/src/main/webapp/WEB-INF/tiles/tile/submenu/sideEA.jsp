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
    		$("#${sideValue}").addClass('active');
	    })
	    
	</script>
					
					<h2 class="blind">전자결재</h2>
					<article>
						<div class="lst_snb" id="accordion">
							<ul>
								<li id="sideMenu1">
									<a href="/electronicApproval/draft/draft"><h3>기안하기</h3></a>
								</li>
							
								<li>
									<h3>결재진행</h3>
									<ul>
										<li id="sideMenu2"><a href="/electronicApproval/approvalProgress/waitingApproval">&nbsp;&nbsp;&nbsp;└ 미결재문서</a></li>
										<li id="sideMenu3"><a href="/electronicApproval/approvalProgress/completeApproval">&nbsp;&nbsp;&nbsp;└ 결재완료문서</a></li>
										<li id="sideMenu4"><a href="/electronicApproval/approvalProgress/refusedApproval">&nbsp;&nbsp;&nbsp;└ 반려문서</a></li>
									</ul>
								</li>
							
								<li>
									<h3>개인문서함</h3>                       
									<ul>                                 
										<li id="sideMenu5"><a href="/electronicApproval/individualDocumentBox/submitApprovalBox">&nbsp;&nbsp;&nbsp;└ 상신문서함</a></li>  
										<li id="sideMenu6"><a href="/electronicApproval/individualDocumentBox/completeApprovalBox">&nbsp;&nbsp;&nbsp;└ 결재완료문서함</a></li>  
										<li id="sideMenu7"><a href="/electronicApproval/individualDocumentBox/refusedApprovalBox">&nbsp;&nbsp;&nbsp;└ 반려문서함</a></li>  
									</ul>                                	
								</li>
								
								<li>
									<h3>그 외 문서함</h3>                       
									<ul>                                 
										<li id="sideMenu8"><a href="/electronicApproval/theRestDocumentBox/implementDocumentBox">&nbsp;&nbsp;&nbsp;└ 시행문서함</a></li>  
										<li id="sideMenu9"><a href="/electronicApproval/theRestDocumentBox/referenceDocumentBox">&nbsp;&nbsp;&nbsp;└ 참조문서함</a></li>  
										<li id="sideMenu10"><a href="/electronicApproval/theRestDocumentBox/overallDocumentBox">&nbsp;&nbsp;&nbsp;└ 전체문서함</a></li>  
									</ul>                                	
								</li>
							</ul>
						</div><!-- side_list -->
					</article>
				<!-- side -->