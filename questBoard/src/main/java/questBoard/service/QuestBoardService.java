package questBoard.service;

import java.util.List;

import questBoard.dto.QuestBoardDTO;

public interface QuestBoardService {
	//게시글 추가
	public void inserQuestBoard(QuestBoardDTO qdto);
	
	//게시글 전체출력
	public List<QuestBoardDTO> getAllQuestBoard();
	
	//하나의 게시글을 return
	public QuestBoardDTO getOneBoard(int num);
	
	//댓글 작성 추가
	public void rePlyInsert(QuestBoardDTO qdto);
	
	//댓글 작성시 글의 순서와 들여쓰기 단계를 update
	public void reSqUpdate(QuestBoardDTO qdto);
	
	//댓글 추가시 update 먼저 실행하고 추가시켜주는 메소드
	public void rePlyProcess(QuestBoardDTO qdto);
	
	public int findrePl(QuestBoardDTO qdto);

}
