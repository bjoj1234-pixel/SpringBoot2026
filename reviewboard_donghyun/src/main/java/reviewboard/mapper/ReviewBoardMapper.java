package reviewboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import reviewboard.dto.ReviewBoardDTO;

@Mapper
public interface ReviewBoardMapper {
	//전체 게시글 출력
	//public List<ReviewBoardDTO> getAllBoard();
	
	//게시글 작성 후 추가
	public void insertBoard(ReviewBoardDTO rdto);
	
	//게시글 상세보기
	public ReviewBoardDTO getReviewDetail(int num);
	
	//상세보기 클릭시 조회수 증가
	public void upReadCount(int num);
	
	//게시글 수정
	public int updateBoard(ReviewBoardDTO rbto);
	
	//게시글 삭제
	public int deleteBoardPro(
			@Param("num") int num,
			@Param("writerPw") String writerPw
	);
	
	//전체 게시글 갯수 계산
	public int getAllCount();
	
	//게시글 (보여줄 갯수만큼)출력
	public List<ReviewBoardDTO> getPageList(
			@Param("startRow") int startRow,
			@Param("rowNum") int rowNum);
	
	//모든 게시글의 별점 총합계산
	public int getAllStar();
	
}
