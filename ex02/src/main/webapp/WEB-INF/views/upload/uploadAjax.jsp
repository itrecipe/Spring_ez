<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Insert title here</title>
<meta charset="UTF-8">
<!-- RWD -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- MS -->
<meta http-equiv="X-UA-Compatible" content="IE=chrome"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8,IE=EmulateIE9"/>

</head>
<body>

<%@include file="../include/header.jsp"%>

<div class="container mt-4 mb-4" id="mainContent">
		<div class="row">
			<div class="col-md-2">
				<h4 class="wordArtEffect text-success pl-4">메뉴</h4>
				<nav class="navbar bg-dark navbar-dark container">
					<!-- 수직 메뉴 -->
					<button class="navbar-toggler d-md-none" type="button"
						data-toggle="collapse" data-target="#collapsibleVertical">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse d-md-block"
						id="collapsibleVertical">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link" href="#"> <i
									class="fas fa-home" style="font-size: 30px; color: white;"></i>
							</a></li>
							<li class="nav-item"><a class="nav-link" href="register">게시물
									등록</a></li>
							<li class="nav-item"><a class="nav-link" href="#">리스트</a></li>
							<li class="nav-item"><a class="nav-link" href="#">도움말</a></li>
						</ul>
					</div>
				</nav>
			</div>

			<div class="col-md-10">
				<div id="submain">
					<h4 class="text-center wordArtEffect text-success">Ajax 파일 업로드</h4>
					
					<div class='bigPictyreWrapper w-90 mb-5 h-100 mx-auto'>
						<div class='bifPicture mt-4 h-100'>
						</div>
					</div>
					
					<!-- 업로드 입력 창 -->
					<div class="uploadDiv">
						<input type="file" name="uploadFile" multiple/>
					</div>
					
					<p>
						<button type="button" id="uploadBtn" class="btn btn-primary mt-3">Ajax Upload</button>
					</p>
 
					<!-- 업로드 결과 창 -->
					 
					<div class="uploadResult mt-3">
						<!-- 카드 이미지로 생성 -->
						<div class='row' id='card'>
							<!-- class="card"로 만들것 -->
						</div>
					</div>
					
					 
				</div> <!-- submain -->
			</div> <!-- col-md-10 -->					
		</div> <!-- row -->
	</div> <!-- mainContent -->

<%@include file="../include/imageModal.jsp"%>

<%@include file="../include/footer.jsp"%>

