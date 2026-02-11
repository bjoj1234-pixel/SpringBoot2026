package questBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import questBoard.dto.QuestBoardDTO;
import questBoard.service.QuestBoardService;

@Controller
public class QuestBoardController {

	@Autowired
	QuestBoardService questboardservice;
	
	//게시글 전체출력
	@GetMapping("/board/list")
	public String boardList(Model model) {
		System.out.println("QuestBoardController boardList()호출");
		
		List<QuestBoardDTO> allList = questboardservice.getAllQuestBoard();
		model.addAttribute("allList",allList);
		
		return "questBoard/questboardList";
	}
	
	//게시글 추가 -1
	//questboardWrite_Form으로 이동하는 컨트롤러
	@GetMapping("/board/writer")
	public String boardWriterForm() {
		System.out.println("QuestBoardController boardWriterForm()호출");

		String nextPage = "questBoard/questBoardWrite_form";
		return nextPage;
	}
	
	//게시글 추가 -2
	@PostMapping("/board/writerPro")
	public String boardWriterPro(QuestBoardDTO qdto){
		System.out.println("QuestBoardController boardWriterPro()호출");
		
		questboardservice.inserQuestBoard(qdto);
		 
		return "redirect:/board/list";
	}
	
	//하나의 상세 게시글 정보로 이동하는 컨트롤러
	@GetMapping("/board/detail")
	public String getOneBoard(@RequestParam("num") int num, Model model) {
		System.out.println("QuestBoardController getOneBoard()호출");
		
		QuestBoardDTO oneList = questboardservice.getOneBoard(num);
		model.addAttribute("onelist", oneList);
		
		return "questBoard/questboardDetail";
	}

	//답글 작성하는 폼으로 이동하는 컨트롤러
	@GetMapping("/board/reply")
	public String reWriteForm(Model model,
			@RequestParam("num") int num,
			@RequestParam("ref") int ref,
			@RequestParam("re_step") int re_step,
			@RequestParam("re_level") int re_level
			) {
		System.out.println("QuestBoardController reWriteForm()호출");
		
		model.addAttribute("num", num);
		model.addAttribute("ref", ref);
		model.addAttribute("re_step", re_step);
		model.addAttribute("re_level", re_level);
		
		return "questBoard/questBoardReWrite_Form";
	}
	
	//답글 작성을 처리하는 컨트롤러
	@PostMapping("/board/reWritePro")
	public String reWritePro(QuestBoardDTO qdto) {
		System.out.println("ReplyBoardController reWritePro()호출");
		questboardservice.rePlyProcess(qdto);
		return "redirect:/board/list";
	}
	

}
