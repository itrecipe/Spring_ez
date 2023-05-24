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

<%@ include file="../include/header.jsp" %>

<div class="container mt-4 mb-4" id="mainContent">
	<div class="row">
		<div class="col-md-2">
		<h4 class="wordArtEffect text-success pl-4">메뉴</h4>
			<nav class="navbar bg-dark navbar-dark container">
				<!-- RWD의 화면 축소시 나타나는 메뉴 버튼(상병계급장) -->
				<!-- d-md-none은 메뉴가 감추어지지 아노고 펼쳐지는 것 예방 -->
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
						<li class="nav-item"><a class="nav-link" href="list">게시판
								목록</a></li>
						<li class="nav-item"><a class="nav-link"
							href='modify?bno=<c:out value="${board.bno }"/>'>수정</a></li>
					</ul>
				</div>
			</nav>
		</div>
		<div class="col-md-10">
			<div id="submain">	
				<h4 class="text-center wordArtEffect text-success">회원가입</h4>
				<form action="../member/memberJoin" method="post" id="frm1" name="frm1">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="form-group">
						<label for="uId">ID</label>
						<input type="email" class="form-control" name="userid" placeholder="email 주소 입력" id="uId" required="required"/>
					</div>
					<div class="form-group">
						<button type="button id="id_chk" class="btn btn "></button>
						<input type="hidden" class="form-control" name="userid" placeholder="email 주소 입력" id="uId" required="required"/>
					</div>
					
					<button type="submit" class="btn btn-success">회원가입</button>
				</form>
		</div>
	</div>
</div>

<%@ include file="../include/footer.jsp" %>

<script>
$(document).ready(function(){
	$("#id_chk").click(function(e){
		e.preventDefault();
		if(document.frm1.userid.value == "") {
			alert("ID를 입력하라!");
			document.frm1.userid.focus();
			return false;
		}
		
		let userid = document.frm.userid.value;
		
		$.ajax({
			url : "../member/idChk?userid=" + userid,
			type : "GET",
			success : function(result){
				if(result == "succerss") {
					document.frm1.reid.value = userid;
					alert("사용 가능한 ID!");
				} else {
					document.frm1.userid.focus();
					document.frm1.userid.value = "";
					alert("다른 ID를 등록하라!");
				}
			}
		});
	});
	
	$("#uPwChk").blur(function(){
		if(document.frm1.userpw.value != document.fr1.userpwChk.value) {
			alert("암호가 일치하지 않음");
			document.frm1.userpw.value = "";
			frm1.userpwChk.value = "";
			frm.userpw.focus();
			return false;
		}
	});
	
	$("#frm1").submit(function(){
		if(document.frm1.reid.)		
	});

});
</script>
</body>
</html>