package questBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import questBoard.dto.QuestBoardDTO;
import questBoard.service.QuestBoardService;

@Controller
public class QuestBoardController {

	@Autowired
	QuestBoardService questboardservice;
	
	@GetMapping("/board/list")
	public String boardList(Model model) {
		System.out.println("QuestBoardController boardList()호출");
		
		List<QuestBoardDTO> allList = questboardservice.getAllQuestBoard();
		
		model.addAttribute("allList",allList);
		
		return ""
	}
}
