package com.green.board;

public class BoardDTO {
	//반드시 MySQL에 작성한 테이블 필드명 순서,
	//데이터 타입이랑 같은 형식으로 작성한다.
	private int num; // 글번호 
	private String writer; // 글쓴이 
	private String subject; // 글제목
	private String writerPw; // 글 비밀번호 
	private String reg_date; // 작성일 
	private int readcount; // 조회수 
	private String content; // 글내용  
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriterPw() {
		return writerPw;
	}
	public void setWriterPw(String writerPw) {
		this.writerPw = writerPw;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	 
}