<script>
$(document).ready(function(){
	
	let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	//RegExp는 정규식 처리 코어 객체 exe, sh, zip, alz를 포함하고 있는 정규식 객체
	let maxSize = 5242880; //5MB
	let cloneObj = $(".uploadDiv").clone(); //입력하기 전 상태의 DOM을 복사한다.
	
	let uploadResult = $(".uploadResult #card");
	
	$("#uploadBtn").on("click", function(e){
	let formData = new FormData();
	//js가 제공하는 core객체 FormData, 비어있는 <form> 태그 엘리먼트의 DOM 객체
	let inputFile = $("input[name='uploadFile']"); //attr속성 selector
	let files = inputFile[0].files; //inputFile은 배열 이다. files는 배열의 요소로 배열 객체
	console.log(files);
	for(let i = 0; i < files.length; i++){
		
		if(!checkExtension(files[i].name, files[i].size)){
			return false;
		}
		formData.append("uploadFile", files[i]);
		//formData 객체가 제공하는 append(name속성명, value속성값)
	}
	//$.ajax 중괄호 안엔 객체형을 집어넣는다.
	$.ajax({
		url : 'uploadAjaxAction',
		processData : false, //필수
		contentType : false, //필수
		data : formData,
		type : 'POST', //필수
		success : function(result) { //result는 성공시 서버로부터 받는 데이터
			console.log(result);
			//alert(result);
			showUploadedFile(result); //JSON객체 형식으로 넘어온 결과를 보여주는 함수를 호출한다.
			
			$(".uploadDiv").html(cloneObj.html()); //입력 성공 후 입력 전(초기) 상태로 되돌려 준다.(초기화를 하라는 의미)
			alert("파일 첨부 완료!");
		}
	});
});
	
	function checkExtension(fileName, fileSize) {
		if(fileSize >= maxSize) {
			alert("파일 사이즈 초과");
			return flase;
		}
		if (regex.test(fileName)){
			//test는 RegExp코어 객체의 메서드로 정규식에 지정된 단어 포함 이후를 체크한다.
			alert("해당 종류의 파일은 업로드 할 수 없다.");
			return false;
		}
		return true;
	}

	function showUploadedFile(uploadResultArr) {
		
		//uploadResultArr는 서버로 부터 받은 JSON객체 타입의 result값
		let str = ""; //HTML로 만들 문자열
		
		//J-QUERY의 each문
		//i는 색인번호, Obj는 uploadResultArr을 구성하고 있는 원소
		$(uploadResultArr).each(function(i, obj) {
			//str += obj.fileName + "<br/>";
			if(!obj.image) { //이미지가 아닌 경우
				//한글이나 공백 등... URL에 포함되어 있을시를 해결한다. encodeURIComponent()
				let fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
				//YYYY/MM/DD/UUID_파일명
				//BS4의 카드 방식으로 표시한다.
				str += "<div class='card col-md-3'>";
				str += "<div class='card-body'>";
				str += "<p class='mr-2'>";
				str += "<a href='download?fileName=" + fileCallPath + "'>";
				str += "<i class='fa fa-paperclip fa-2x' aria-hidden='true'></i>" + obj.fileName;
				str += "</a>";
				str += "</p>";
				str += "<h4 class='mr-2'><span data-file='" + fileCallPath + "' data-type='file'> &times; </span></h4>";
				str += "</div>";
				str += "</div>";
			}
			else { //이미지인 경우에는 섬네일 파일 경로를 사용 한다.
				let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
				let originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName; //원본파일 경로
				originPath = originPath.replace(new RegExp(/\\/g),"/") //\\를 /로 대체 한다.
				
				str += "<div class='card col-md-3'>";
				str += "<div class='card-body'>";
				str += "<p class='mr-2'>";
				str += "<a href=\"javascript:showImage(\'" + originPath + "\')\">"; //원본 파일을 보기 위해 클릭 이벤트 처리
				//"<a href="javascript:showImage('" + kkkk + ')" 문자열로 처리해야해서 작성(이건 국쌤이 안 적어도 된다 하셨는데 걍 적어둠)
				str += "<img src='display?fileName=" + fileCallPath + "'></a>"; 
				//a의 클릭 아이콘, 클릭 링크 이미지, 직접 자원에 접근하지 못한다.(그래서 서버에서 읽어와서 보내줘야 한다.)
				str += "</p>";
				str += "<h4 class='d-inline-block mr-2'><span data-file='" + fileCallPath + "' data-type='image'> &times; </span></h4>";
				str += "</div>";
				str += "</div>";
			}
		});
		
		uploadResult.append(str);
	}
	//클릭 이벤트 처리
	$(".uploadResult").on("click","span", function(e){
		let targetFile = $(this).data("file"); //파일경로 (Ajax로 전송), this는 이벤트가 일어난 span
		let type = $(this).data("type"); //파일 형태
		console.log(targetFile);
		
		let targetLi = $(this).closest(".card"); //span소속 card엘리먼트
		
		$.ajax({
			url : 'deleteFile',
			data : {fileName : targetFile, type:type}, //객체형으로 보내고 서버는 각각의 속성으로 처리
			dataType : 'text',
			type : 'POST',
			success : function(result) {
				//alert(result);
				targetLi.remnove(); //화면에서 지우기	
			}
		});
	});
});

function showImage(fileCallPath) {
	//<a>태그에서 직접 호출시를 대비하여 J-Query밖에서 만든다.
	//alert("원본사진 보여주기");
	$('.imageMocal .modal-body').html("<img class='d-block w-75 mx-auto' src='display?fileName="
			+ encodeURI(fileCallPath)+"&size=1' >");
			$(".imageModal").modal("show");
	/* $('.imageModal .modal-body').html("<img class='d-block w-75 mx-auto' src='display?fileName=" 
			+ encodURI(fileCallPath) + "$size=1">") */
	
}
</script>
</body>
</html>