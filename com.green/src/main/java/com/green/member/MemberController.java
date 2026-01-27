package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	
	

	
}
