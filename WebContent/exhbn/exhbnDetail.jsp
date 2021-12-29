<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>${dto.title}</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="./resources/assets/favicon1.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="./resources/css/styles1.css" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="../menu.jsp"/>
        <!-- Product section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="/upload/${dto.image}"  alt="..." /></div>
                    <div class="col-md-6">
                        <div class="small mb-1">${dto.category }</div>
                        <h1 class="display-5 fw-bolder">${dto.title }</h1>
                        <div class="fs-5 mb-5">
                            <span class="text-decoration-line-through"></span>
                        </div>
                        <p class="lead">${dto.description}</p>
                        <div class="fs-5 mb-5">
                            <span class="text-decoration-line-through">${dto.price }</span><br>
                            <span class="text-decoration-line-through">${dto.period }</span><br>
                            <span class="text-decoration-line-through">${dto.location }</span>
                        </div>
                        <div class="d-flex">
                            <button class="btn btn-outline-dark flex-shrink-0" type="button">
                                <a href="https://goo.gl/maps/NtcRSoYBkAmrQTzK8" target="_blank"><i class="bi bi-map me-1"></i>
                                지도 보기
                                </a>
                            </button>
                           
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- Related items section-->
        <section class="py-5 bg-light">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">Related products</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
        <c:if test="${not empty	dtoList }">
        <c:forEach items="${dtoList}"  var="exh" end="3">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="/upload/${exh.image}" width="450" height="300" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${exh.title }</h5>
                                    <!-- Product price-->
                                    ${exh.period }
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="./ExhDetailAction.do?e_id=${exh.e_id}">View exhibition</a></div>
                            </div>
                        </div>
                    </div>
        </c:forEach>
        </c:if>
                </div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="./resources/js/scripts1.js"></script>
    </body>
</html>