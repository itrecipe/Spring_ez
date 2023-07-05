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
<%@ include file="../include/header.jsp" %>
	
	<div class="container mt-4 mb-4" id="mainContent">
		<form>
			<div class="form-group">
				<label for="bno">번호 : </label>
				<input type="text" class="form-control" id="bno" name="bno" readonly
					value='<c:out value="${h_board.bno}"/>' />
			</div>
			<div class="form-group">
				<label for="title">제목 : </label>
				<input type="text" class="form-control" id="title" name="title" readonly
					value='<c:out value="${h_board.title}"/>' />
			</div>
			<div class="form-group">
				<label for="content">내용 : </label>
<textarea class="form-control" id="content" name="content" rows="10" readonly>
<c:out value="${h_board.content}"/>
</textarea>				
			</div>
			<div class="form-group">
				<label for="writer">작성자 : </label>
				<input type="text" class="form-control" id="writer" name="writer" readonly
					value='<c:out value="${h_board.writer}"/>' />
			</div>
			<div class="form-group">
				<label for="phone">폰 번호 : </label>
				<input type="text" class="form-control" id="phone" name="phone" readonly
					value='<c:out value="${h_board.phone}"/>' />
			</div>
		</form>

			<button type="button" data-oper='modify' class='btn btn-success'>수정</button>&nbsp;&nbsp;
			<button data-oper='list' class='btn btn-info'>목록</button>

			<!-- 버튼 클릭 이벤트를 처리하기 위해 form을 hidden 속성을 사용하여 추가 해준다. -->
			<form id='operForm' action="modify" method="get">
				<input type="hidden" id="bno" name="bno"
					 value='<c:out value="${h_board.bno}"/>'>
			</form>

	</div> <!-- mainContent -->

<%@ include file="../include/footer.jsp" %>

<!-- 게시판 상세보기(get.jsp) 페이지 관련 이벤트 처리 -->
<script>
//페이지 로드시 실행 코드를 정의해주는 함수, 즉 페이지가 로드되면 아래 코드 블록이 실행됨.
$(document).ready(function(){
	//operForm 변수를 선언하여 $("#operForm");을 선택자로 선택하여 id가 operForm인 요소를 가져온다.
	let operForm = $("#operForm");
	
	$("button[data-oper='modify']").on("click", function(){
		
		if(!confirm("수정 할래요?")){
			alert("수정을 취소 합니다.");
			return false;
		} else {
			alert("modify 페이지로 이동 합니다.");
		}
		
		operForm.attr("action", "modify").submit();
	});
	
	$("button[data-oper='list']").on("click", function(){
		//id가 bno인 DOM 객체를 찾아서 제거한다.
		operForm.find("#bno").remove();
		operForm.attr("action", "list");
		operForm.submit();
	});
});
</script>

</body>
</html>