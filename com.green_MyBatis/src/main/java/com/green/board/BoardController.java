package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.member.MemberDTO;

import jakarta.servlet.http.HttpSession;


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
	public String boardWritePro(BoardDTO bdto,HttpSession session) {
		System.out.println("1)BoardController boardWritePro() 메소드호출");
		
		//session.setAttribute("loginmember") 저장한 데이터를 꺼내와야 한다.
		//세션에서 값 꺼내오는 메소드 session.getAttribute("loginmember")
		//Session은 자바의 Object 최상위 객체이므로 다운캐스팅 하여야한다.
		//로그인 id=> admin9867의 정보 한 행이 모두 MemberDTO 타입으로
		//loginedMember에 저장된다.
		MemberDTO loginedMember = (MemberDTO)session.getAttribute("loginmember");
		
		//로그인 정보가 존재하는지 체크하는 코드가 필요
		if(loginedMember != null){
			//지금 현재 로그인된 id=>loginedMember.getId()
			bdto.setId(loginedMember.getId());
			System.out.println("DB에 저장될 아이디 확인:"+loginedMember.getId());
		}else{
			System.out.println("로그인 실패");
			return "redirect:/member/login";
		}
		//서비스의 addBoard() 메소드를 호출하여 DB저장
		boardservice.addBoard(bdto);
		
		//저장 후에는 => 게시판 목록으로 페이지 이동(redirect)
		return "redirect:/board/list";		
	}
	
	//3. DB에서 전체 게시글 목록 select로 검색하여 추출 => 모델(model)객체 담는다.
	//	 전체목록 화면 boardList.html로 이동한다.
//	@GetMapping("/board/list")
//	public String printBoard(Model model) {
//		System.out.println("1)BoardController printBoard() 메소드호출");
//		
//		List<BoardDTO> listboard = boardservice.allBoard();
//		
//		model.addAttribute("list", listboard);
//		
//		String nextPage = "board/boardList";
//		return nextPage;		
//	}
	
	//검색을 위한 board/list 커스텀 하기
	@GetMapping("/board/list")
	public String boardList(Model model,
			@RequestParam(value="searchType",required=false) String searchType,
			@RequestParam(value="searchKeyword",required=false) String searchKeyword,
			//1. 페이지 번호 => 1부터 시작이므로 초기값 1로 정의한다.
			@RequestParam(value="page",defaultValue = "1") int page,
			//2. 페이지 사이즈 => 한 화면에 보여지는 게시글의 개수를 5로 초기화한다.
			@RequestParam(value="pageSize",defaultValue = "5") int pageSize
			) {
		System.out.println("1)BoardController printBoard() 메소드호출");
		
		//3. 전체 게시글의 개수인 totalCnt 메소드 가져오기
		int totalCnt = boardservice.getAllcount();
		System.out.println("전체행의 개수"+ totalCnt);
		
		//4. PageHandler 클래스 접근하기위해 인스턴스화 한다.		
		PageHandler ph = new PageHandler(totalCnt,page,pageSize);
		
		List<BoardDTO> listboard;
		
		//검색 종료 후 => 검색내용이 list나오기
		if(searchType != null && !searchKeyword.trim().isEmpty()) {
			//서비스에서 searchBoard() 메소드호출
			listboard = boardservice.searchBoard(searchType, searchKeyword);
		}else {
			//검색하지 않고 전체보기 list나오기
			//boardservice.allBoard() => 사용못하는 이유는?
			//=>페이징이 안된 모든 레코드가 출력되는 메소드이므로
			listboard = boardservice.getPagelist(ph.getStartRow(),pageSize);
			
		}
		//System.out.print(listboard);
		
		//검색하지 않고 전체보기 list나오기
		model.addAttribute("list", listboard);
		
		//pageHandler 클래스 모두 model객체에 담아서 html로 보내야함
		//그래야 UI 화면에 페이징 그릴 수 있다.
		model.addAttribute("ph", ph); //PageHandler 클래스를 인스턴스한 참조변수이다.
		
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
	
	// 7. 하나의 게시글을 삭제하는 컨트롤러
	// 현재 boardInfo.html의 [삭제하기]버튼 클릭하면 삭제됨
	// 삭제된 후 board/list로 이동
	// 삭제 실패 후는 boardInfo.html에 머물다.
	@GetMapping("/board/deletePro")
	public String boardDeletePro(
			@RequestParam("num") int num,
			@RequestParam("writerPw") String writerPw
			) {
		System.out.println("1)BoardController boardDeletePro() 메소드호출");

		// boardService removeBoard()메소드 삭제: true, 실패:false
		boolean isSuccess = boardservice.removeBoard(num, writerPw);
		
		if(isSuccess){
			return "redirect:/board/list";
		}else {
			//삭제 실패 시 상세페이지 그대로 
			return "redirect:/board/boardInfo?num="+num;
		}
		
	}
	
}











