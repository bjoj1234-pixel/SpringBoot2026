package com.green.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MyController {
	
	@GetMapping("/old-url")
	public String oldPage(RedirectAttributes re) {
		//리다이렉트 시점에서 딱 한번만 사용할 데이터를 임시로 보관함
		re.addFlashAttribute("msg","이전 주소에서 자동으로 이동되었습니다.");
		//"new-url" 주소로 리다이렉트 하라는 의미
		//새로운 주소로 이동해
		return "redirect:/new-url";
	}
	
//	@GetMapping("/old-url")
//	public String oldPage() {
//		//"new-url" 주소로 리다이렉트 하라는 의미
//		//새로운 주소로 이동해
//		return "redirect:/new-url";
//	}
	
	@GetMapping("/new-url")
	public String newPage() {
		
		return "new-page-view"; //new-page-view.html로 연결
	}
}
