<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page session="false" import="java.util.*" %>	
<!-- 해당 페이지는 session을 사용안함, 기본은 session="true"이며 없으면 만들고 있으면 그대로 사용한다. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error_page</title>
</head>
<body>

	<h4>
		<%-- model객체에 설정된 속성명 exception --%>
		<c:out value="${exception.getMessage()}"></c:out>
	</h4>
	<ul>
		<c:forEach items="${exception.getStackTrace() }" var="stack">
			<li><c:out value="${stack}"></c:out></li>
		</c:forEach>
	</ul>

</body>
</html>