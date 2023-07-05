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

<%--지시자 incude는 소스 포함하여 컴파일시에 포함 시킴,액션태그 include는 실행 시점에 include --%>
<%@include file="../include/header.jsp"%>
			
			<div class="container mt-4 mb-4" id="mainContent">
					<div class="table-responsive-md">
						<table id="boardTable" class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>폰번호</th>
									<th>작성일</th>
									<th>수정일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="h_board">
								<!-- c:forEach문은 jsp의 코어 태그 라이브러리 반복문을 구현할때 사용 -->
									<tr>
										<td class="bno"><c:out value="${h_board.bno}" /></td>
										<td>
										
										<!-- Jquery로 페이지 이벤트 처리 전 --> 
										<a class="move" href='get?bno=<c:out value="${h_board.bno}"/>'>
											<c:out value="${h_board.title}" />
										</a>
										
										<!-- jquery로 페이지 이벤트 처리 후 
										<a class='move' href='<c:out value="${h_board.bno}"/>'>
												<c:out value="${h_board.title}" />
										</a>
										-->
										
										</td>
										<td><c:out value="${h_board.writer}" /></td>
										<td><c:out value="${h_board.phone}" /></td>

										<td><fmt:formatDate pattern="yyyy-MM-dd"
												value="${h_board.regdate}" /></td>
										<td><fmt:formatDate pattern="yyyy-MM-dd"
												value="${h_board.updatedate}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 게시물 등록 버튼 -->
						<div>
							<button type="button" class="btn btn-info float-right mb-3" id="regBtn">게시물 등록</button>
						</div>
					</div> <!-- table-responsive-md -->
			</div> <!-- mainContent -->
<%@include file="../include/footer.jsp"%>

<script>
$(document).ready(function(){
	let result = '<c:out value="${result}"></c:out>';
	//result redirect: 로 URL 이동시킬시 RedirectAttributes에 저장해준 속성값을 의미 한다.
	
	console.log("result : " + result);
	
	history.replaceState({}, null, null); //현재 히스토리를 전부 비운다.
	
	$("#regBtn").on("click", function(){
		self.location = "register";
	});
	
	/*
	let actionForm = $("#actionForm");

	$(".page-item a").on("click", function(e){
		e.preventDefault(); //a 태그의 본래 기능을 취소 시킨다.
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		 
		   find(selector) 메서드는 자식 엘리먼트에서 selector에 해당하는 엘리먼트를 선택한다.
		   pageNum이 name인 input의 value에 클릭한 a의 href값(페이지 번호)을 넣어준다.
		   this는 이벤트가 일어난 객체이므로 <a>가 된다.
		
		actionForm.submit(); //submit(), reset()은 form의 이벤트
	});
	
	$(".move").on("click", function(e){
		e.preventDefault();
		actionForm.append("<input type='hidden' name='bno' value='"
				+ $(this).attr("href") + "'>");
		//메서드에 의해 구해지는 값이라서 +로 연결을 해줬다. 뒤에는 변수 선언시가 아닌 표시를 의미하므로 보이는 대로 표시 해준다.
		actionForm.attr("action", "get");
		actionForm.submit();
	});
*/

/* list:566 Uncaught SyntaxError: Unexpected end of input 에러는 코드 또는 스크립트의 문법 오류로 인해 발생합니다. 
해당 오류 메시지는 입력의 예상하지 못한 종료를 의미합니다. 주로 다음과 같은 상황에서 발생할 수 있습니다: */
});

</script>
</body>
</html>