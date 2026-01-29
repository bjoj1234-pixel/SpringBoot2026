package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// controller -> service : DAO 메소드 찾아
// DAO야 메소드 있어 -> DB에서 찾아옴
// DB -> id,pw값들고 -> DAO로 보냄 -> service의 메소드로 보냄
// -> controller에게 찾아서 보냄

@Service
public class MemberService {

	//id중복체크, 성공, 실패 상수변수 정의
	//회원가입의 중복을 확인하는 상수
	public final static int user_id_already_exit = 0;//아이디가 이미 존재하는지 유무
	//회원가입의 성공여부를 확인하는 상수
	public final static int user_id_success = 1;
	//회원가입의 실패를 확인하는 상수
	public final static int user_id_fail = -1;
	
	//서비스에서는 DAO(MemberDAO)를 DI로 정의한다.
	@Autowired
	MemberDAO memberdao;
	
	//PasswordEncoder 객체도 DI(의존객체)를 정의한다.
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//회원 전체목록 출력하는 메소드
	public List<MemberDTO> allListMember(){
		return memberdao.allSelectMember();
	}
	
	
	//회원가입이 제대로 되었는지, 혹은 회원가입이 실패했는지 예외처리
	public int signupConfirm(MemberDTO mdto) {
		System.out.println("MemberService signupConfirm() 메소드 확인");
		
		//회원가입 중복체크 (true면 있다는 뜻이므로 중복, false면 중복X)
		boolean isMember = memberdao.isMember(mdto.getId());
		//이거역시 여기서 먼저 메소드 작성후, create method로 DAO에 메소드 생성
		
		//회원가입 중복체크 통과했다면
		if(isMember == false) {
			//문자인pw를 암호화된 비밀번호로 변화해주는 코드
			//passwordEncoder.encode(null) 안에 암호화 하고싶은 필드명 입력
			String encodepw = passwordEncoder.encode(mdto.getPw());
			//encode(암호화): 인간언어 -> 기계어
			//decode(복호화): 기계어 -> 인간언어
			
			//암호화된 encodepw를 mdto.getPw() => 수정
			mdto.setPw(encodepw);
			
			//중복된 아이디가 없으면 DB에 정보가 추가되어야 한다.
			//DB에 회원정보가 추가되는 부분 => 암호화가 이루어져야 한다.
			int result = memberdao.insertMember(mdto);
			
			if(result > 0) {
				return user_id_success; //가입성공시 result =1;
			}else {
				return user_id_fail; //가입실패시 result =-1;
			}
		}else {
			//중복된 아이디가 존재할때
			return user_id_already_exit; //중복된 아이디가 있으면 result = 0;
		}
		
	}
	
	//----------------- 2026년 1월 27일 서비스 로직 작성시작 부분 ---------
	public MemberDTO oneSelect(String id) {
		System.out.println("MemberService oneSelect() 메소드 확인");
		return memberdao.oneSelectMember(id);
	}
	
	// 한사람의 패스워드만 출력하는 메소드
	public String onePass(String id) { 
		//void가 아닌이상 데이터타입이 존재하면 반드시 return 반환값이 필요
		return memberdao.getPass(id);
	}
	
	//개인 한사람의 정보를 수정하는 메소드
	//DB의 패스워드와 같은지 비교
	//DB의 패스워드와 내가 입력한 패스워드가 같을때 실행문
	//DB의 패스워드와 내가 입력한 패스워드가 다를때 실행문
	public boolean modifyMember(MemberDTO mdto) {//DAO의 return 데이터타입이 int이면 여기서는 보통 boolean임.
		System.out.println("MemberService modifyMember() 메소드 확인");

		//1. DB조회(패스워드)
		String dbPass = memberdao.getPass(mdto.getId());
		
		//2. if비교
		if(dbPass.equals(mdto.getPw()) && dbPass != null) {
			//내가 입력한 DB의 패스워드가 존재할때
			return memberdao.updateMember(mdto) == 1;
		}else {
			//내가 입력한 DB의 패스워드가 존재하지않을때
			return false;
		}
		
		
		
	}
	
	// 한사람 개인의 정보를 삭제하는 메소드
	public boolean oneDelete(String id) {//DAO의 return 데이터타입이 int이면 여기서는 보통 boolean임.
		System.out.println("MemberService oneDelete() 메소드 확인");
		//현재 deleteMember() DAO의 결과값이 result = 0 또는 1
		// 삭제되면 1, 아니면 0
		// memberdao.deleteMember(id) => 1 == 1 => true
		// memberdao.deleteMember(id) => 0 == 1 => false
		
		return memberdao.deleteMember(id) == 1;
	}

	// 암호화된 DB를 복호화하여 로그인하는 메소드
	public MemberDTO loginConfirm(MemberDTO mdto) {
		System.out.println("MemberService loginConfirm() 메소드 확인");
		
		//1. DB에서 해당정보의 id가져오기(입력한id의 암호화된 password를 가져옴)
		MemberDTO dbMember = memberdao.oneSelectMember(mdto.getId());
		
		//2. DB에서 꺼내온 id의 비밀번호와 입력한 값이 일치하는지 확인
		//암호화된 데이터를 PasswordEncoder.matches(사용자 입력한 문, DB에 저장된 암호문)
		
		if(dbMember != null && dbMember.getPw() != null) {
			
			//사용자 입력 비밀번호(mdto.getPw())를 자동으로 복호화시켜 비교시킴
			if(passwordEncoder.matches(mdto.getPw(),dbMember.getPw())) {
				//로그인 성공한 경우
				return dbMember;
			}
		}
		return null; // 로그인 실패		
	}
	
	


}
