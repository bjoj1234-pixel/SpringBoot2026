function bdWrite(){
	console.log("글작성 js");
	
	let form = document.write_confirm;
	
	if(form.writer.value === ""){
		alert("작성자를 입력해주세요.");
		form.writer.focus();
	}else if(form.title.value === ""){
		alert("글제목을 입력해주세요.");
		form.title.focus();
	}else if(form.content.value === ""){
		alert("글내용을 입력해주세요.");
		form.content.focus();
	}else {
		form.submit();
	}
	
}