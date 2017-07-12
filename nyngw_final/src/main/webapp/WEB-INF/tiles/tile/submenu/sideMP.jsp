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
									<h3>나의 근태 관리</h3>
									<ul>
										<li><a href="/mypage/myDalManagement/attended">&nbsp;&nbsp;&nbsp;└ 출결현황</a></li>
										<li><a href="/mypage/myDalManagement/vacation">&nbsp;&nbsp;&nbsp;└ 휴가현황</a></li>
									</ul>
									
								</li>
							
								<li>
									<h3>나의 급여 관리</h3>
									<ul>
										<li><a href="/mypage/myPayManagement/salary">&nbsp;&nbsp;&nbsp;└ 급여 명세서 보기</a></li>
										<li><a href="/mypage/myPayManagement/severance">&nbsp;&nbsp;&nbsp;└ 퇴직금 현황</a></li>
									</ul>
								</li>
							
								<li>
									<h3>기본 설정</h3>                       
									<ul>                                 
										<li><a href="/mypage/basicSetting/sign">&nbsp;&nbsp;&nbsp;└ 서명 등록</a></li>  
									</ul>                                	
								</li>
							</ul>
						</div><!-- side_list -->
					</article>
				<!-- side -->