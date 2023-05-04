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
						<input type="text" class="form-control" id="writer" name="writer" 
							readonly value='<c:out value="${board.writer}"/>'/>
					</div>
				</form>
				<button type="button" data-oper='modify' class="btn btn-info">수정</button>
				&nbsp;&nbsp;
				<button data-oper='list' class="btn btn-danger">게시판 목록</button>
				<!-- 버튼 클릭을 처리하기 위한 form, 보이지 않는 창 -->
				<form id='operForm' action="modify" method="get">
				
					<input type='hidden' id='bno' name='bno'
						value='<c:out value="${board.bno}"/>'>
				<!-- 페이지 정보 추가 -->	
					<input 
					type='hidden' name='pageNum' 
					value='<c:out value="${cri.pageNum}"/>'>

					<input 
					type='hidden' name='amount' 
					value='<c:out value="${cri.amount}"/>'>
					<!-- 검색 기능 적용 및 추가 -->
					<input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
					<input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
				</form>			
				<!-- 댓글 처리 창 -->
				<div class="row mt-4">
					<div class="col-md-12 clearfix">
						<i class="fas fa-reply fa-2x"></i> Reply
						<button id="addReplyBtn" class="btn btn-outline-primary float-right">
							New Reply
						</button>
					</div>
				</div>
				<div class="row mt-2">
					<div class="col-md-12">
					<ul class="char list-group">
						<li class='list-group-item clearfix' data-rno="12">
							<strong class="text-primary">user00</strong>
							<small class="float-right text-mute">2023-05-03</small>						
							<p>댓글 내용!</p>
						</li>
					</ul>
					</div>
				</div>
			</div> <!-- submain -->
		</div> <!-- 우측 col-md-10 -->
	</div> <!-- row -->
</div> <!-- mainContent -->

<%@ include file="../include/footer.jsp" %>

<%-- 외부 js파일 import --%>
<script src="../js/reply.js"></script>

<%-- 테스트용 script --%>
<!-- 테스트 종료 후 주석 처리 --> 
<!--  
<script>
	console.log("JS TEST");
	console.log(replyService);
	
	let bnoValue = '<c:out value="${board.bno}"/>'
	
	replyService.add(    
		{reply:"JS Test", replyer:"tester", bno:bnoValue},
			function(result){ 
			   alert("JS_TEST_RESULT: " + result);
			}
		);
	
	replyService.getList({bno:bnoValue, page:1}, function(list){
		//list는 getList에서 받는 성공시 데이터
		for(let i=0, len = list.length||0; i < len; i++){
			console.log(list[i]);
		}
	});
	
	replyService.remove(23, function(count){ //count는 성공시 서버에서 오는 데이터, 콜백 구현은 여기서 한다. 
		
			console.log(count);
		
			if(count === "success") {
				alert("REMOVED!");
			}
		}, function(err) {
			alert('REMOVE_ERROR...');
	});
		
		replyService.update({
			rno : 11,
			bno : bnoValue,
			reply : "Modified Reply..."
		
		}, 
		function(result) {
			alert("UPDATE_SUCCESS!");
	});
	
	 replyService.get(10, function(data){
		    console.log(data);
		    alert("LIST_SELECT_SUCCESS!");
	});
	
</script>
-->

<!-- 댓글 처리, spript 엘리먼트는 필요한 위치에서 여러개 사용해도 무방하다. -->
<script>
	$(document).ready(function(){
		let bnoValue = '<c:out value="${board.bno}"/>';
		let replyUL = $(".chat");
		
		showList(1);
		
		function showList(page) {
			console.log("show list " + page);
			replyService.getList({bno:bnoValue, page: page || 1},
					function(list){ //list는 서버에서 ArrayList(배열형태, 요소는 reply객체의 JSON 배열);
				//자바스크립트에서는 JS객체 처럼 사용
				var str="";
				
				if(list == null || list.length == 0) {
					replyUL.html("");
					return;
				}
				
				for(let i=0, len = list.length || 0; i < len; i++) {
					str += "<li class='list-group-item clearfix' data-rno='" + list[i].rno + "'>";
					str += "<strong class='text-primary'>" + list[i].replyer + "</strong>";
					//str += "<small class='float-right text-mute'>" + list[i].replyDate + "</small>";
					str += "<small class='float-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small>";
					str += "<p>" + list[i].reply + "</p>";
					str += "</li>";
				}

				//자바에서 Date객체는 ajax로 클라이언트에서 처리할시 posix로 처리한다.
				replyUL.html(str);
			});
		}
		
		let modal = $("#myReplyModal");
		let modalInputReply = modal.find("input[name='reply']"); //find는 후손중에서 선택한다.
		let modalInputReplyer = modal.find("input[name='replyer']");
		let modalInputReplyDate = modal.find("input[name='replyDate']");
		
		let modalModBtn = $("#modalModBtn");
		let modalRemoveBtn = $("#modalModRemoveBtn");
		let modalRegisterBtn = $("#modalRegisterBtn");
		
		$("#addReplyBtn").on("click", function(e){
			
			modal.find("input").val(""); //input 값 초기화
			modalInputReplyDate.closert("div").hide(); //날짜 입력 DOM은 감춘다.
			modal.find("button[id] != 'modalCloseBtn'}").hide();
			
			modalRegisterBtn.show(); //등록버튼 다시 보이게
			
			$(".modal").modal("show");
		});

		//댓글 등록 이벤트
	   modalRegisterBtn.on("click",function(e){
	      
	      var reply = {
	            reply: modalInputReply.val(),
	            replyer:modalInputReplyer.val(),
	            bno:bnoValue
	          };
	      replyService.add(reply, function(result){
	        
	        alert(result);
	        
	        modal.find("input").val("");
	        modal.modal("hide");
	        
	        showList(1); //등록 후 댓글 목록 보이게 한다.
	        //showList(-1);
        
      });
    });
	
	//댓글 조회 이벤트
	$(".chat").on("click", "li", function(e){ //li는 .chat의 자식(후손)
	
		let rno =$(this).data("rno"); 
		//이벤트가 일어난 li는 this
		//data(data-의 값)은 data-값으로 되어있는 DOM을 선택한다.
		
		replyService.get(rno, function(reply){
		//reply는 서버에서 받은 ReplyVO의 JSON인데 바로 JS의 객체로 처리한다.
			modalInputReply.val(reply.reply);
			modalInputReplyer.val(reply.replyer);
			modalInputReplyDate.val(replyService.displayTime( reply.replyDate))
			.attr("readonly", "readonly");
			modal.data("rno", reply.rno);
			//data-rno속성을 reply.rno로 추가한다.
			
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");
		});
	});
	
	//댓글 수정 이벤트
	modalModBtn.on("click", function(e){
		var reply = {rno:madal.data("rno"), reply: modalInputReply.val()};
		
		replyService.update(reply, function(Result){
			
			alert(result);
			modal.modal("hide");
			showList(1);
		});
	});
	
	//댓글 삭제 이벤트
	modalRemovalBtn.on("click", function(e){
		
		var rno = modal.data("rno");
		
		replyService.remove(rno, function(result){
			
			alert(result);
			modal.modal("hide");
			showList(1);
		});
	});
	
	
</script>

<!-- 게시판 상세보기 창에서 이벤트 처리하기 -->
<script>
$(document).ready(function() {
	let operForm = $('#operForm');
	
	$("button[data-oper='modify']").on("click", function(e) {
		//location.href='modify?bno=<c:out value="${board.bno}"/>';
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