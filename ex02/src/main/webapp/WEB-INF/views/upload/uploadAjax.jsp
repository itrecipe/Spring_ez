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
					<div class="uploadDiv">
						<input type="file" name="uploadFile" multiple/>
					</div>
 

				</div> <!-- submain -->
			</div> <!-- col-md-10 -->					
		</div> <!-- row -->
	</div> <!-- mainContent -->

<%@include file="../include/footer.jsp"%>
</body>
</html>