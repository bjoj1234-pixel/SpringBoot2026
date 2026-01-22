package com.green.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.book.dao.BookDAO;
import com.green.book.dto.BookDTO;

@Service
public class BookService {
	
	@Autowired
	BookDAO bookdao;
	
	public void addBookConfirm(BookDTO bdto) {
		System.out.println("도서정보 입력화면이야");
		bookdao.addBook(bdto);
	}
	
	
}
