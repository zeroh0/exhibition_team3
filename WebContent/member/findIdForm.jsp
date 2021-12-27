<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script>
	if('${requestScope.msg}' != '') {
		var message = '${requestScope.msg}';
		alert(message);
	}
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/findId.do" method="post">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email1"> @ <input type="text" name="email2"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="아이디 찾기"></td>
			</tr>
		</table>
	</form>
</body>
</html>