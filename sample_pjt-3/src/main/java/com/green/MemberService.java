package com.green;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//memberservice클래스는 비즈니스 로직을 작성하는 클래스이다.
//@Service의 의미이다.
@Service
public class MemberService {
	
	//MemberDAO 클래스를 MemberService 클래스에서 사용하는 방법
	//DI를 이용해 외부로부터 객체를 주입하여 사용한다.
	//DI를 의미하는 @Autowired 사용한다.
	
	@Autowired
	MemberDAO memberdao;

	public void signUpConfirm(MemberDTO mdto) {
		System.out.println("회원가입 출력화면이야");	
		memberdao.insertMember(mdto);
		
	}

	public void signInConfirm(MemberDTO mdto) {
		System.out.println("로그인 정보 출력화면이야");
		MemberDTO loginMember = memberdao.selectMember(mdto);

		//if문을 이용해서 id,pw가 DB존재하면 로그인성공,
		//아니면 로그인 실패로 출력하기
		//java.lang.NullPointerException: Cannot invoke "com.green.MemberDTO.getId()" because "loginMember" is null
		//=> 로그인한 id,pw 입력하지 않은채로 로그인을 실행했을때 발생하는 오류
		//그러므로 반드시 null을 예외로 처리한다.
		//위에 getid를 loginmember에 담았기때문에 아래 if문에 안넣어도됨
		//일반적으로 .equals()안에 입력된값을 넣음.
		if(loginMember != null && mdto.getPw().equals(loginMember.getPw())){
			System.out.println("id: "+loginMember.getId());
			System.out.println("Pw: "+loginMember.getPw());
			System.out.println("로그인 성공");
		}else {
			System.out.println("로그인 실패");
		}		
	}
	

}
// 1. UI => input으로 자료입력
// 2. DTO => 가방에 넣는다.
// 3. Controller => 출력하고싶은 페이지 Mapping() URL로
//				 => DTO의 자료를 .getter()로 꺼내 사용한다.
//				 => ModelAndView 에 넣고
//				 => 출력페이지에 담아서 보낸다.
// 4. DAO => 쿼리문들의 집합으로 DB와 연동하여 원하는 조건에 만족하는 메소드들에
//           접근할 수 있다.
//           Controller, Service 클래스도 모두
//           DI인 @Autowired를 이용하여 메소드에 접근할 수 있다.
// 5. Service => 비즈니스 로직을 담당하는 클래스로 DAO나, 또는 DTO를
//               DI로 외부로부터 객체를 삽입받아 메소드를 접근할 수 있다.













