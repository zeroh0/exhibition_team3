<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>회원가입</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
		function Postcode() {
			new daum.Postcode({
				oncomplete: function(data) {
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

					// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
					// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					var roadAddr = data.roadAddress; // 도로명 주소 변수
					var extraRoadAddr = ''; // 참고 항목 변수

					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
						extraRoadAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if(data.buildingName !== '' && data.apartment === 'Y'){
						extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if(extraRoadAddr !== ''){
						extraRoadAddr = ' (' + extraRoadAddr + ')';
					}

					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					document.getElementById('zipcode').value = data.zonecode;
					document.getElementById("roadAddress").value = roadAddr;
					document.getElementById("jibunAddress").value = data.jibunAddress;

					// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
					if(roadAddr !== ''){
						document.getElementById("extraAddress").value = extraRoadAddr;
					} else {
						document.getElementById("extraAddress").value = '';
					}

					var guideTextBox = document.getElementById("guide");
					// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
					if(data.autoRoadAddress) {
						var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
						guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
						guideTextBox.style.display = 'block';

					} else if(data.autoJibunAddress) {
						var expJibunAddr = data.autoJibunAddress;
						guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
						guideTextBox.style.display = 'block';
					} else {
						guideTextBox.innerHTML = '';
						guideTextBox.style.display = 'none';
					}
				}
			}).open();
		}
	</script>
	<script>
		let isConfirm = false;

		function confirm(){
			var cert1 = document.getElementById("cert").value;
			var cert2 = document.getElementById("cert_confirm").value;
			if (cert1.length === 0 || cert2.length === 0){
				alert("인증확인요망");
			} else if (cert1 !== cert2){
				alert("인증확인요망");
			} else {
				alert("인증이 완료되었습니다.");
				isConfirm = true;
			}
		}
	</script>
	<script>
		function sendEmail(){
			var mailId = document.newMember.email1.value+'@'+document.newMember.email2.value;
			var emailPassword = document.getElementById('Emailpassword').value;
			if(emailPassword.length > 0) {
				window.open("certMail.jsp?email="+mailId+"&emailPassword="+emailPassword);
			}
		}
	</script>
	<script>
		function selectDomain(obj){
			document.newMember.email2.value=obj.value;
			if(obj.value === "") document.newMember.email2.focus();
		}
	</script>
	<script>
		function checkForm() {
			if (document.newMember.password.value !== document.newMember.password_chk.value) {
				alert("비밀번호와 비밀번호확인 값이 서로 다릅니다!");
				document.newMember.password.value = "";
				document.newMember.password_chk.value = "";
				document.newMember.password.focus();
				return false;
			}
			//validation 체크
			const regExpId = /^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
			const regExpName = /^[가-힣]*$/;
			const regExpPassword =/^[0-9]*$/;
			const regExpPhone = /^\d{3}-\d{3,4}-\d{4}$/;
			const regExpEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; //akim
			const form = document.newMember;

			var id = form.id.value;
			var name = form.name.value;
			var passwd = form.password.value;
			var phone = form.phone1.value +"-" + form.phone2.value +"-" + form.phone3.value;
			var email = form.email1.value +"@"+form.email2.value;

			if(!regExpId.test(id)){
				alert("아이디는 문자로 시작해주세요");
				form.id.focus();
				form.id.value='';
				return false;
			}
			if(!regExpName.test(name)){
				alert("이름은 한글만 입력해주세요!");
				form.name.focus();
				form.name.value='';
				return false;
			}
			if(!regExpPassword.test(passwd)){
				alert("비밀번호는 숫자만 입력해주세요");
				form.password.focus();
				form.password.value='';
				form.password_chk.value='';
				return false;
			}
			if(!regExpPhone.test(phone)){
				alert("연락처 입력을 확인 해주세요");
				form.phone2.focus();
				form.phone2.value='';
				form.phone3.value='';
				return false;
			}
			if(!regExpEmail.test(email)){
				alert("이메일 입력을 확인 해주세요");
				form.email1.focus();
				form.email1.value='';
				form.email2.value='';
				return false;
			}

			return true;
		}
	</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/join.do" method="post" name="newMember" onsubmit="return checkForm()">
	<table>
		<tr>
			<td>아이디</td>
			<td>
				<input type="text" name="id" placeholder="아이디" required>
				<button>아이디 중복 체크</button>
			</td>
		</tr>

		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password" placeholder="비밀번호" required></td>
		</tr>

		<tr>
			<td>비밀번호 확인</td>
			<td><input type="password" name="password_chk" placeholder="비밀번호 확인" required></td>
		</tr>

		<tr>
			<td>이름</td>
			<td><input type="text" name="name" placeholder="이름" required></td>
		</tr>

		<tr>
			<td>이메일</td>
			<td>
				<input type="text" name="email1" required> 
				@ 
				<input type="text" name="email2" required>
				<input type="button" value="인증번호 전송" data-toggle="modal" data-target="#exampleModal">
			</td>
		</tr>

		<tr>
			<td>이메일 인증</td>
			<td>
				<input name="cert" type="text" id="cert" value="" readonly>
				<input name="cert_confirm" id="cert_confirm" type="password" value="">
				<input type="button" value="확인" onclick="confirm()">
			</td>
		</tr>

		<tr>
			<td>전화번호</td>
			<td>
				<input type="text" name="phone1"> - 
				<input type="text" name="phone2"> - 
				<input type="text" name="phone3">
			</td>
		</tr>

		<tr>
			<td>우편번호</td>
			<td>
				<input name="zipcode" id="zipcode" type="text" placeholder="우편번호" required>
				<input type="button" onclick="Postcode()" value="우편번호 찾기">
				<span id="guide" style="color:#999;display:none"></span>
			</td>
		</tr>

		<tr>
			<td>도로명주소</td>
			<td>
				<input name="roadAddress" id="roadAddress"  type="text" placeholder="도로명주소" required readonly>
			</td>
		</tr>

		<tr>
			<td>지번주소</td>
			<td>
				<input name="jibunAddress" id="jibunAddress"  type="text" placeholder="지번주소" required readonly>
			</td>
		</tr>

		<tr>
			<td>상세주소</td>
			<td>
				<input name="detailAddress" id="detailAddress" type="text" placeholder="상세주소">
			</td>
		</tr>

		<tr>
			<td>참고항목</td>
			<td>
				<input name="extraAddress" id="extraAddress" type="text" placeholder="참고항목" required readonly>
			</td>
		</tr>

		<tr>
			<td colspan="2">
                <input type="reset" value="취소">
			    <input type="submit" value="등록">
            </td>
		</tr>
	</table>
</form>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">New message</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<div class="mb-3">
						<label for="recipient-name" class="col-form-label">email비밀번호</label>
						<input type="password" class="form-control" id="Emailpassword" name="emailPassword">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" onclick="sendEmail()">Send message</button>
			</div>
		</div>
	</div>
</div>

</body>
</html>
