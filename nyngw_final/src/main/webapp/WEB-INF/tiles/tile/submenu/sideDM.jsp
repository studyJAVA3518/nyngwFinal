<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>					
				
<script>
	$(function() {

		//아코디언
		$("#accordion>ul>li").click(function() {
			if ($(this).children("ul").attr("style") == "display: block;") {
				$(this).children("ul").slideUp();
			} else {
				$(this).children().slideDown();

			}

		})
		$("#${sideValue}").addClass('sideBarActive');
		$("#accordion>ul>li>ul>#${sideValue}").parents().slideDown();
	})
</script>
	
	<!-- 문서 관리에 대한 서브메뉴 -->
					
					<h2 class="blind">문서관리</h2>
					<article>
						<div class="lst_snb" id="accordion">
							<ul>
								<li>
									<h3>문서관리</h3>
									<ul>
										<li id="sideMenu1"><a href="/documentManagement/documentManager/documentSelect">&nbsp;&nbsp;&nbsp;└ 일반 문서</a></li>
										<li id="sideMenu2"><a href="/documentManagement/documentManager/edocumentSelect">&nbsp;&nbsp;&nbsp;└ 전자문서</a></li>
									</ul>							
							</ul>
						</div><!-- side_list -->
					</article>
				<!-- side -->