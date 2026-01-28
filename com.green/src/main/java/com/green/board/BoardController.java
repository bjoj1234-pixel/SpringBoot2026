package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	
	// 1. 게시글 작성 폼화면으로 이동하는 핸들러
	@GetMapping("/board/write")
	public String boardWriteForm(){
		System.out.println("1)BoardController boardWriteForm() 메소드호출");
		String nextPage = "board/boardWrite_form";
		return nextPage;
	}
	
	// 2. 폼에서 입력한 데이터를 DB에 영구저장하는 데이터 추가를 처리하는 컨트롤러
	@PostMapping("/board/writePro")
	public String boardWritePro(BoardDTO bdto) {
		System.out.println("1)BoardController boardWritePro() 메소드호출");
		
		//서비스의 addBoard() 메소드를 호출하여 DB저장
		boardservice.addBoard(bdto);
		
		//저장 후에는 => 게시판 목록으로 페이지 이동(redirect)
		return "redirect:/board/list";		
	}
	
	//3. DB에서 전체 게시글 목록 select로 검색하여 추출 => 모델(model)객체 담는다.
	//	 전체목록 화면 boardList.html로 이동한다.
	@GetMapping("/board/list")
	public String printBoard(Model model) {
		System.out.println("1)BoardController printBoard() 메소드호출");
		
		List<BoardDTO> listboard = boardservice.allBoard();
		
		model.addAttribute("list", listboard);
		
		String nextPage = "board/boardList";
		return nextPage;		
	}
	
	//4. 하나의 게시글 상세정보 확인 핸들러
	//num 글번호 받아 -> 해당 게시글 DB에서 조회하고, 그 상세정보를 
	//boardInfo 전달하는 컨트롤러
	@GetMapping("/board/boardInfo")
	public String boardInfo(@RequestParam("num") int num, Model model) {
		System.out.println("1)BoardController boardInfo() 메소드호출");
		
		BoardDTO oneboardInfo = boardservice.OneBoard(num);
		model.addAttribute("oneboard", oneboardInfo);
		
		String nextPage = "board/boardInfo";
		return nextPage;		
	}	
	
	//5. 게시글의 수정폼으로 이동하는 컨트롤러
	@GetMapping("/board/update")
	public String boardUpdateForm(@RequestParam("num") int num, Model model) {
		System.out.println("1)BoardController boardUpdateForm() 메소드호출");
		//기존의 하나의 게시글을 불러오는 쿼리를 이용하여 수정한다.
		BoardDTO oneboardInfo = boardservice.OneBoard(num);
		
		model.addAttribute("oneboard", oneboardInfo);
		
		String nextPage = "board/boardUpdate_form";
		return nextPage;
	}
	
	//6. 하나의 게시글 수정을 처리하는 컨트롤러
	@PostMapping("/board/updatePro")
	public String boardUpdatePro(BoardDTO bdto, Model model) {
		System.out.println("1)BoardController boardUpdatePro() 메소드호출");
		
		boolean isSuccess = boardservice.modifyBoard(bdto);
		//수정완료면 true, 아니면 false
		if(isSuccess) {
			//수정 성공시 list로 이동
			return "redirect:/board/list";
		}else {
			//수정 실패시 현재 수정하고 있는 폼에 num을 가지고 존재해야 함
			return "redirect:/board/update?num="+bdto.getNum();
		}		
	}
	
	
	
}
