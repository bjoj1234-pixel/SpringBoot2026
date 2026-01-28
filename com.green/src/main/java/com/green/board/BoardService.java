package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	//service에서는 가급적 객체화X DI를 통해 연결.
	
	@Autowired
	BoardDAO boardDAO;
	
	//하나의 게시글이 추가되는 메소드를 BoardDAO에서 접근하여 사용
	public void addBoard(BoardDTO bdto) {
		System.out.println("3)BoardService addBoard() 메소드호출");
		boardDAO.insertBoard(bdto);
	}
	
	//게시글 전체 목록을 출력하는 메소드
	public List<BoardDTO> allBoard(){
		System.out.println("3)BoardService allBoard() 메소드호출");
		return boardDAO.getAllBoard();
	}
	
	//하나의 게시글을 출력하는 메소드
	public BoardDTO OneBoard(int num) {
		System.out.println("3)BoardService OneBoard() 메소드호출");
		return boardDAO.getOneBoard(num);
	}

	//하나의 게시글을 수정하는 메소드
	public boolean modifyBoard(BoardDTO bdto) {
		System.out.println("3)BoardService modifyBoard() 메소드호출");
		
		int result = boardDAO.updateBoard(bdto);
		
		if(result > 0) {
			System.out.println("게시글 수정 성공");
			return true; //수정이 된 경우
		}else {
			System.out.println("게시글 수정 실패(비밀번호 불일치)");
			return false;
		}
	}
	


}
