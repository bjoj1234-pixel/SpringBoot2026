package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
	
	//서비스(MemberService) 클래스를 DI로 의존객체화 해야한다.
	@Autowired
	MemberService memberservice;
	
	
	
	//회원가입 양식 폼
	@GetMapping("/member/signup")
	public String signup() {
		System.out.println("MemberController signup() 메소드 확인");
		String nextPage="member/signup_form";
		return nextPage;
	}
	
	//회원가입 확인
	@PostMapping("/member/signup_confirm")
	public String signupConfirm(MemberDTO mdto,Model model) { //@RequestParam은 7개나 불러와야되서, 편의를 위해 DTO를 사용함.
		System.out.println("MemberController signupConfirm() 메소드 확인");
		String nextPage = "member/signup_result";
		//회원가입이 제대로 되었는지, 혹은 회원가입이 실패했는지 예외처리
		int result = memberservice.signupConfirm(mdto); //여기다 적어서 create method로 서비스에 메소드 생성가능
		//서비스에서 중복아이디가 있냐 없냐에따라 1,0,-1를 담아서 갖고옴.
		
		// 회원가입이 성공하였을 경우 => 회원 목록인 새로운 주소로 이동(리다이렉트)
		if(result == memberservice.user_id_success) {
			return "redirect:/member/list";
		}else {
			//회원가입이 실패한 경우
			model.addAttribute("result", result);
			return nextPage;
		}		
	}
	
	//회원 전체 목록화면 호출
	@GetMapping("/member/list")
	public String memberList(Model model) {
		// MemberService의 allListMember()
		List<MemberDTO> memberlist = memberservice.allListMember();
		model.addAttribute("list", memberlist);
		
		String nextPage = "member/memberList";
		return nextPage;
	}
	
}
