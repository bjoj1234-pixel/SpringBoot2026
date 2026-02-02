package com.green.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.member.MemberDTO;

@Mapper //MemberMapper는 매퍼(연결 : SQL(xml파일))역할을 한다.
public interface MemberMapper {
	//MemberDAO의 메소드들을 추상메소드로 작성한다.
	//설정된 메소드들은 IoC컨테이너에 탑재된다.

	//회원 가입 추가하는 추상메소드
	public int insertMember(MemberDTO mdto);
	//id 중복체크(이미가입된 id로 회원가입하면 실패출력)
	public boolean isMember(String id);
	//회원 전체 목록 검색 쿼리
	public List<MemberDTO> allSelectMember();
	//개인 한 사람의 정보를 검색하는 메소드
	public MemberDTO oneSelectMember(String id);
	//개인 한사람의 패스워드 리턴하는 쿼리
	public String getPass(String id);
	//개인 한사람의 정보를 수정하는 쿼리
	public int updateMember(MemberDTO mdto);
	// 한사람 개인의 정보를 삭제하는 메소드 작성
	public int deleteMember(String id);
}
