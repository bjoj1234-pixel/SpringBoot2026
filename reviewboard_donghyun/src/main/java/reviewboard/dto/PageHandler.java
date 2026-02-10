package reviewboard.dto;

public class PageHandler {
	private int totalBoard;//전체 게시글 수
	private int currentPage;//현재 페이지 번호
	private int rowNum;//한 페이지에 보여줄 행수
	private int pageBlock = 3; //페이지 묶음 수 [1][2][3]▶
	
	private int startRow;//(보여지는 내에서)시작 줄 위치
	private int endRow;//(보여지는 내에서)끝 줄 위치
	
	private int totalPage; //전체 페이지 수
	private int startPage; //(보여지는 내에서)블록페이지의 시작 번호
	private int endPage; //(보여지는 내에서)블록페이지의 마지막 번호
	
	private boolean prev; //◀이전
	private boolean next; //▶다음
	
	public PageHandler(int totalBoard, int currentPage, int rowNum) {
		this.totalBoard = totalBoard;
		this.currentPage = currentPage;
		this.rowNum = rowNum;
		
		calcPaging();//페이징 계산함수
	}
	
	//페이징 계산함수
	public void calcPaging() {
		totalPage = (int)Math.ceil(totalBoard / (double)rowNum);
		//ceil=> 소수점을 무조건 올림해줌

		//(보여지는 내에서)시작하는 줄
		startRow = (currentPage - 1) * rowNum;
		//(보여지는 내에서)끝줄
		endRow = rowNum;
		//(보여지는 내에서)시작페이지 번호
		startPage = ((currentPage - 1)/pageBlock) * pageBlock + 1;
		//(보여지는 내에서) 끝페이지 번호
		endPage = startPage + (pageBlock - 1);
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		prev = (startPage > 1);//true or false 반환
		next = (endPage < totalPage);//true or false 반환
	}

	public int getTotalBoard() {
		return totalBoard;
	}

	public void setTotalBoard(int totalBoard) {
		this.totalBoard = totalBoard;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
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
