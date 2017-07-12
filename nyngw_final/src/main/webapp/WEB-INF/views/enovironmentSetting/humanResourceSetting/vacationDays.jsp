<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!-- 환경설정관리 -> 인사부 설정 -> 휴가 일수 설정에 대한 화면 -->

<h2>휴가 일수 설정</h2>
<p>직원별로 휴가일수를 관리할 수 있으며, 휴가일수를 설정하면 직원들의 휴가 사용현황을 보다 정확하게 보실 수 있습니다.</p>
<div class="row">
	<div class="col-md-6">
		<h4>책정기준</h4>
		<div role="tabpanel">
		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#days" aria-controls="home" role="tab" data-toggle="tab">근무일수 기준</a></li>
		    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">직급기준</a></li>
		  </ul>
		
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="days">홈</div>
		    <div role="tabpanel" class="tab-pane" id="profile">프로필</div>
		  </div>
		
		</div>
	</div>
</div>

<script>
$(function(){
	$('#myTab a').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
	})
})
</script>