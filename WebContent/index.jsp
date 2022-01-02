<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html >
    <head>
        <title>EX page</title>
        <meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="./resources/vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="./resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="./resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="./resources/vendor/animate/animate.css">
		<link rel="stylesheet" type="text/css" href="./resources/vendor/css-hamburgers/hamburgers.min.css">
		<link rel="stylesheet" type="text/css" href="./resources/vendor/animsition/css/animsition.min.css">
		<link rel="stylesheet" type="text/css" href="./resources/vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="./resources/vendor/daterangepicker/daterangepicker.css">
		<link rel="stylesheet" type="text/css" href="./resources/css/util.css">
		<link rel="stylesheet" type="text/css" href="./resources/css/main.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
    	<%@ include file="menu.jsp"%>
      
        <header class="py-5 bg-image-full" style="background-image: url('https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Vincent_Willem_van_Gogh_058.jpg/1920px-Vincent_Willem_van_Gogh_058.jpg')">
            <div class="text-center my-5">
                <h1 class="text-white fs-3 fw-bolder">Art Gallery</h1>
                <p class="text-black-50 mb-0 fw-bolder">by Group3</p>
            </div>
        </header>

        <section class="py-5">
            <div class="container my-5">
                <div class="row justify-content-center">
                    <div class="col-lg-6">
                        <h2 class="text-center">New Thrilling Masterpiece</h2>
                        <p class="lead text-center">새로운,</p>
                        <p class="mb-0 text-center">다채로운</p>
                    </div>
                </div>
            </div>
        </section>

        <div class="py-5 bg-image-full" style="background-image: url('http://art-vangogh.com/image/Arles%20(1888-1889)/106%20Starry%20Night%20Over%20the%20Rhone.jpg')">
            <!-- Put anything you want here! The spacer below with inline CSS is just for demo purposes!-->
            <div style="height: 20rem"></div>
        </div>
      
        <section class="py-5">
            <div class="container my-5">
                <div class="row justify-content-center">
                    <div class="col-lg-6">
                        <h2>Engaging New Art works</h2>
                        <p class="lead">The new products of our gallery</p>
                        <p class="mb-0">very new and very thrilling experience</p>
                    </div>
                </div>
            </div>
        </section>
        
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
        </footer>

        <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="./resources/js/scripts.js"></script> -->
    </body>
</html>
