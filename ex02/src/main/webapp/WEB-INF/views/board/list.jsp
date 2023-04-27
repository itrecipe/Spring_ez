<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>list</title>
<meta charset="UTF-8">
<!-- RWD -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- MS -->
<meta http-equiv="X-UA-Compatible" content="IE=chrome"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8,IE=EmulateIE9"/>

</head>
<body>

<%--지시자 include는 소스를 포함하여 컴파일시에 포함한다, 액션태그 include는 실행 시점에 include를 한다. --%>
<%@ include file="../include/header.jsp" %>

<!-- list화면 표시 -->
<div class="container mt-4 mb-4" id="mainContent">
	<div class="row">
		<div class="col-md-2">
			<h4 class="wordArtEffect text-success pl-4">메뉴</h4>
			<nav class="navar bg-dark navbar-dark container"> <!-- 수직 메뉴 -->
				<button class="navbar-toggler d-md-none" type="button" data-toggle="collapse"
					data-target="#collapsibleVertical">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse d-md-block" id="collapsibleVertical">
					<ul class="navbar-nav">
						<li class="nav-item">
							<a class="nav-link" href="#">
								<i class="fas fa-home" style="font-size: 30px; color:white;"></i>
							</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="register">게시물 등록</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">리스트</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">도움말</a>
						</li>
					</ul>				
				</div>
			</nav>
		</div> 
		
		<div class="col-md-10">
			<div id="submain">
				<h4 class="text-center wordArtEffect text-success">게시판</h4>
				
				<div>
					<button type="button" class="btn btn-primary float-right" id="regBtn">게시물 등록</button>
				</div>
				
				<div class="table-responsive-md">
					<table id="boardTable" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>수정일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="board">
								<tr>
									<td class="bno"><c:out value="${board.bno}"/></td>
									<td>
										<!-- jquery로 페이지 이벤트 처리 전  
										<a class="move" href='get?bno=<c:out value="${board.bno}"/>'>
											<c:out value="${board.title}"/>
										</a>
										 -->
										 <!--  jquery로 페이지 이벤트 처리 후 -->
										 <a class="move" href='<c:out value="${board.bno}"/>'>
										 	<c:out value="${board.title}"/>
										 </a>
									</td>
									<td><c:out value="${board.writer}"/></td>
									<!-- BoardVO에서 날짜는 Date형이므로 문자열로 변환해야 한다. -->
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}"/></td>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>	<!-- table responsive-md -->			
			</div>	<!-- submain -->
			<!--pagination - 페이지 표시-->
			<ul class="pagination justify-content-center" style="margin:20px 0">
				<c:if test="${pageMaker.prev}">
					<li class="page-item">
						<a class="page-link" href="${pageMaker.startPage-1}">Prev</a>
					</li>
				</c:if>
				<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
					<li class="page-item ${pageMaker.cri.pageNum == num ? 'active' : ''}">
						<a class="page-link" href="${num}">${num}</a>
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next}">
					<li class="page-item">
						<a class="page-link" href="${pageMaker.endPage + 1}">Next</a>
					</li>
				</c:if>
			</ul>
			
			<!-- 페이지 클릭시 처리를 담당하는 form(안보이게 해둠) -->
			<form id="actionForm" action="list" method='get'>
				<input type="hidden" name="pageNum" value='${pageMaker.cri.pageNum}'>
				<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
			</form>			
		</div> 
	</div>
</div>

<%@ include file="../include/messageModal.jsp" %>
<%@ include file="../include/footer.jsp" %>
<script>
$(document).ready(function(){
	let result = '<c:out value="${result}"></c:out>';
	//result는 redirect: 로 URL이동시 RedirectAttributes에 저장한 속성값
	console.log("result : " + result);
	checkModal(result);
	
	history.replaceState({}, null, null);
	//모달창이 활성화 된 뒤에 현재 히스토리를 전부 비운다.

	$("#regBtn").on("click", function(){
		self.location = "register";
	});
	
	let actionForm = $("#actionForm");
	$(".page-item a").on("click", function(e){
		e.preventDefault(); //a의 본래 기능을 취소시킨다.
		console.log('page 번호 클릭');
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		//find() 메서드는 자식 엘리먼트에서 selector에 해당하는 엘리먼트를 선택
		//pagaNum이 name인 input의 value에 클릭한 a의 href값(페이지 번호)를 넣어준다.
		//this는 이벤트가 일어난 객체이므로 <a>가 된다.
		actionForm.submit(); //submit(), reset()은 form의 이벤트
	});
	
	$(".move").on("click",function(e){
		e.preventDefault();
		actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href") + "'>");
		//메서드에 의해 구해지는 값으로 +로 연결해야 한다, 뒤에는 변수 선언시가 아닌 표시 이므로 보이는 대로 표시한다.
		actionForm.attr("action", "get");
		actionForm.submit();
	});
	
	function checkModal(result) {
		if(result == ""){
			return;
		}
		if(parseInt(result) > 0) {
			$(".modal-body #mbody").html("게시글 : " + parseInt(result) + "번이 등록 되었습니다");
		}
		else if(result == "success"){
			$(".modal-body #mbody").html("게시글 수정/삭제 처리가 되었습니다.");
		}
		else{
			return;
		}
		$("#messageModal").modal("show");
	}
});
</script>
</body>
</html>