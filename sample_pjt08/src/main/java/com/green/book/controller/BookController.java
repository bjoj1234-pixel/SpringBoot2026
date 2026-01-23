package com.green.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.book.dto.BookDTO;
import com.green.book.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	BookService bookservice;
	//도서 추가
	@PostMapping("/book/bookadd")
	public String bookAdd() {
		System.out.println("bookAdd()");
		return "bookAdd";
	}
	
	@PostMapping("/book/bookaddcheck")
	public String bookaddcheck(BookDTO bdto) {
		System.out.println("bookaddcheck()");
		
		return "redirect:/book/bookadd";
	}
	
	//도서 전체목록
	@GetMapping("/book/booktotal")
	public String bookTotal() {
		System.out.println("bookTotal()");
		return "bookTotal";
	}
	
	//추가한 도서
	@PostMapping("/book/booklist")
	public ModelAndView bookAddConfirm(BookDTO bdto) {
		System.out.println("bookAddConfirm()");
		
		bookservice.addBookConfirm(bdto);
		
		ModelAndView modelview = new ModelAndView();
		
		modelview.addObject("title",bdto.getTitle());
		modelview.addObject("author",bdto.getAuthor());
		modelview.addObject("isbn",bdto.getIsbn());
		
		modelview.setViewName("bookList");
				
		return modelview;		
	}
	
	//도서 검색하기
	@GetMapping("/book/bookresult")
	public ModelAndView bookSearch(BookDTO bdto) {
		System.out.println("bookSearch()");
		
		bookservice.addBookConfirm(bdto);
		
		ModelAndView modelview = new ModelAndView();
		
		modelview.addObject("s_title",bdto.getTitle());
		modelview.addObject("s_author",bdto.getAuthor());
		modelview.addObject("s_isbn",bdto.getIsbn());
		
		modelview.setViewName("bookresult");
				
		return modelview;	
	}

	
	
}
