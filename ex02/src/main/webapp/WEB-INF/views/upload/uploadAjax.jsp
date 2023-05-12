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
					<!-- 
					<div class="uploadResult">
						<input type="file" name="uploadFile" multiple/>
					</div>
					 -->
					 
				</div> <!-- submain -->
			</div> <!-- col-md-10 -->					
		</div> <!-- row -->
	</div> <!-- mainContent -->

<%@include file="../include/footer.jsp"%>

<script>
$(document).ready(function(){
	
	let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	//RegExp는 정규식 처리 코어 객체 exe, sh, zip, alz를 포함하고 있는 정규식 객체
	let maxSize = 5242880; //5MB
	let cloneObj = $(".uploadDiv").clone(); //입력하기 전 상태의 DOM을 복사한다.
	
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
			
			$(".uploadDiv").html(cloneObj.html()); //입력 성공 후 입력 전(초기) 상태로 되돌려 준다.
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
		$(uploadResultArr).each(function(i, obj)) {
			
		}
	}
});
</script>
</body>
</html>