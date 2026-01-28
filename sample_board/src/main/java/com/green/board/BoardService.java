package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	//글작성의 성공여부를 확인하는 상수
	public final static int board_write_success = 1;
	//글작성의 실패를 확인하는 상수
	public final static int board_write_fail = -1;
		
	
	@Autowired
	BoardDAO boarddao;
	
	//글작성 메소드
	public int boardWriteConfirmService(BoardDTO bdto) {
		System.out.println("BoardService boardWriteConfirm() 메소드확인");
		return boarddao.boardWriteDAO(bdto);
	}
	
	//게시판 전체목록 출력 메소드
	public List<BoardDTO> boardListService(){
		System.out.println("BoardService boardListDAO() 메소드확인");
		return boarddao.boardListDAO();
	}
	
	//글삭제 메소드
	public boolean boardDeleteService(int id) {
		System.out.println("BoardService boardDeleteService() 메소드확인");
		return boarddao.boardDeleteDAO(id) == 1; //true(삭제) or false(삭제실패) 반환
	}
	
	//글수정 화면출력 메소드
	public BoardDTO boardModifyService(int id) {
		System.out.println("BoardService boardModifyService() 메소드확인");
		return boarddao.boardModifyDAO(id);
	}
	
	//글수정 메소드
	public boolean boardModifySubmitService(BoardDTO bdto) {
		System.out.println("BoardService boardModifySubmitService() 메소드확인");
		return boarddao.boardModifySubmitDAO(bdto) == 1;
	}
	
}
