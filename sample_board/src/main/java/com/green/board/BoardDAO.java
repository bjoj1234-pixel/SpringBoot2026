package com.green.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Repository
public class BoardDAO {

	//외부 데이터 DI
	@Autowired
	private DataSource datasource;

	//게시판 글작성
	public int boardWriteDAO(BoardDTO bdto) {
		System.out.println("BoardDAO boardWriteDAO() 메소드확인");
		
		String sql = "INSERT INTO board2(title,content,writer) VALUES(?,?,?)";
		int result = 0;
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1,bdto.getTitle());
			pstmt.setString(2,bdto.getContent());
			pstmt.setString(3,bdto.getWriter());
			
			result = pstmt.executeUpdate();
			
			System.out.println("INSERT result"+result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	//게시판 전체목록 쿼리
	public List<BoardDTO> boardListDAO(){
		System.out.println("BoardDAO boardListDAO() 메소드확인");
		
		String sql = "SELECT * FROM board2";
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				ResultSet rs = pstmt.executeQuery();	
			){
			
			while(rs.next()) {
				BoardDTO bdto = new BoardDTO();
				
				bdto.setId(rs.getInt("id"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setCreatedAt(rs.getString("createdAt"));
				
				list.add(bdto);				
			}
			rs.close();		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;		
	}
	
	//게시글 삭제 쿼리
	public int boardDeleteDAO(int id) {
		System.out.println("BoardDAO boardDeleteDAO() 메소드확인");

		int result=0;
		String sql = "DELETE FROM board2 WHERE id=?";
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, id);			
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//게시글 수정 화면출력 쿼리
	public BoardDTO boardModifyDAO(int id) {
		System.out.println("BoardDAO boardModifyDAO() 메소드확인");
		
		BoardDTO bdto = new BoardDTO();
		
		String sql = "SELECT * FROM board2 WHERE id=?";
		
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt =conn.prepareStatement(sql);
				){
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bdto.setId(rs.getInt("id"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setCreatedAt(sql);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bdto;
	}
	
	//게시글 수정 쿼리
	public int boardModifySubmitDAO(BoardDTO bdto) {
		System.out.println("BoardDAO boardModifySubmitDAO() 메소드확인");
		int result = 0;
		
		String sql = "UPDATE board2 SET title=?, content=?, writer=? WHERE id=?";
		
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, bdto.getTitle());
			pstmt.setString(2, bdto.getContent());
			pstmt.setString(3, bdto.getWriter());
			pstmt.setInt(4, bdto.getId());
			
			result = pstmt.executeUpdate();

			System.out.println("UPDATE result"+result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;		
	}

	//검색 메소드 쿼리
	public List<BoardDTO> getSearchBoardDAO(String searchType, String searchKeyword){
		System.out.println("BoardDAO getSearchBoard() 메소드확인");
		
		List<BoardDTO> searchList = new ArrayList<BoardDTO>();
		
		String sql="";
		
		if("title".equals(searchType)) {
			sql = "SELECT * FROM board2 WHERE title LIKE ? ORDER BY id DESC";
		}else if("content".equals(searchType)) {
			sql = "SELECT * FROM board2 WHERE content LIKE ? ORDER BY id DESC";
		}else if("writer".equals(searchType)){
			sql = "SELECT * FROM board2 WHERE writer LIKE ? ORDER BY id DESC";
		}else {
			sql = "SELECT * FROM board2 WHERE createdAt LIKE ? ORDER BY id DESC";
		}
		
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO bdto = new BoardDTO();
				bdto.setId(rs.getInt("id"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setCreatedAt(rs.getString("createdAt"));
				
				searchList.add(bdto);
			}			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return searchList;
	}
	


}

















