<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>회원삭제</title>
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
<script>
    function deleteConfirm() {
        if (confirm('정말 삭제하시겠습니까?') == true) {
            document.delForm.submit();
        } else {
            return;
        }
    }
</script>
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
					<span class="login100-form-title-1">Account Deletion</span>
				</div>
				<%
        		 String error = request.getParameter("error");
          		  if(error!=null){
          		  out.print("<div class='alert alert-danger'>");
          		  out.print("비밀번호를 확인해주세요");
          		  out.print("</div>");
          		 }
     		 	 %>
				<form class="login100-form validate-form" action="${pageContext.request.contextPath}/deleteMember.do" method="post" name="delForm">
					<div class="wrap-input100 validate-input m-b-26">
						<span class="label-input100">Password</span>
						<input class="input100" type="password" name="password" placeholder="Enter Password" required>
						<span class="focus-input100"></span>
					</div>
					<div class="flex-sb-m w-full p-b-30">
					</div>
					<div class="container-login100-form-btn">
						<button class="login100-form-btn" type="button" value="회원탈퇴" onclick="deleteConfirm()">Submit</button>
					</div>
					</form>
				
			</div>
		</div>
	</div>
</body>
</html>
<%-- <body>
	<form action="${pageContext.request.contextPath}/deleteMember.do" method="post" name="delForm">
        <input type="password" name="password" required>
        <input type="button" value="회원탈퇴" onclick="deleteConfirm()">
    </form>
</body>
</html> --%>