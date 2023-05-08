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
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8,IE=EmulateIE9"/> 
</head>
<body>

<%@include file="../include/header.jsp"%>

<div class="container mt-4 mb-4" id="mainContent" >
	<div class="row">
		<div class="col-md-2">
			<h4 class="wordArtEffect text-success pl-4">메뉴</h4>
			<nav class="navbar bg-dark navbar-dark container">
				<!-- RWD의 화면 축소시 나타나는 메뉴 버튼(상병계급장) -->
				<!-- d-md-none은 메뉴가 감추어지지 아노고 펼쳐지는 것 예방 -->
				<button class="navbar-toggler d-md-none" type="button" data-toggle="collapse" 
					data-target="#collapsibleVertical">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse d-md-block" id="collapsibleVertical">
					<ul class="navbar-nav">
						<li class="nav-item">
							<a class="nav-link" href="#">
								<i class="fas fa-home" style="font-size:30px;color:white;"></i>
							</a>
						</li>
						<li class="nav-item">
						 	<a class="nav-link" href="list">게시판 목록</a>
						</li>								
					</ul>
				</div>	
			</nav>
		</div>
		<div class="col-md-10">
			<div id="submain">
				<h4 class="text-center wordArtEffect text-success">게시글 수정</h4>
				<form  id="mform" name="mform" action="modify" method="post">
					<!-- 페이지 관련 정보 추가 -->
					<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
        			<input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
        			<!-- 검색 적용 -->	
        			<input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
					<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>				
					<div class="form-group">
						<label for="bno">번호:</label>
						<input type="text" class="form-control" id="bno" name="bno" readonly value='<c:out value="${board.bno }"/>' />		
					</div>
					<div class="form-group">
						<label for="title">제목:</label>
						<input type="text" class="form-control" id="title" name="title" value='<c:out value="${board.title }"/>'/>		
					</div>
					<div class="form-group">
						<label for="content">내용:</label>
						<textarea class="form-control" id="content" name="content" rows="10" >
							<c:out value="${board.content}" />
						</textarea>		
					</div>
					<div class="form-group">
						<label for="writer">작성자:</label>
						<input type="text" class="form-control" id="writer" name="writer" readonly value='<c:out value="${board.writer }"/>'/>		
					</div>
					<div class="form-group">
						<label for="regDate">게시일:</label>
						<input type="text" class="form-control" id="regDate" name="regDate" 
							value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.regDate}" />'  readonly/>		
					</div>
					<div class="form-group">
						<label for="updateDate">수정일:</label>
						<input type="text" class="form-control" id="updateDate" name="updateDate" 
							value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.updateDate}" />'  readonly/>		
					</div>
					<button type="submit" data-oper='modify' class="btn btn-info">Modify</button>&nbsp;&nbsp;
  					<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>&nbsp;&nbsp;						
					<button type="submit" data-oper='list' class="btn btn-success">List</button>					
				</form>
			</div>
		</div>
	</div>
</div>

<%@include file="../include/footer.jsp"%>

<script>
$(function(){
	let formObj = $("#mform");
	
	$("button").on("click",function(e){
		e.preventDefault(); //이벤트가 일어난 버튼의 기본 동작을 제거
		let operation = $(this).data("oper");
		//data(data-의 뒷이름)메서드는 속성이 html5에 새롭게 추가된 data-속성값을 참조하여 값을 반환
		console.log("operation : "  + operation);
		if(operation == "remove") {
			formObj.attr("action","remove");
		}
		else if(operation == "list") {
			formObj.attr("action", "list").attr("method","get");
			//페이지 정보
			let pageNumTag = $("input[name='pageNum']").clone(); //복사해둠
		    let amountTag = $("input[name='amount']").clone();
			//검색정보
		    let keywordTag = $("input[name='keyword']").clone();
		    let typeTag = $("input[name='type']").clone();
		    
			formObj.empty(); //formObj의 자식 엘리먼트를 모두 제거(4개포함 게시판 컬럼)
			//복사해둔 페이지 관련 정보를 다시 추가
			formObj.append(pageNumTag); //자식으로 붙여쓰기
		    formObj.append(amountTag);
		    formObj.append(keywordTag);
		    formObj.append(typeTag);	       
		}
		formObj.submit();
	});
	
});
</script>
</body>
</html>