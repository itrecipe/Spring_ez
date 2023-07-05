<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
    
<!DOCTYPE html>
<html>
  <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Grayscale - Start Bootstrap Theme</title>
        <link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/resources/css/styles.css" rel="stylesheet" />
    </head>
    
<body id="page-top">

<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
         <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="../home/index">h_board!</a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars"></i>
                </button>
          <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ms-auto">
                        <li class="nav-item"><a class="nav-link" href="../board/list">board</a></li>
                        <!--  
                        <li class="nav-item"><a class="nav-link" href="#projects">Projects</a></li>
                        --->
                        <li class="nav-item"><a class="nav-link" href="#signup">Contact</a></li>
               </ul>
         </div>
       </div>
</nav>

<!-- Masthead -->
        <header class="masthead">
            <div class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
                <div class="d-flex justify-content-center">
                    <div class="text-center">
                        <h1 class="mx-auto my-0 text-uppercase">H_BOARD PROJECT!</h1>
                        <h2 class="text-white-50 mx-auto mt-2 mb-5">게시판 만들기 프로젝트!!</h2>
                        <a class="btn btn-primary" href="#about">Get Started</a>
                    </div>
                </div>
            </div>
        </header>


<!-- Bootstrap core JS-->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        
<!-- Core theme JS-->
     <script src="/resources/js/scripts.js"></script>
     <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

<!-- JQuery import 
Uncaught ReferenceError: $ is nUncaught ReferenceError: $ is not definedot defined
게시글 등록 버튼 처리를 할때 제이쿼리를 로드할 수 없어서 위 에러가 났었다.
아래 코드를 추가해서 import를 시켜준 후 해결 되었다.
-->
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</body>
</html>