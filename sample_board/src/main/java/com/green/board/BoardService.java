package com.green.board;

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
	
	
}
