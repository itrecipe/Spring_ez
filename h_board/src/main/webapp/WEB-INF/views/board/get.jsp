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
			<div class="form-group">
				<label for="regdate">등록일 : </label>
				<input type="text" class="form-control" id="regdate" name="regdate" readonly
					value='<c:out value="${h_board.regdate}"/>' />
			</div>
			<div class="form-group">
				<label for="updatedate">수정일 : </label>
				<input type="text" class="form-control" id="updatedate" name="updatedate" readonly
					value='<c:out value="${h_board.updatedate}"/>' />
			</div>
		</form>
	</div> <!-- mainContent -->

<%@ include file="../include/footer.jsp" %>
</body>
</html>