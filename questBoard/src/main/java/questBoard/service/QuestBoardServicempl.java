package questBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questBoard.dto.QuestBoardDTO;
import questBoard.mapper.QuestBoardMapper;

@Service
public class QuestBoardServicempl implements QuestBoardService{
	
	@Autowired
	QuestBoardMapper questboardmapper;

	
	//게시글 추가
	@Override
	public void inserQuestBoard(QuestBoardDTO qdto) {
		System.out.println("QuestBoardServicempl inserQuestBoard() 출력");
		questboardmapper.inserQuestBoard(qdto);
	}
	//게시글 전체출력
	@Override
	public List<QuestBoardDTO> getAllQuestBoard() {
		System.out.println("QuestBoardServicempl inserQuestBoard() 출력");
		return questboardmapper.getAllQuestBoard();
	}
	//하나의 게시글을 return
	@Override
	public QuestBoardDTO getOneBoard(int num) {
		System.out.println("QuestBoardServicempl inserQuestBoard() 출력");
		return questboardmapper.getOneBoard(num);
	}
	//댓글 작성 추가
	@Override
	public void rePlyInsert(QuestBoardDTO qdto) {
		System.out.println("QuestBoardServicempl inserQuestBoard() 출력");
		questboardmapper.rePlyInsert(qdto);
	}
	//댓글 작성시 댓글의 순서와 단계를 update
	@Override
	public void reSqUpdate(QuestBoardDTO qdto) {
		System.out.println("QuestBoardServicempl inserQuestBoard() 출력");
		questboardmapper.reSqUpdate(qdto);
	}
	//댓글 추가시 update 먼저 실행하고 추가시켜주는 메소드
	@Override
	public void rePlyProcess(QuestBoardDTO qdto) {
		System.out.println("QuestBoardServicempl inserQuestBoard() 출력");
		questboardmapper.reSqUpdate(qdto);
		questboardmapper.rePlyInsert(qdto);		
	} 

}
