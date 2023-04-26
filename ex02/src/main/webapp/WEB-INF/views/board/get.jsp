<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>get</title>
<meta charset="UTF-8">
<!-- RWD -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- MS -->
<meta http-equiv="X-UA-Compatible" content="IE=chrome"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8,IE=EmulateIE9"/>

</head>
<body>

<%@ include file="../include/header.jsp" %>

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
							<a class="nav-link" href='modify?bno=<c:out value="${board.bno}"/>'>수정</a>
						</li>
					</ul>
				</div>
			</nav>
		</div>
		<div class="col-md-10">
			<div id="submain">
				<h4 class="text-center wordArtEffect text-success">게시글 내용</h4>
				<form>
					<div class="form-group">
						<label for="bno">번호 : </label>
						<input type="text" class="form-control" id="bno" name="bno" readonly value='<c:out value="${board.bno}"/>'/>
					</div>
					<div class="form-group">
						<label for="title">제목 : </label>
						<input type="text" class="form-control" id="title" name="title" readonly value='<c:out value="${board.title}"/>'/>
					</div>
					<div class="form-group">
						<label for="content">내용 : </label>
						<textarea class="form-control" id="content" name="content" rows="10" readonly>
							<c:out value="${board.content}"/>
						</textarea>
					</div>
					<div class="form-group">
						<label for="writer">작성자 : </label>
						<input type="text" class="form-control" id="writer" name="writer" readonly value='<c:out value="${board.writer}"/>'/>
					</div>
				</form>
				<button type="button" data-oper="modify" class="btn btn-info">수정</button>
				&nbsp;&nbsp;
				<button data-oper="list" class="btn btn-danger">게시판 목록</button>
				<!-- 버튼 클릭을 처리하기 위한 form, 보이지 않는 창 -->
				<form id='operForm' action="modify" method="get">
					<input type='hidden' id='bno' name='bno'
						value='<c:out value="${board.bno}"/>'>
				</form>				
			</div>
		</div>
	</div>
</div>

<%@ include file="../include/footer.jsp" %>

<script>
$(document).ready(function() {
	let operForm = $('#operForm');
	$("button[data-oper='modify']").on("click", function(e) {
		//location.href='modify?bn0o=<c:out value="${board.bno}"/>';
		operForm.attr("action", "modify").submit();
	});
	$("button[data-oper='list']").on("click", function(e) {
		operForm.find('#bno').remove();
		//id가 bno인 DOM 객체를 찾아서 제거한다.
		operForm.attr("action", "list").submit();
		operForm.submit();
	});
});
</script>
</body>
</html>