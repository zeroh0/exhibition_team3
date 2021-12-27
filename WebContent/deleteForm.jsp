<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원삭제</title>
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
	<form action="${pageContext.request.contextPath}/deleteMember.do" method="post" name="delForm">
        <input type="password" name="password" required>
        <input type="button" value="회원탈퇴" onclick="deleteConfirm()">
    </form>
</body>
</html>