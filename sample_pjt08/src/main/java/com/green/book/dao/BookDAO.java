package com.green.book.dao;


import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

//import com.green.AppConfig;
import com.green.book.dto.BookDTO;

@Repository
public class BookDAO {
//	private final AppConfig appConfig;
	
	//도서전체정보
	private ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
	
//	BookDAO(AppConfig appConfig) {
//        this.appConfig = appConfig;
//    }
	
	//add(도서추가) 메소드
	public void addBook(BookDTO bdto) {
		System.out.println("도서추가입력 메소드");
		
		printRent();
	}
	
	//addresult(도서추가) 메소드
	public void addResult(Model model) {
		System.out.println("도서추가입력 메소드");
		model.addAttribute("list", bookList);
		printRent();
	}
		
	//전체 도서 메소드
	public void totalBook(BookDTO bdto) {
		System.out.println("도서검색 메소드");
		printRent();
	}	
	
	//result(도서검색) 메소드
	public void searchBook(BookDTO bdto) {
		System.out.println("도서검색 메소드");
		printRent();
	}
	
	//출력 메소드
	public void printRent() {
		for(BookDTO bl : bookList) {
			System.out.println(bl.getTitle());
		}
		//System.out.println(bookList);
	}
	
}
