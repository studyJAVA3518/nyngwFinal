<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<title>파일 첨부</title>
	
	<!-- 다음오픈에디터 라이브러리 --> 
	<link rel="stylesheet" href="/cas/resources/daumeditor/css/popup.css" type="text/css"  charset="utf-8"/>
	<script src="/cas/resources/daumeditor/js/popup.js" type="text/javascript" charset="utf-8"></script>
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- jquery.form.js - ajaxSubmit() 사용 -->
	<script type='text/javascript' src='http://malsup.github.com/jquery.form.js'></script>
	
	<script>
		$(document).ready(function (){
			// 등록버튼 클릭 이벤트
			$('.submit a').on('click', function () {

			    var page = '${param.page}';    // 어디페이지에서 보냈는지 체크
			    var form = $('#daumOpenEditorForm');

			    form.ajaxSubmit({
			        type: 'POST',
			        url: '${pageContext.request.contextPath}/daumeditor/singleUploadFileAjax',
			        dataType: 'JSON',    // 반환되는 데이타 타입
			        data: {'page':page},
			        beforeSubmit: function(){ },
			        success: function(fileInfo) {
			             if(fileInfo.result==-1) {
			                  alert('파일이 5MB를 초과하였습니다.');
			                  return false;
			             } else {
			                  done(fileInfo);
			             }
			        }
			   });

			});
	
		    // <input type=file> 태그 기능 구현
		    $('.file input[type=file]').change(function (){
		        var inputObj = $(this).prev().prev();    // 두번째 앞 형제(text) 객체
		        var fileLocation = $(this).val();           // 파일경로 가져오기
	
		        inputObj.val(fileLocation.replace('C:\\fakepath\\',''));    // 몇몇 브라우저는 보안을 이유로 경로가 변경되서 나오므로 대체 후 text에 경로 넣기
		    });
	
		});
	
		//첨부한 파일을 에디터에 적용시키는 함수
		function done(fileInfo) { // fileInfo는 Ajax 요청 후 리턴하는 JSON형태의 데이터를 담을 객체
			if (typeof (execAttach) == 'undefined') {
				return;
			}

			var _mockdata = {
				'attachurl' : fileInfo.attachurl,
				'filemime' : fileInfo.filemime,
				'filename' : fileInfo.filename,
				'filesize' : fileInfo.filesize
			};
			execAttach(_mockdata); // 다음오픈에디터에 붙이기
			closeWindow(); // 파일 팝업 종료
		}

		//잘못된 경로로 접근할 때 호출되는 함수
		function initUploader() {
			var _opener = PopupUtil.getOpener();
			if (!_opener) {
				alert('잘못된 경로로 접근하셨습니다.');
				return;
			}

			var _attacher = getAttacher('file', _opener);
			registerAction(_attacher);
		}
	</script>
	
	<style>

    /* css */
    .header {
        background-image: none;
        background-color: #027dfc;
    }

    /* 파일첨부(.file) */
    .file {
        display: inline-block;
        margin-top: 8px;
        overflow: hidden;
    }

    .file .file-text {
        display: inline-block;
        padding: 6px 10px 8px 10px;
        border : 1px solid #c7c7c7;
        width: 179px;
        font-size: 14px;
        color: #8a8a8a;
        float: left;
    }

    .file .file-text:FOCUS {
        border-color: #54c4e5;
        outline: 0;
        -webkit-box-shadow: inset 0px 1px 1px rgba(0,0,0,0.075), 0px 0px 8px rgba(102,175,233,0.6);
        box-shadow: inset 0px 1px 1px rgba(0,0,0,0.075), 0px 0px 8px rgba(102,175,233,0.6);
    }

    .file .file-btn {
        margin-left: 2px;
        padding: 6px 8px 4px 8px;
        height: 20px;
        line-height: 20px;
        font-size: 12px;
        font-weight: bold;
        background-color: #fff;
        border: 1px solid #989898;
        color: #989898;
        cursor: pointer;
        float: left;
    }

    .file .file-btn:HOVER {
        background-color: #edfbff;
        border: 1px solid #009bc8;
        color: #009bc8;
    }

</style>

</head>

<body onload="initUploader();">

	<div class="wrapper">
		<div class="header">
			<h1>파일 첨부</h1>
		</div>	
		<div class="body">
			<dl class=alert>
				<dt>5MB이하만 가능합니다.</dt>
				<dd>
					<form id=daumOpenEditorForm encType=multipart/form-data method=post
						action="">

						<!-- 파일첨부 -->
						<div class=file>
							<input disabled class=file-text> <label class=file-btn
								for=uploadInputBox>파일첨부</label> <input id=uploadInputBox
								style="display: none" type=file name=Filedata>
							<!-- 버튼대체용(안보임) -->
						</div>
						<!-- //파일첨부 -->

					</form>
				</dd>
			</dl>

		</div>
		<div class="footer">
			<ul>
				<li class="submit"><a href="#" title="등록" class="btnlink">등록</a> </li>
				<li class="cancel"><a href="#" onclick="closeWindow();" title="취소" class="btnlink">취소</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
