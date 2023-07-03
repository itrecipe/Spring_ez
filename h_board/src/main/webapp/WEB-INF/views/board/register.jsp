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
		<form>
			<h1>게시물 등록</h1>
			<div class="form-group">
				<label for="bno">번호 : </label>
				<input type="text" class="form-control" id="bno" name="bno"/>
			</div>
			<div class="form-group">
				<label for="title">제목 : </label>
				<input type="text" class="form-control" id="title" name="title"/>
			</div>
			<div class="form-group">
				<label for="content">내용 : </label>
<textarea class="form-control" id="content" name="content" rows="10">
</textarea>				
			</div>
			<div class="form-group">
				<label for="writer">작성자 : </label>
				<input type="text" class="form-control" id="writer" name="writer"/>
			</div>
			<div class="form-group">
				<label for="phone">폰 번호 : </label>
				<input type="text" class="form-control" id="phone" name="phone"/>
			</div>
			<div class="form-group">
				<label for="regdate">등록일 : </label>
				<input type="text" class="form-control" id="regdate" name="regdate"/>
			</div>
			<div class="form-group">
				<label for="updatedate">수정일 : </label>
				<input type="text" class="form-control" id="updatedate" name="updatedate"/>
			</div>
			<button type="submit" class="btn btn-success">작성</button> &nbsp;&nbsp;
			<button type="reset" class="btn btn-danger">작성</button> &nbsp;&nbsp;
				<a id="listlink" href="list" class="btn btn-primary">목록</a>		
			</form>
	</div> <!-- mainContent -->

<%@include file="../include/footer.jsp" %>
</body>
</html>