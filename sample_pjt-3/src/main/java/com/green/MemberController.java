package com.green;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class MemberController {
	//DI(의존성 객체주입)
	//MemberController가 직접 MemberService를 생성하지않고
	//스프링 컨테이너(AppConfig.java)가 만든 memberservice를 주입시켜라
	@Autowired
	MemberService memberservice;

	// 아래 작성한 메소드 핸들러 메소드이다.
	// 회원 가입양식
	// http://localhost:8090/member/signup의 경로를 매핑(연결)한다.
	@GetMapping("/member/signup") 
	public String signUpForm() {
		//아래 프린트문은 log역할
		System.out.println("signUpForm()");
		return "signUpForm"; //응답에 사용하는 html파일 이름반환
	}
	
	// 로그인 양식
	@GetMapping("/member/signin")
	public String signInForm() {
		System.out.println("signInForm()");
		// src/main/resources/templates/signinForm.html
		// 매핑한다. => 매핑 URL주소는 @GetMapping("/member/signin")이다. 
		return "signInForm";
	}
	
	// 회원가입한 데이터가 signUpResult 페이지로 전달되는 메소드
	// 숨겨서 가는 @PostMapping()사용한다.
	// 가입한 자료를 매개변수로 넘겨줘야 하므로 @RequestParam 사용한다.
	// public String sign(String id, String pw, String email){}
	@PostMapping("/member/signUp_confirm")
	public String signupconfirm(MemberDTO mdto,Model model) {
		System.out.println("signupconfirm()");
		
		//MemverService 비즈니즈 로직을 담당하는 클래스
		//MemberService memberservice =new MemberService();
		memberservice.signUpConfirm(mdto);
		//먼저 메소드명을 적고 이클립스에서 클릭을 하면 자동으로 해당 클래스에 메소드가 생성됨
		
		
		//현재가입한 시간을 출력하는 로직을 작성
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		

		
		// model로 담기
		model.addAttribute("now", sdf.format(dt));
		model.addAttribute("new_id", mdto.getId());
		model.addAttribute("new_pw", mdto.getPw());
		model.addAttribute("new_email", mdto.getEmail());
		
		return "signUpResult";
	}
		
	
	//ModelAndView 클래스는 model과 View를 하나로 합쳐서 클라이언트에 전달한다.
	
	//MemberDTO를 데이터 타입으로 매개변수 지정한다.
	@GetMapping("/member/signInconfirm")
	public ModelAndView signInconfirm(MemberDTO mdto) {
		
			//MemberService memberservice =new MemberService();
			memberservice.signInConfirm(mdto);
			
			ModelAndView modelview = new ModelAndView();
			
			modelview.addObject("id",mdto.getId());
			modelview.addObject("pw",mdto.getPw());
			
			//view페이지 signInResult.html은 어떻게 추가하나
			modelview.setViewName("signInResult");
			
			//Model model2
			
		
		System.out.println("signconfirm()");

		
		return modelview;
	}
	
}
