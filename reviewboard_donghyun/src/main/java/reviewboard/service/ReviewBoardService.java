package reviewboard.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reviewboard.dto.PageHandler;
import reviewboard.dto.ReviewBoardDTO;
import reviewboard.mapper.ReviewBoardMapper;

@Service
public class ReviewBoardService {
	@Autowired
	private ReviewBoardMapper reviewboardmapper;
	
	//전체 게시글 출력
//	public List<ReviewBoardDTO> getAllBoard(){
//		System.out.println("2)ReviewBoardService getAllBoard 메소드호출");
//		return reviewboardmapper.getAllBoard();
//	};
	
	//게시글 작성 후 추가
	public void insertBoard(ReviewBoardDTO rdto) {
		System.out.println("2)ReviewBoardService insertBoard 메소드호출");
		reviewboardmapper.insertBoard(rdto);
	};
	
	//게시글 상세보기
	public ReviewBoardDTO getReviewDetail(int num) {
		System.out.println("2)ReviewBoardService getReviewDetail 메소드호출");
		//상세보기 클릭시 조회수 증가
		reviewboardmapper.upReadCount(num);
		return reviewboardmapper.getReviewDetail(num);
	};
	
	//게시글 수정
	public boolean updateBoard(ReviewBoardDTO rbto) {
		System.out.println("2)ReviewBoardService updateBoard 메소드호출");
		
		//수정 결과를 1또는0으로 저장
		int result = reviewboardmapper.updateBoard(rbto);
		
		if(result > 0) {//성공시
			System.out.println("게시글 수정 성공");
			return true;
		}else {//실패시
			System.out.println("게시글 수정 실패(비밀번호 불일치)");
			return false;
		}
		
	};
		
	//게시글 삭제
	public boolean deleteBoardPro(int num,String writerPw) {
		System.out.println("2)ReviewBoardService deleteBoardPro 메소드호출");
		
		//수정 결과를 1또는0으로 저장
		int result = reviewboardmapper.deleteBoardPro(num, writerPw);
		
		if(result > 0) {//성공시
			System.out.println("게시글 삭제 성공");
			return true;
		}else {//실패시
			System.out.println("게시글 삭제 실패(비밀번호 불일치)");
			return false;
		}

	};
	
	
	//전체 게시글 갯수 계산
	public int getAllCount() {
		System.out.println("2)ReviewBoardService getAllCount 메소드호출");
		return reviewboardmapper.getAllCount();
	};
	
	//게시글 (보여줄 갯수만큼)출력
	public List<ReviewBoardDTO> getPageList(int startRow,int rowNum){
		System.out.println("2)ReviewBoardService getPageList 메소드호출");
		return reviewboardmapper.getPageList(startRow, rowNum);
	};
	
	//모든 게시글의 별점 평균계산
	public double getAllStarAvg() {
		System.out.println("2)ReviewBoardService getAllStarAvg 메소드호출");
		//모든 게시글의 별점 총합
		int allstar =  reviewboardmapper.getAllStar();
		//모든 게시글 수
		double allNum = (double) reviewboardmapper.getAllCount();
		//평균계산
		double avg = allstar/allNum;
		//소수점 첫째자리까지만 자르기
		double avg2 = Math.floor(avg * 10) / 10.0;		
		
		return avg2;
	};
	//별점 평균에 따른 별갯수 계산
	public int calcStarAvg() {
		System.out.println("2)ReviewBoardService calcStarAvg 메소드호출");
		int star = (int)Math.round(getAllStarAvg());
		return star;
	}
		
}
