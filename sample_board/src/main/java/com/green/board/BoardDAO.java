package com.green.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	//외부 데이터 DI
	@Autowired
	private DataSource datasource;

	//게시판 글작성
	public int boardWriteDAO(BoardDTO bdto) {
		System.out.println("BoardDAO boardWriteDAO() 메소드확인");
		
		String sql = "INSERT INTO board(title,content,writer) VALUES(?,?,?)";
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
	


}
