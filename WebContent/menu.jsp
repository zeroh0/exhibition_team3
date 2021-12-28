<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="#!">전시-미술품</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="product.jsp">Home</a></li>
                <c:choose>
                <c:when test="${empty member}">
                <li class="nav-item"><a class="nav-link" href="<c:url value="/member/loginForm.jsp"/>">로그인</a></li>
                <li class="nav-item"><a class="nav-link" href="<c:url value="/member/joinForm.jsp"/>">회원가입</a></li>
               </c:when>
               <c:otherwise>
               <li style="padding-top:7px; color:black;">[${member.name}님]</li>
  	       <li class="nav-item"><a class="nav-link" href="<c:url value="/logout.do"/>">로그아웃</a></li>
  	       <li class="nav-item"><a class="nav-link" href="<c:url value="/member/updateForm.jsp"/>">회원 수정</a></li>
               </c:otherwise>
                </c:choose>
                <li class="nav-item"><a class="nav-link" href="<c:url value="/BbsListAction.go?pageNum=1"/>">게시판</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">미술품</a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">All Products</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                        <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                    </ul>
                </li>
            </ul>
            <form class="d-flex">
                <button class="btn btn-outline-dark" type="submit">
                    <i class="bi-cart-fill me-1"></i>
                    Cart
                    <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                </button>
            </form>
        </div>
    </div>
</nav>
</body>
</html>