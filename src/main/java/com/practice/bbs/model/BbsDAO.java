package com.practice.bbs.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.practice.util.JdbcUtil;

public class BbsDAO {
	
	private static BbsDAO dao = new BbsDAO();
	private BbsDAO() {
		
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static BbsDAO getInstance() {
		 return dao;
	}
	
	String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	String uid = "jsp";
	String upw = "jsp";
	
	DataSource ds;
	
	//글 등록 메서드
	
	public void regist(String writer, String title, String content) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO BBS(BNO, WRITER, TITLE, CONTENT) VALUES (BBS_SEQ.NEXTVAL, ?, ?, ?)";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		
	}
	//목록조회 메서드
	
	public ArrayList<BbsVO> getList(int pageNum, int amount){
		
		ArrayList<BbsVO> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT *\r\n"
				+ "FROM (SELECT ROWNUM AS RN,\r\n"
				+ "             A.*\r\n"
				+ "      FROM (SELECT *\r\n"
				+ "            FROM BBS\r\n"
				+ "            ORDER BY BNO DESC)A)\r\n"
				+ "WHERE RN > ? AND RN <= ?";
		
		try {
			
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNum -1) * amount);
			pstmt.setInt(2, pageNum * amount); 
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int bno = rs.getInt("bno");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				
				BbsVO vo = new BbsVO(bno, title, writer, content, regdate);
				
				list.add(vo);
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
		
		
	}
	//전체 게시글 수 계산하는 메서드
	public int getTotal() {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) AS TOTAL FROM BBS";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return result;
	}
	
	//게시글 상세보기
	public BbsVO GetContent(String bno) {
		
		BbsVO vo = new BbsVO();
		
		String sql = "select * from bbs where bno = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				vo.setRegdate( rs.getTimestamp("regdate") );
				vo.setBno( rs.getInt("bno"));
				vo.setWriter( rs.getString("writer") );
				vo.setTitle( rs.getString("title") );
				vo.setContent( rs.getString("content") );
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		return vo;
		
	}
	//글 수정
	public void update(String bno, String title, String content) {
		
		String sql = "update bbs set title = ?, content = ? where bno = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, bno);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		
	}
	
	//글 삭제
	public void delete(String bno) {
		
		String sql = "delete from bbs where bno = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			
			pstmt.executeUpdate();
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
			
		}
		
		
		
	}

}
