package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BoardController {
	//서비스 DI
	@Autowired
	BoardService boardService;
	
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
		int result = boardService.boardWriteConfirmService(bdto);
		
		if(result == boardService.board_write_success) {
			re.addFlashAttribute("msg", "글작성 되었습니다.");
			return "redirect:/board/list";//글작성 성공시 전체목록
		}else {
			re.addFlashAttribute("msg", "글작성 실패");
			return "redirect:/board/write";//글작성 실패시 글작성페이지에 머뭄
		}
	}
	
	//게시판 전체 목록
	@GetMapping("/board/list")
	public String boardList(Model model) {
		System.out.println("BoardController boardList() 메소드 확인");
		
		List<BoardDTO> boardlist = boardService.boardListService();
		model.addAttribute("list", boardlist);
		
		String nextPage="board/boardList";
		return nextPage;
	}
	
	//게시판 글 삭제
	@GetMapping("/board/delete")
	public String boardDelete(
			@RequestParam("id") int id,
			RedirectAttributes re
			) {
		System.out.println("BoardController boardDelete() 메소드 확인");
		
		boolean result = boardService.boardDeleteService(id);
		
		if(result) {
			re.addFlashAttribute("msg", "삭제 되었습니다.");			
		}else {
			re.addFlashAttribute("msg", "삭제 실패");			
		}
		
		return "redirect:/board/list";	
	}
	
	//게시글 수정 화면출력
	@GetMapping("/board/modify")
	public String boardModify(BoardDTO bdto,Model model) {
		System.out.println("BoardController boardModify() 메소드 확인");
		
		BoardDTO onelist = boardService.boardModifyService(bdto.getId());
		model.addAttribute("onelist", onelist);
		
		String nextPage="board/boardModify";
		return nextPage;				
	}
	
	//게시글 수정 핸들러
	@PostMapping("/board/modifySubmit")
	public String boardModifySubmit(BoardDTO bdto,RedirectAttributes re) {
		System.out.println("BoardController boardModifySubmit() 메소드 확인");
		
		boolean resultt = boardService.boardModifySubmitService(bdto);
		
		if(resultt) {
			re.addFlashAttribute("msg", "게시글이 수정되었습니다.");
			return "redirect:/board/list";
		}else {
			re.addFlashAttribute("msg", "수정실패");
			return "redirect:/board/modify?id="+bdto.getId();
		}		
	}
	
}