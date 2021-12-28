<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>title</title>
    <script>
	if('${requestScope.msg}' != '') {
		var message = '${requestScope.msg}';
		alert(message);
	}
	</script>
  </head>
  <body>
  <p>
    <c:set var="member" value="${sessionScope.member}"/>
    <c:choose>
      <c:when test="${member == null}">
        <a href="${pageContext.request.contextPath}/member/loginForm.jsp">로그인</a>
        <a href="${pageContext.request.contextPath}/member/joinForm.jsp">회원가입</a>
        <a href="${pageContext.request.contextPath}/member/findIdForm.jsp">아이디 찾기</a>
        <a href="${pageContext.request.contextPath}/member/findPasswordForm.jsp">비밀번호 찾기</a>
      </c:when>
      <c:when test="${member != null}">
        <span>${member.name} 환영합니다!</span>
        <a href="${pageContext.request.contextPath}/member/updateForm.jsp">회원정보수정</a>
        <a href="${pageContext.request.contextPath}/member/deleteForm.jsp">회원탈퇴</a>
        <a href="${pageContext.request.contextPath}/logout.do">로그아웃</a>
      </c:when>
    </c:choose>
    <a href="${pageContext.request.contextPath}/BbsListAction.do?pageNum=1">게시판</a>
  </body>
</html>
