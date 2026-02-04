package com.myboard.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.myboard.board.BoardDTO;

@Mapper
public interface BoardMapper {
	//게시판 글작성
	public int boardWriteDAO(BoardDTO bdto);
	
	//게시판 전체목록 쿼리
	// ------ 2026년 2월 3일 오류 -------
	// boardListDAO() 메소드가 board-mapper.xml에 존재하지 않는데
	// boardService에서 사용하고 있어서 생기는 오류 입니다.
	public List<BoardDTO> boardListDAO();
	
	//게시글 삭제 쿼리
	public int boardDeleteDAO(int id);
	
	//게시글 수정 화면출력 쿼리
	public BoardDTO boardModifyDAO(int id);
	
	//게시글 수정 쿼리
	public int boardModifySubmitDAO(BoardDTO bdto);
	
	//검색 메소드 쿼리
	public List<BoardDTO> getSearchBoardDAO(@Param("searchType") String searchType,
			@Param("searchKeyword") String searchKeyword);
	
	
}
