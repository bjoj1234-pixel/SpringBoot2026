package com.green.board;

//페이징을 하기위한 계산식을 가지고 있는 클래스
public class PageHandler {

	//1. 기본 변수
	private int totalCnt; //전체 게시글 개수
	private int pageNum; //현재 페이지 번호
	private int pageSize; //한 페이지에 보여줄 레코드(=행의) 개수
	private int pageBlock = 3;//한 화면에 페이지 묶음(1~3페이지)
	
	//2. DB 조회 변수
	//Limit 1(startRow), 5(PageSize) => 1부터 시작해서 5개만 출력
	private int startRow; //DB의 시작 위치
	private int endRow; //가져올 게시글 개수 = pageSize
	
	//3. PageBlock 부분 : [1][2][3], [4][5][6]
	private int totalPage; //전체 페이지 수
	private int startPage; //블록페이지의 시작 번호
	private int endPage; //블록페이지의 마지막 번호
	
	private boolean prev; //◀이전
	private boolean next; //▶다음
	
	//생성자
	public PageHandler(int totalCnt, int pageNum, int pageSize) {
		this.totalCnt = totalCnt;
		this.pageNum = pageNum;
		this.pageSize = pageSize;		

		//계산함수 콜할 예정
		calcPaging();
	}
	
	//페이지 계산하는 메소드
	public void calcPaging() {
		// totalPage : 전체 페이지수 계산
		// [1][2][3], [4][5][6]
		// 게시글의 개수 계속 증가/감소
		// 한페이지에 5개 / 총 게시글 11개 -> 블록(페이지수) 3개
		// 11개 / 5개 => int(2.2) X
		// 나누어서 소수자리수까지 모두 반올림이 되어야 페이지 하나 생성
		totalPage = (int)Math.ceil(totalCnt / (double) pageSize); //Math.ceil은 나눴을때 소수점을 무조건 올림해서 정수로 출력하는 메소드
//		totalPage = (int)Math.ceil(totalPage / (double) pageSize); //Math.ceil은 나눴을때 소수점을 무조건 올림해서 정수로 출력하는 메소드
		//(double) pageSize을 한 이유 => 1.0개,2.0개,3.0개 이런식으로 바꿔줘야 나눴을때 2.2 이런식으로 결과가 나오면서, 이게 올림되서 정수로 출력됨

		//DB조회하는 범위, 첫번째
		//[1]페이지 -> 0부터 5개 0~4 
		//[2]페이지 -> 5부터 5개 5~9
		//[3]페이지 -> 10부터 5개 10~14
		
		//pageNum = 1 => 현재 페이지 번호
		startRow = (pageNum - 1) * pageSize;
		endRow = pageSize;
		
		// pageBlock = 3 블록페이지의 시작 / 끝
		// [1][2][3], [4][5][6], [7][8][9]
		// pageNum = 1, pageBlock = 3 : (1-1) /3 = 0
		// 0*3 = 0+1 => 1
		// pageNum이 1 또는 2 또는 3 이어도 모두 startPage = 1이 출력되어야함
		// 2-1 = 1/3 => 0 * 3 = 0+1 => 1
		// 3-1 = 2/3 => 0 * 3 = 0+1 => 1
		// 4-1 = 3/3 => 1 * 3 = 3+1 => 4
		startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
		//현재 pageBlock =3으로 지정, 만약 pageBlock = 5로 바꾸면 단순 2를 플러스하는 방식은 아래와 같은 이유로 안된다.
		//endPage = startPage + 2 => [1][2][3][4][5] => 식 불가능(X)
		endPage = startPage + (pageBlock - 1); 
		
		//실제 페이지는 [1] ~ [8] => 전체 페이지 수(totalPage)까지만 출력되어야 하는데
		//위의 계산식으로는 [1] ~ [9]까지 출력된다.
		//이런 경우 가장 마지막 페이지를 강제로 endPage에 totalPage 담아준다.
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		// 이전/다음 버튼 여부 true, false
		// 이전의 게시글이 존재하면 이전(◀)생성
		prev = (startPage > 1);//true/false 반환		
		
		// 다음의 게시글이 존재하면 이전(▶)생성
		// endPage = 8, totalPage = 8 =>false;
		// endPage = 8, totalPage = 9 =>true;
		next = (endPage < totalPage);//true/false 반환

		
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
	
	
}




