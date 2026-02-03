package com.green.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.board.BoardDTO;

@Mapper
public interface BoardMapper {
	//하나의 게시글 작성하여 추가하는 쿼리문
	public void insertBoard(BoardDTO bdto);
	
	//전체 게시글 목록을 출력하는 쿼리문
	public List<BoardDTO> getAllBoard();
	
	//하나의 게시글 상세정보보기
	//Readcount 누적하여 조회수를 증가하는 메소드도 함께 작성한다.
	public int upReadCount(int num);
	public BoardDTO getOneBoard(int num);
	
	//하나의 게시글을 수정하는 메소드
	public int updateBoard(BoardDTO bdto);
	
	// 게시글 작성시 비밀번호 입력하였기 때문에 => 삭제시에도 비밀번호와 번호가 일치하는지 체크
	// 매개변수가 2개이상인 경우는 @Param("변수" 데이터타입 필드명)이용해 작성한다.
	public int deleteBoard(@Param("num") int num,@Param("writerPw") String writerPw);
	
	//내용 또는 제목으로 게시글 검색하는 메소드
	//검색메소드 반드시, searchType, searchKeyword 매개변수 필요
	public List<BoardDTO> getSearchBoard(@Param("searchType") String searchType,
			@Param("searchKeyword") String searchKeyword);
	
	//전체 게시글의 개수를 구하는 메소드
	public int getAllcount();
	
	//Limit 1(startRow), 5(PageSize) => 1부터 시작해서 5개만 출력
	//startRow ~ 한 페이지에 보여줄 레코드(=행의) 개수(pageSize)만큼 보여주는 메소드
	public List<BoardDTO> getPagelist(
			@Param("startRow") int startRow,
			@Param("pageSize") int pageSize);
	
}
