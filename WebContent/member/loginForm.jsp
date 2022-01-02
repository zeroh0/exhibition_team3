<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="../resources/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/vendor/animsition/css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/vendor/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="../resources/css/util.css">
	<link rel="stylesheet" type="text/css" href="../resources/css/main.css">
</head>
<body>
	<jsp:include page="../menu.jsp"/>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title" style="background-image: url('https://upload.wikimedia.org/wikipedia/commons/thumb/a/a8/Nighthawks_by_Edward_Hopper_1942.jpg/1024px-Nighthawks_by_Edward_Hopper_1942.jpg');">
					<span class="login100-form-title-1">Sign In</span>
				</div>
				<%
        		 String error = request.getParameter("error");
          		  if(error!=null){
          		  out.print("<div class='alert alert-danger'>");
          		  out.print("아이디와 비밀번호를 확인해 주세요");
          		  out.print("</div>");
          		 }
     		 	 %>
				<form class="login100-form validate-form" action="${pageContext.request.contextPath}/login.do" method="post">
					<div class="wrap-input100">
						<span class="label-input100">ID</span>
						<input class="input100" type="text" name="id" placeholder="Enter ID" required autofocus >
						<span class="focus-input100"></span>
					</div>
	
					<div class="wrap-input100">
						<span class="label-input100">Password</span>
						<input class="input100" type="password" name="password" placeholder="Enter password">
						<span class="focus-input100"></span>
					</div>

					<div class="flex-sb-m w-full p-b-30">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
							<label class="label-checkbox100" for="ckb1">Remember me</label>
						</div>
						
						<div>
							<div>
							<a href="findIdForm.jsp" class="txt1">아이디를 잊으셨나요?</a>
							</div>
							<a href="findPasswordForm.jsp" class="txt1">비밀번호를 잊으셨나요?</a>
						</div>
					</div>
					<div class="container-login100-form-btn">
						<button class="login100-form-btn">Login</button>
					</div>
					<p class="text-muted text-center mt-2 ml-2 mb-0">회원이 아니신가요? <a href="joinForm.jsp" class="text-primary ml-1">회원가입</a></p>
					
				</form>
				
			</div>
		</div>
	</div>
</body>
</html>