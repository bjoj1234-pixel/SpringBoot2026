package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BoardController {
	//서비스 DI
	@Autowired
	BoardService boardservice;
	
	//게시판 글작성
	@GetMapping("/board/write")
	public String boardWrite() {
		System.out.println("BoardController boardWrite() 메소드 확인");
		String nextPage="board/boardWrite";
		return nextPage;
	}
	//글작성 핸들러
	@PostMapping("/board/write_confirm")
	public String boardWriteConfirm(BoardDTO bdto,RedirectAttributes re) {
		System.out.println("BoardController boardWriteConfirm() 메소드 확인");
		int result = boardservice.boardWriteConfirmService(bdto);
		
		if(result == boardservice.board_write_success) {
			re.addFlashAttribute("msg", "글작성 되었습니다.");
			return "redirect:/board/list";//글작성 성공시 전체목록
		}else {
			re.addFlashAttribute("msg", "글작성 실패");
			return "redirect:/board/write";//글작성 실패시 글작성페이지에 머뭄
		}
	}
	
	//게시판 전체 목록
//	@GetMapping("/board/list")
//	public String boardList() {
//		System.out.println("BoardController boardList() 메소드 확인");
//		
//		//List<BoardDTO> boardlist = 
//	}
}
