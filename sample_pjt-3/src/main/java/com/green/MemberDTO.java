package com.green;

// DTO(Data Transfer Object)는 데이터 전송객체
// DTO라는 가방에 입력한 자료를 담아서 이동한다.
public class MemberDTO {
//멤버변수는 접근제어자 private 이용한다.
//단, private은 자기자신 클래스에서만 접근가능
//반드시 getter, setter할것
	private String id;
	private String pw;
	private String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
