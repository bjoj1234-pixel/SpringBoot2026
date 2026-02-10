package replyBoard.dto;

import java.util.Date;

public class ReplyBoardDTO {
	private int num; //번호
	private String writer; //작성자
	private String email; //이메일
	private String subject; //제목
	private String password; //비밀번호
	private Date reg_date; //작성일
	private int ref; //같은 원글 번호(댓글 묶음 기준)
	private int re_step; //댓글 출력 순서 (위->아래)
	private int re_level; //댓글 들여쓰기 단계(원글/댓글 구분)
	private int readcount; //조회수
	private String content; //글내용
	private String upload1; //이미지1
	private String upload2; //이미지2
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
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
	public String getUpload1() {
		return upload1;
	}
	public void setUpload1(String upload1) {
		this.upload1 = upload1;
	}
	public String getUpload2() {
		return upload2;
	}
	public void setUpload2(String upload2) {
		this.upload2 = upload2;
	}
	
	
}
