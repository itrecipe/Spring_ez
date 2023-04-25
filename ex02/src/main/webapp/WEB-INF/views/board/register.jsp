<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>register</title>
<meta charset="UTF-8">
<!-- RWD -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- MS -->
<meta http-equiv="X-UA-Compatible" content="IE=chrome"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8,IE=EmulateIE9"/>

</head>
<body>

<%@include file="../include/header.jsp" %>

<!-- register 메인화면 -->
<div class="container mt-4 mb-4" id="mainContent">
	<div class="row">
		<div class="col-md-2">
			<h4 class="wordArtEffect text-success pl-4">메뉴</h4>
			<nav class="navbar bg-dark navbar-dark container">
				<!-- RWD의 화면 축소시 나타나는 메뉴 버튼(상병 계급장 모양) -->
				<!-- d-md-none은 메뉴가 감춰지지 않고 펼쳐지는 것을 예방한다. -->
				<button class="navbar-toggler d-md-none" type="button" data-toggle="collapse"
				data-target="#collapsibleVertical">
				<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse babvar-collapese d-md-block" id="collapsibleVertical">
					<ul class="navbar-nav">
						<li class="nav-item">
							<a class="nav-link" href="#">
								<i class="fas fa-home" style="font-size: 30px; color: white;"></i>
							</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="list">게시판 목록</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">추후메뉴</a>
						</li>
					</ul>
				</div>
			</nav>
		</div>		
		<div class="col-md-10">
			<div id="submain">
				<h4 class="text-center wordArtEffect text-success">게시물 등록</h4>
				<form action="../board/register" method="post" id="fwrite" name="freg" role="form">
					<div class="form-group">
						<label for="title">제목 : </label>
						<input type="text" class="form-control" id="title" placeholder="Enter Title"
						name="title" required="required"/>
					</div>
					<div class="form-group">
						<label for="content">내용 : </label>
						<textarea class="form-control" id="content" placeholder="Enter Content"
						name="content" row="10" required="required">
						</textarea>
					</div>
					<div class="form-group">
						<label for="writer">작성자 : </label>
						<input type="text" class="form-control" id="writer" name="writer"/>
					</div>
					<button type="submit" class="btn btn-success">작성</button>&nbsp;&nbsp;
					<button type="reset" class="btn btn-danger">취소</button>&nbsp;&nbsp;
					<a id="listLink" href="list" class="btn btn-primary">목록보기</a>
					
				</form>
			</div>		
		</div>
	</div>
</div>
<%@include file="../include/footer.jsp" %>
</body>
</html>