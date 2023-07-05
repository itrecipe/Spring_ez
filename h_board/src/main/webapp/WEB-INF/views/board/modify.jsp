<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@include file="../include/header.jsp" %>

	<div class="container mt-4 mb-4" id="mainContent">
	
	<!-- 버튼 클릭을 처리하기 위해 form추가, 보이지 않게 설정해둠 -->
	  	<form id='operForm' action="modify" method="get">
	  		<input type="hidden" id="bno" name="bno"
	  		value='<c:out value="${h_board.bno}"/>'>
		</form>
	
		<form id="mform" name="mform" action="modify" method="post">
			<div class="form-group">
				<label for="bno">번호 : </label>
				<input type="text" class="form-control" id="bno" name="bno" 
					value='<c:out value="${h_board.bno}"/>' />
			</div>
			<div class="form-group">
				<label for="title">제목 : </label>
				<input type="text" class="form-control" id="title" name="title" 
					value='<c:out value="${h_board.title}"/>' />
			</div>
			<div class="form-group">
				<label for="content">내용 : </label>
<textarea class="form-control" id="content" name="content" rows="10" >
<c:out value="${h_board.content}"/>
</textarea>				
			</div>
			<div class="form-group">
				<label for="writer">작성자 : </label>
				<input type="text" class="form-control" id="writer" name="writer" 
					value='<c:out value="${h_board.writer}"/>' />
			</div>
			<div class="form-group">
				<label for="phone">폰 번호 : </label>
				<input type="text" class="form-control" id="phone" name="phone"
					value='<c:out value="${h_board.phone}"/>' />
			</div>
		</form>

			<button type="submit" data-oper='modify' class='btn btn-success'>수정</button>&nbsp;&nbsp;
			<button type="submit" data-oper='remove' class='btn btn-danger'>삭제</button>&nbsp;&nbsp;
			<button type="submit" data-oper='list' class='btn btn-info'>목록</button>
			<!-- data-oper 속성은 HTML 요소에 사용자 정의 데이터를 저장하기 위해 사용되는 속성, 
				클라이언트 측 스크립트에서 해당 속성값을 활용하여 작업을 수행하거나 조건을 확인하는데 활용 한다.-->
			
			
	</div> <!-- mainContent -->

<%@include file="../include/footer.jsp" %>

<script>
$(function(){
	let formObj = $("#mform");
	
	$("button").on("click", function(e){
		e.preventDefault(); //이벤트가 일어난 버튼의 기본 동작을 제거한다.
		
		let operation = $(this).data("oper");
		//data(data-의 뒷이름) 메서드는 속성값이 html5에서 새롭게 추가된 data-속성값을 참조하여 반환 한다.

		//게시글 삭제 이벤트 처리
		if(operation == "remove"){
			
			if(confirm("정말 삭제 할래요? 삭제시 복구는 불가해요~")){
				alert("삭제 성공!");
			} else {
				alert("삭제 취소!");
				return false;
			}
			
			formObj.attr("action", "remove");
		}
		
		//리스트로 이동할 이벤트
		else if(operation == "list"){
			formObj.attr("action", "list").attr("method", "get");
		}
		
		//게시글 수정 이벤트 처리
		else if(operation == "modify"){
			console.log("submit clicked!");
		}
		formObj.submit();
	});
});
</script>
</body>
</html>