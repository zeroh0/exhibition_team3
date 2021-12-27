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
        <a href="${pageContext.request.contextPath}/loginForm.jsp">로그인</a>
        <a href="${pageContext.request.contextPath}/joinForm.jsp">회원가입</a>
      </c:when>
      <c:when test="${member != null}">
        <span>${member.name} 환영합니다!</span>
        <a href="${pageContext.request.contextPath}/updateForm.jsp">회원정보수정</a>
        <a href="${pageContext.request.contextPath}/deleteForm.jsp">회원탈퇴</a>
        <a href="${pageContext.request.contextPath}/logout.do">로그아웃</a>
      </c:when>
    </c:choose>
  </body>
</html>
