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
								<!-- jsp의 코어 태그 라이브러리 반복문을 구현할때 사용, -->	
									<tr>
										<td class="bno"><c:out value="${h_board.bno}" /></td>
										<td>
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
					</div> <!-- table-responsive-md -->
			</div> <!-- mainContent -->
<%@include file="../include/footer.jsp"%>

</body>
</html>