package com.green;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

//DAO는 쿼리문의 집합장소 -> 데이터를 직접 처리하는 객체
@Repository // 데이터 저장소인 DAO객체야라는 의미
public class MemberDAO {

    private final AppConfig appConfig;
	//원래 DB커넥션이 존재해야하지만, 현재 DB가 존재하지 않으므로
	//HashMap<>을 이용해 DB처럼 사용하고자 한다.
    //HashMap<String, MemberDTO>의 keySet은 아이디로 지정
	private Map<String, MemberDTO> memberDB = new HashMap<>();

    MemberDAO(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
	
	//insertMember 메소드 생성
	public void insertMember(MemberDTO mdto) {
		System.out.println("회원가입을 추가하는 메소드야");	
		//Insert into memberDB(mdto.getId(),mdto);
		//DB가 아직 존재하지 않기때문에 HashMap<>의 추가 메소드 put을 이용한다.
		memberDB.put(mdto.getId(), mdto);
		printMember();
	}
	
	//회원 정보 출력 메소드(이건 로그확인용이라서 service로 옮기기 않아도됨)
	public void printMember() {
		for(String key : memberDB.keySet()) {
			MemberDTO mdto = memberDB.get(key);
			System.out.println("id: "+mdto.getId());
			System.out.println("Pw: "+mdto.getPw());
		}
		
	}
	
	//selectMember 메소드 생성
	public MemberDTO selectMember(MemberDTO mdto) {
		System.out.println("로그인의 정보를 확인하는 메소드야");
		
		//id와 pw를 비교해서 같으면 로그인 성공, 아니면 로그인 실패
		//loginMember = {"kjb1045","111","kjb1045@naver.com"};
		MemberDTO loginMember = memberDB.get(mdto.getId());
		return loginMember;
	}
			
	
}
