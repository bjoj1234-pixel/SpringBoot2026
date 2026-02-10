package questBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import questBoard.dto.QuestBoardDTO;

@Mapper
public interface QuestBoardMapper {
	//게시글 추가
	public void inserQuestBoard(QuestBoardDTO qdto);
	
	//게시글 전체출력
	public List<QuestBoardDTO> getAllQuestBoard();
	
	//하나의 게시글을 return
	public QuestBoardDTO getOneBoard(int num);
	
	//댓글 작성 추가
	public void rePlyInsert(QuestBoardDTO qdto);
	
	//댓글 작성시 댓글의 순서와 단계를 update
	public void reSqUpdate(QuestBoardDTO qdto);
	
}
