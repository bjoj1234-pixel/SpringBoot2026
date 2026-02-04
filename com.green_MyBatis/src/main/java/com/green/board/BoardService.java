package com.green.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.board.mapper.BoardMapper;

@Service
public class BoardService {
	
	//service에서는 가급적 객체화X DI를 통해 연결.
	
//	@Autowired
//	BoardDAO boardDAO;
	
	@Autowired
	private BoardMapper boardmapper; 
	
	//하나의 게시글이 추가되는 메소드를 BoardDAO에서 접근하여 사용
	public void addBoard(BoardDTO bdto) {
		System.out.println("3)BoardService addBoard() 메소드호출");
		boardmapper.insertBoard(bdto);
	}
	
	//게시글 전체 목록을 출력하는 메소드
	public List<BoardDTO> allBoard(){
		System.out.println("3)BoardService allBoard() 메소드호출");
		return boardmapper.getAllBoard();
	}
	
	//하나의 게시글을 출력하는 메소드
	public BoardDTO OneBoard(int num) {
		System.out.println("3)BoardService OneBoard() 메소드호출");
		// 조회수 증가 메소드 추가
		boardmapper.upReadCount(num);
		//조회수 증가 + 하나게시글 검색
		return boardmapper.getOneBoard(num);
	}

	//하나의 게시글을 수정하는 메소드
	public boolean modifyBoard(BoardDTO bdto) {
		System.out.println("3)BoardService modifyBoard() 메소드호출");
		
		int result = boardmapper.updateBoard(bdto);
		
		if(result > 0) {
			System.out.println("게시글 수정 성공");
			return true; //수정이 된 경우
		}else {
			System.out.println("게시글 수정 실패(비밀번호 불일치)");
			return false;
		}
	}
	
	//게시글 하나를 삭제하는 메소드
	public boolean removeBoard(int num, String writerPw) {
		System.out.println("3)BoardService removeBoard() 메소드호출");
		//DAO에서 받아오는 deleteBoard()는 삭제=1, 아니면 0
		int result = boardmapper.deleteBoard(num, writerPw);
		
		if(result > 0) {
			System.out.println("게시글 삭제 성공");
			return true;
		}else {
			System.out.println("게시글 삭제 실패(비밀번호 불일치)");
			return false;
		}
	}
	
	
	//게시글 검색 메소드
	public List<BoardDTO> searchBoard(String searchType, String searchKeyword){
		System.out.println("3)BoardService searchBoard() 메소드호출");
		System.out.println("3)searchType :"+searchType);
		System.out.println("3)searchKeyword :"+searchKeyword);
		return boardmapper.getSearchBoard(searchType,searchKeyword);
	}
	
	//전체 게시글수 검색하는 메소드
	public int getAllcount() {
		System.out.println("3)BoardService getAllcount() 메소드호출");
		return boardmapper.getAllcount();
	}
	
	//startRow ~ pageSize까지의 행 출력
	public List<BoardDTO> getPagelist(int startRow, int pageSize){
		System.out.println("3)BoardService getPagelist() 메소드호출");
		return boardmapper.getPagelist(startRow, pageSize); 
	}
	
	//검색 페이징에 필요한 메소드
	public int getSearchCount(String searchType,String searchKeyword) {
		System.out.println("3)BoardService getSearchCount() 메소드호출");
		return boardmapper.getSearchCount(searchType, searchKeyword);
	}
	//검색결과 노출
	public List<BoardDTO> getSearchPageList(String searchType, String searchKeyword,int startRow,int pageSize){
		System.out.println("3)BoardService getSearchPageList() 메소드호출");
		return boardmapper.getSearchPageList(searchType, searchKeyword, startRow, pageSize);
	}
	
	public int getMyBoardCount(String loginId) {
		System.out.println("3)BoardService getMyBoardCount() 메소드호출");
		return boardmapper.getMyBoardCount(loginId);
	}
	
	public List<BoardDTO> getMyBoardList(String loginId, int startRow, int pageSize) {
		System.out.println("3)BoardService getMyBoardList() 메소드호출");
		return boardmapper.getMyBoardList(loginId, startRow, pageSize);
	}

	
			

	
	

}
















