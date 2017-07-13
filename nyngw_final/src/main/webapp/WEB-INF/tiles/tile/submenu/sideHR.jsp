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
					
					<h2 class="blind">서브 메뉴</h2>
					<article>
						<div class="lst_snb" id="accordion">
							<ul>
								<li>
									<h3>사원 관리</h3>
									<ul>
										<li><a href="/humanResource/dalManagement/hrm">&nbsp;&nbsp;&nbsp;└ 근태관리</a></li>
										<li><a href="/humanResource/vacationManagement/vmtm">&nbsp;&nbsp;&nbsp;└ 휴가관리</a></li>
										<li><a href="/humanResource/payManagement/pmm">&nbsp;&nbsp;&nbsp;└ 급여관리</a></li>
									</ul>
									
								</li>
							
								<li>
									<h3>인사 관리</h3>
									<ul>
										<li><a href="/humanResource/memberJoin/mjm">&nbsp;&nbsp;&nbsp;└ 사원 등록</a></li>
										<li><a href="/humanResource/joinMemberList/jlm">&nbsp;&nbsp;&nbsp;└ 사원 목록</a></li>
										<li><a href="/humanResource/retiredMemberList/rmm">&nbsp;&nbsp;&nbsp;└ 퇴사자 목록</a></li>
									</ul>
								</li>
							</ul>
						</div><!-- side_list -->
					</article>
				<!-- side -->