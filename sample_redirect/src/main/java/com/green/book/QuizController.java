package com.green.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class QuizController {
	//1.입력 페이지
	@GetMapping("/quiz")
	public String quizPage() {	
		return "quiz-view";
	}

	//2.정답확인 페이지
	@PostMapping("/check-quiz")
	public String checkPage(	
		@RequestParam("pass") String pass,
		RedirectAttributes re
		) {
		//정답이면 main으로 이동
		if(pass.equals("1234")) {
			return "redirect:/main";
		}else {
			//오답이면 다시 퀴즈페이지 이동
			re.addFlashAttribute("msg","비밀번호가 틀렸습니다. 다시 시도하세요!");
			return "redirect:/quiz";			
		}
	}

	//3.메인 페이지
	@GetMapping("/main")
	public String mainPage() {		
		return "main-view";
	}
}
