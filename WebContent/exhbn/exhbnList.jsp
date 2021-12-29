<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function confirmDelete(e_id){
 location.href="./artDeleteAction.in?e_id="+e_id+";
}
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<div class="row" align="center">
<c:if test="${not empty	exhList }">
   <c:forEach items="${exhList}"  var="exh">  
	<div class="col-md-4">
		<img style="width: 300px;" src="/upload/${exh.image}" >
        <h3>${exh.title}</h3>
        <p>${exh.e_id}</p>
        <p>${exh.description}</p>
        <p>${exh.category}</p>
        <p>${exh.price}</p>
        <p>${exh.location}</p>
        <p>${exh.period}</p>
        <p>${exh.time}</p>
      </div>   
       <div class="form-group row">
        <div class="col-sm-offset-2 col-sm-10">
            <c:set var="userId" value="${member.id}" />
            <c:if test="${sessionId==memberId}"><!-- 관리자와 로그인 아이디가 같은 경우 버튼 보이기  -->
              <p>
		<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">삭제
        </button>
              <a href="${pageContext.request.contextPath}/updateExhbnForm.do?e_id=${exh.e_id}" class="btn btn-primary">수정</a>
            </c:if>
        </div>
    </div> 
    </c:forEach>
</c:if>  
</div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">미술품 삭제</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
         ${exh.e_id}번 미술품을 삭제하겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">아니오</button>
        <button type="button" class="btn btn-primary" onclick="confirmDelete('${exh.e_id}')">예</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>