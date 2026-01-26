package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
			//중복된 아이디가 없으면 DB에 정보가 추가되어야 한다.
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
	


}
