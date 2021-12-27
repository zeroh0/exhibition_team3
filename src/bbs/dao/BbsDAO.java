package bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jdbc.DBConnection;
import jdbc.DBUtil;

public class BbsDAO {
	
	/**
	 * 글 조회수 증가 처리
	 */
	public void updateBbsReadcount(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update bbs set readCount = readCount+1 where num = ?";
		try {
			conn = DBConnection.getConnection();	
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("에러: " + e);
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}
	
	
}
