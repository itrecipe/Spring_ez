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
								<c:forEach items="${list}" var="h_board">
									<tr>
										<td class="bno"><c:out value="${h_board.bno}" /></td>
										<td>
										<a class="move" href='get?bno=<c:out value="${board.bno}"/>'>
											<c:out value="${board.title}" />
										</a>
										<a class='move' href='<c:out value="${h_board.bno}"/>'>
												<c:out value="${h_board.title}" />
										</a>
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
					</div>

<%@include file="../include/footer.jsp"%>

</body>
</html>