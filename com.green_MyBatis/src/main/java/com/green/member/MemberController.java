package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

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
		//회원가입이 제대로 되었는지, 혹은 회원가입이 실패했는지 예외처리
		int result = memberservice.signupConfirm(mdto); //여기다 적어서 create method로 서비스에 메소드 생성가능
		//서비스에서 중복아이디가 있냐 없냐에따라 1,0,-1를 담아서 갖고옴.
		model.addAttribute("result", result);
				
		String nextPage = "member/signup_result";
		return nextPage;
	}
	
	//회원 전체 목록화면 호출
	@GetMapping("/member/list")
	public String memberList(Model model) {
		System.out.println("MemberController memberList() 메소드 확인");

		// MemberService의 allListMember()
		List<MemberDTO> memberlist = memberservice.allListMember();
		model.addAttribute("list", memberlist);
		
		String nextPage = "member/memberList";
		return nextPage;
	}
	
	//----------------- 2026년 1월 27일 Controller 로직 부분 ---------
	// 한 개인의 정보를 상세보기하는 핸들러
	@GetMapping("/member/memberInfo")
	public String memberInfo(Model model,MemberDTO mdto) { // @RequestParam("id") String id =>이렇게 넣어도됨.
		System.out.println("MemberController memberInfo() 메소드 확인" + mdto.getId());
		MemberDTO onememberInfo = memberservice.oneSelect(mdto.getId());
		
		model.addAttribute("onelist", onememberInfo);
		String nextPage = "member/memberInfo";
		return nextPage;
	}
	
	// 개인 정보를 수정하는 화면으로 이동하는 핸들러
	@GetMapping("/member/modify")
	public String modifyForm(MemberDTO mdto,Model model) {
		System.out.println("MemberController modifyForm() 메소드 확인" + mdto.getId());
		
		//개인수정 화면 그릴때 필요한 정보 : 한사람의 데이터
		//oneSelect(String id) : memberservice의 메소드
		MemberDTO oneModify = memberservice.oneSelect(mdto.getId());
		model.addAttribute("member", oneModify);
		String nextPage = "member/member_modify";
		return nextPage;		
	}
	
	//개인정보 수정을 처리하는 핸들러
	//비밀번호가 일치하는지의 비교에 관련된 핸들러
	//redirect 사용해볼것
	//modifyMember() 메소드 이용해볼것 => service
	
	@PostMapping("/member/modify")
	public String modifySubmit(MemberDTO mdto, RedirectAttributes re) {//RedirectAttributes는 한번만 데이터를 넘길수있다.
		System.out.println("MemberController modifySubmit() 메소드 확인" + mdto.getId());
		
		boolean result = memberservice.modifyMember(mdto);
		//지금 현재 result의 결과값 true 또는 false
		if(result) {
			//update 성공
			re.addFlashAttribute("msg", "회원정보가 수정되었습니다.");
			//수정이 완료되면 list의 url => /member/list
			return "redirect:/member/list";
		}else {
			//비밀번호가 틀림
			re.addFlashAttribute("msg", "비밀번호가 틀립니다.");
			//비밀번호가 틀리면 현재화면인 modify에 그대로 존재해야함
			//http://localhost:8090/member/modify?id=abcd1111(아이디)
			//비밀번호가 틀릴경우 위 주소랑 같은 곳으로 돌아가야함
			return "redirect:/member/modify?id="+mdto.getId();
		}		
	}
	
	//개인 한 사람의 정보를 삭제하는 핸들러
	@GetMapping("/member/delete")
	public String deleteMember(
			@RequestParam("id") String id,
			RedirectAttributes re
			) {
		System.out.println("MemberController deleteMember() 메소드 확인");

		boolean result = memberservice.oneDelete(id);
		//result 삭제가 된경우 true, 삭제가 되지않은 경우 false
		
		if(result) {
			//입력된 id가 존재하는, 그래서 삭제된 경우
			re.addFlashAttribute("msg", "회원이 삭제되었습니다.");
			//삭제된 경우 List url => /member/list
			return "redirect:/member/list";
		}else {
			re.addFlashAttribute("msg", "삭제 실패");
			//삭제가 실패된경우 =>			
			//http://localhost:8090/member/memberInfo?id=abcd1111(아이디) 주소에 머물러야함
			return "redirect:/member/memberInfo?id="+id;
		}
		
	}
	
	
	//로그인 양식 폼
	@GetMapping("/member/login")
	public String loginForm() {
		System.out.println("MemberController loginForm() 메소드 확인");
		String nextPage="member/login_form";
		return nextPage;
	}
		
	//로그인을 처리하기 위한 컨트롤러
	@PostMapping("/member/loginPro")
	public String loginPro(MemberDTO mdto,HttpSession session,RedirectAttributes re) {
		System.out.println("MemberController loginPro 메소드 확인");

		MemberDTO logInMember = memberservice.loginConfirm(mdto);
		
		//Model객체는 요청 1번짜리 이다.
		//화면 이동하면 => 바로 사라짐
		//로그인 유지가 안됨
		
		//Session(세션) => 스프링부트의 내장 객체이다.(그냥 꺼내쓰면 됨)
		//세션이란 ? 서버가 사용자 한명을 기억하기위해 사용하는 저장공간
		//=>로그인 유지가 가능(로그아웃 하기전까지 계속 로그인되있음)
		//HttpSession 클래스이름
		
		//Session 기본 3가지 명령어
		//1. 세션에 값 저장하기
		//session.setAttribute("이름",값) => 로그인 성공시 사용
		//2. 세션에 저장된 값 가져오기
		//session.getAttribute("이름") => 로그인 여부 확인
		//3. 세션 전체삭제
		//session.invalidate(); => 로그아웃시
		
		if(logInMember != null) {
			//로그인 성공
			session.setAttribute("loginmember", logInMember);
			re.addFlashAttribute("msg", "로그인 되었습니다.");
			//로그인 성공시 홈으로 이동
			return "redirect:/";
		}else {
			re.addFlashAttribute("msg", "로그인 실패");
			//로그인 실패시 홈으로 이동
			return "redirect:/member/login";			
		}
	}
	
	//로그아웃
	@GetMapping("/member/logout")
	public String loginout(HttpSession session) {
		System.out.println("MemberController loginout 메소드 확인");
		//1. session에 담겨있으므로 session.invalidate()로 세션객체 완전 삭제함
		session.invalidate();
		
		//로그아웃시 home으로 이동
		return "redirect:/";
	}
	

	
}
