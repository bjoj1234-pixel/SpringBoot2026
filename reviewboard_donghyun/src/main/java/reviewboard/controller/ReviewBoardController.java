package reviewboard.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reviewboard.dto.PageHandler;
import reviewboard.dto.ReviewBoardDTO;
import reviewboard.service.ReviewBoardService;

@Controller
public class ReviewBoardController {

	@Autowired
	ReviewBoardService reviewboardservice;
	
	//게시글 작성
	@GetMapping("/board/write")
	public String insertBoard() {
		System.out.println("1)ReviewBoardController insertBoard() 메소드호출");
		String nextPage = "board/boardWrite_form";
		return nextPage;
	}
	
	//게시글 작성후 DB에 저장하는 컨트롤러
	@PostMapping("/board/writePro")
	public String insertBoardPro(ReviewBoardDTO rdto) {
		System.out.println("1)ReviewBoardController insertBoardPro() 메소드호출");
		//DB저장
		reviewboardservice.insertBoard(rdto);
		//게시판 전체목록으로 redirect
		return "redirect:/board/list";
	}
	
	//전체 게시글 출력
	@GetMapping("/board/list")
	public String getAllBoard(Model model,
			@RequestParam(value="startPage",defaultValue = "1") int startPage,
			@RequestParam(value="rowNum",defaultValue = "5") int rowNum
			) {
		System.out.println("1)ReviewBoardController getAllBoard() 메소드호출");
		
		
		//전체 게시글 수
		int totalBoard = reviewboardservice.getAllCount();
		//전체 별점 평균
		double allStarAvg = reviewboardservice.getAllStarAvg();
		//별점 평균에 따른 별갯수 계산
		int star = reviewboardservice.calcStarAvg();
		
		//페이지 핸들러 
		PageHandler pghandler = new PageHandler(totalBoard, startPage, rowNum);
		
		List<ReviewBoardDTO> listboard;
		
		listboard = reviewboardservice.getPageList(pghandler.getStartRow(),rowNum);
		
		model.addAttribute("list", listboard);
		model.addAttribute("pghandler", pghandler);
		model.addAttribute("allStarAvg", allStarAvg);
		model.addAttribute("star", star);
		
		String nextPage = "board/boardList";
		return nextPage;	
	}
	
	//게시글 상세보기
	@GetMapping("/board/detail")
	public String getReviewDetail(@RequestParam("num") int num,Model model) {
		System.out.println("1)ReviewBoardController getReviewDetail() 메소드호출");
		
		ReviewBoardDTO reviewDetail = reviewboardservice.getReviewDetail(num);
		model.addAttribute("reviewdetail", reviewDetail);
		
		String nextPage = "board/boardDetail";
		return nextPage;			
	}
	
	//게시글 수정
	@GetMapping("/board/update")
	public String updateBoard(@RequestParam("num") int num,Model model) {
		System.out.println("1)ReviewBoardController updateBoard() 메소드호출");
		
		ReviewBoardDTO reviewUpdate = reviewboardservice.getReviewDetail(num);
		model.addAttribute("reviewUpdate", reviewUpdate);
		
		String nextPage = "board/boardUpdate_form";
		return nextPage;			
	}
	
	//게시글 수정을 처리하는 컨트롤러
	@PostMapping("/board/updatePro")
	public String updateBoardPro(ReviewBoardDTO rdto, Model model) {
		System.out.println("1)ReviewBoardController updateBoardPro() 메소드호출");
		
		boolean isSuccess = reviewboardservice.updateBoard(rdto);
		
		if(isSuccess) {//수정성공시
			return "redirect:/board/list";
		}else {//수정실패시			
			return "redirect:/board/update?num="+rdto.getNum();			
		}
	}
	
	//게시글 삭제
	@GetMapping("/board/delete")
	public String deleteBoard(@RequestParam("num") int num,Model model) {
		System.out.println("1)ReviewBoardController deleteBoard() 메소드호출");
		
		ReviewBoardDTO deleteBoard = reviewboardservice.getReviewDetail(num);
		model.addAttribute("deleteBoard", deleteBoard);

		String nextPage = "board/boardDelelte_form";
		return nextPage;	
	}
	
	//게시글 삭제를 처리하는 컨트롤러
	@PostMapping("/board/deletePro")
	public String deleteBoardPro(
			@RequestParam("num") int num,
			@RequestParam("writerPw") String writerPw
			) {
		System.out.println("1)ReviewBoardController deleteBoardPro() 메소드호출");

		boolean isSuccess = reviewboardservice.deleteBoardPro(num, writerPw);
		
		if(isSuccess) {//삭제성공시
			return "redirect:/board/list";
		}else {//삭제실패시(상세페이지 그대로)		
			return "redirect:/board/delete?num="+num;			
		}		
	}
	
}


















