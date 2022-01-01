<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
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
<title>비밀번호 찾기</title>
<script>
	if('${requestScope.msg}' != '') {
		var message = '${requestScope.msg}';
		alert(message);
	}
</script>
</head>
<body>
<jsp:include page="/menu.jsp"/>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title" style="background-image: url('https://upload.wikimedia.org/wikipedia/commons/thumb/a/a8/Nighthawks_by_Edward_Hopper_1942.jpg/1024px-Nighthawks_by_Edward_Hopper_1942.jpg');">
					<span class="login100-form-title-1">Find Password</span>
				</div>
				<form class="login100-form validate-form" action="${pageContext.request.contextPath}/findPassword.do" method="post">
					<div class="wrap-input100 validate-input m-b-26">
						<span class="label-input100">ID</span>
						<input class="input100" type="text" name="id" placeholder="Enter ID" required>
						<span class="focus-input100"></span>
					</div>
	
					<div class="wrap-input100 validate-input m-b-18">
						<span class="label-input100">Email</span>
						<h6><input type="text" name="email1"> @ <input type="text" name="email2"></h6>
						<span class="focus-input100"></span>
					</div>

					<div class="flex-sb-m w-full p-b-30">
					</div>
					<div class="container-login100-form-btn">
						<button class="login100-form-btn">Find</button>
					</div>
					</form>
				
			</div>
		</div>
	</div>
</body>
</html>
<%-- 예전 코드 --%>
<%-- 
	<jsp:include page="/menu.jsp"/>
	<form action="${pageContext.request.contextPath}/findPassword.do" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" required></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email1"> @ <input type="text" name="email2"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="비밀번호 찾기"></td>
			</tr>
		</table>
	</form> --%>