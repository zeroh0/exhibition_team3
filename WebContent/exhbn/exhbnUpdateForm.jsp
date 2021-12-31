<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script type="text/javascript" src="./resources/js/validation.js"></script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../menu.jsp"/>
<div class="jumbotron">
  <div class="container">
     <h1 class="display-3">미술품 등록</h1>
  </div>
</div>
<div class="container">
  <form name="newProduct" action="./updateExhbnAction.do" class="form-horizontal" 
        method="post" enctype="multipart/form-data" 
      >
    <div class="form-group row">
       <label class="col-sm-2">전시번호</label>
       <div class="col-sm-3">
         <input type="text" name="e_id" id="productId" value="${dto.e_id }" class="form-control">
       </div>
    </div>
   <div class="form-group row">
       <label class="col-sm-2">전시 제목</label>
       <div class="col-sm-3">
       <%--페이지 출력시 name태그에 자동 으로 커서이동 처리 autofocus() --%>
         <input type="text" name="title" value="${dto.title }"class="form-control"  autofocus required>
       </div>
    </div>
    
  <div class="form-group row">
       <label class="col-sm-2">전시 항목</label>
       <div class="col-sm-3">
         <input type="text" name="category" id="unitPrice" value="${dto.category }" class="form-control" required>
       </div>
   </div>
    <div class="form-group row">
       <label class="col-sm-2">전시 설명</label>
       <div class="col-sm-5">
         <textarea rows="2" cols="50" name="description" class="form-control" required>${dto.description }</textarea>
       </div>
   </div>
   <div class="form-group row">
       <label class="col-sm-2">전시 요금</label>
       <div class="col-sm-3">
         <input type="text" name="price" value="${dto.price }" class="form-control" required>
       </div>
   </div>
  <div class="form-group row">
       <label class="col-sm-2">전시 장소</label>
       <div class="col-sm-3">
         <input type="text" name="location" value="${dto.location }" class="form-control" required>
       </div>
   </div>
   
   <div class="form-group row">
       <label class="col-sm-2">전시 기간</label>
       <div class="col-sm-3">
         <input type="text" name="period" value="${dto.period }" id="unitsInStock" class="form-control" required>
       </div>
   </div>
   <div class="form-group row">
       <label class="col-sm-2">전시 시간</label>
       <div class="col-sm-5">
       <input type="text" name="time" value="${dto.time }" id="unitsInStock" class="form-control" required>
       </div>
   </div>
   <div class="form-group row">
      <label class="col-sm-2">이미지</label>
       <div class="col-sm-5">
         <img style="width: 400px;" id="preview-image" >
         <input type="file" name="image" value="${dto.image }" class="form-control" id="input-image">
       </div>
   </div>
   <div class="form-group row">
       <div class="col-sm-offset-2 col-sm-10">
         <input type="submit" value="미술품 수정" class="btn btn-primary">
       </div>
   </div>
   
  </form>
</div>
<script>
function readImage(input) {
    // 인풋 태그에 파일이 있는 경우
    if(input.files && input.files[0]) {
        // 이미지 파일인지 검사 (생략)
        // FileReader 인스턴스 생성
        const reader = new FileReader()
        // 이미지가 로드가 된 경우
        reader.onload = e => {
            const previewImage = document.getElementById("preview-image")
            previewImage.src = e.target.result
        }
        // reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0])
    }
}
// input file에 change 이벤트 부여
const inputImage = document.getElementById("input-image")
inputImage.addEventListener("change", e => {readImage(e.target)})
</script>
<jsp:include page="../footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="../resources/js/scripts.js"></script>
</body>
</html>