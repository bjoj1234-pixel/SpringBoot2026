/* 회원가입 유효성 검사 규칙 */
function signupForm(){
	console.log("회원가입폼");
	
	/* DOM으로 form을 연결 */
	let form = document.signup_form;//form태그 name과 같게
	
	if(form.id.value === ""){
		alert("새로운 id 입력");
		/* 커서를 id로 지정 */
		form.id.focus();
	}else if(form.pw.value === ""){
		alert("새로운 pw 입력");
		form.pw.focus();
	}else if(form.mail.value === ""){
		alert("새로운 mail 입력");
		form.mail.focus();
	}else if(form.phone.value === ""){
		alert("새로운 phone 입력");
		form.phone.focus();
	}else{
		/* 전송해라 */
		form.submit();
	}
}

/*로그인 예외처리*/
function loginForm(){
	console.log("로그인폼");
	
	/* DOM으로 form을 연결 */
	let form = document.login_form;//form태그 name과 같게
	
	if(form.id.value === ""){
		alert("새로운 id 입력");
		/* 커서를 id로 지정 */
		form.id.focus();
	}else if(form.pw.value === ""){
		alert("새로운 pw 입력");
		form.pw.focus();
	}else{
		/* 전송해라 */
		form.submit();
	}
}

//회원이 로그인 된 상태면 글쓰기 가능, 아니면 '로그인 후 사용가능' 메시지 출력
	let write = document.getElementById("writeBtn");
	write.addEventListener("click",function(){
		const isLogin = this.dataset.login;
		
		if(isLogin === "true"){
			/* 로그인이 된 상태 => 글쓰기 => /board/write */
			location.href = "/board/write";	
		}else{
			/* 로그인이 안된 상태 => 글쓰기 안됨 => alert 창띄우기 */
			/* 로그인폼으로 이동한다. */
			alert("로그인 후 사용가능");
			location.href = "/member/login";
		}
	});


