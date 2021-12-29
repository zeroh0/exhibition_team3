package exhbn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exhbn.model.ExhbnDTO;
import jdbc.DBConnection;
import jdbc.DBConnectionOracle;
import jdbc.DBUtil;

public class ExhbnDAO {
	private static ExhbnDAO instance;
	private ExhbnDAO() {}
	public static ExhbnDAO getInstance() {
		if(instance == null) instance = new ExhbnDAO();
		return instance;
	}
	
	/**
	 * 	전시 목록 조회 
	 */
	public List<ExhbnDTO> getExhList() {
		List<ExhbnDTO> exhList = new ArrayList<ExhbnDTO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from exhbn order by e_id";
		
		try {
			conn=DBConnectionOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ExhbnDTO exhbn = new ExhbnDTO();
				exhbn.setE_id(rs.getInt(1));
				exhbn.setTitle(rs.getString(2));
				exhbn.setCategory(rs.getString(3));
				exhbn.setDescription(rs.getString(4));
				exhbn.setPrice(rs.getString(5));
				exhbn.setLocation(rs.getString(6));
				exhbn.setPeriod(rs.getString(7));
				exhbn.setTime(rs.getString(8));
				exhbn.setImage(rs.getString(9));
				
				exhList.add(exhbn);
			}
		}catch(Exception e) {
			System.out.println("에러:"+e.getMessage());
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return exhList;
	} //
	
	/**
	 * 상세보기에 나타날 관련된 전시페이지
	 */
	public List<ExhbnDTO> otherExh(int e_id) {
		List<ExhbnDTO> dtoList = new ArrayList<ExhbnDTO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from exhbn where e_id not in (?)";

		try {
			conn = DBConnectionOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ExhbnDTO exhbn = new ExhbnDTO();
				exhbn.setE_id(rs.getInt(1));
				exhbn.setTitle(rs.getString(2));
				exhbn.setCategory(rs.getString(3));
				exhbn.setDescription(rs.getString(4));
				exhbn.setPrice(rs.getString(5));
				exhbn.setLocation(rs.getString(6));
				exhbn.setPeriod(rs.getString(7));
				exhbn.setTime(rs.getString(8));
				exhbn.setImage(rs.getString(9));

				dtoList.add(exhbn);
			}
		} catch (Exception e) {
			System.out.println("에러:" + e.getMessage());
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return dtoList;
	} //
	
	/**
	 * 전시 상세보기 
	 */
	public ExhbnDTO detailView(int e_id) {
		ExhbnDTO dto=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from exhbn where e_id=?";
		try {
			conn = DBConnectionOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, e_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new ExhbnDTO();
				dto.setE_id(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setCategory(rs.getString(3));
				dto.setDescription(rs.getString(4));
				dto.setPrice(rs.getString(5));
				dto.setLocation(rs.getString(6));
				dto.setPeriod(rs.getString(7));
				dto.setTime(rs.getString(8));
				dto.setImage(rs.getString(9));
			}
		} catch (Exception e) {
			System.out.println("에러:" + e.getMessage());
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return dto;
	}
	
	/**
	 * 전시 등록 
	 */
	public void insertexhbn(ExhbnDTO exhbn) {
		  Connection conn=null;
		  PreparedStatement pstmt=null;
		  String sql ="insert into exhbn values(exhbn_seq.nextval,?,?,?,?,?,?,?,?)";
		  try {
			     conn = DBConnectionOracle.getConnection();
			     pstmt = conn.prepareStatement(sql);
			     pstmt.setString(1, exhbn.getTitle());
			     pstmt.setString(2, exhbn.getCategory());
			     pstmt.setString(3, exhbn.getDescription());
			     pstmt.setString(4, exhbn.getPrice());
			     pstmt.setString(5, exhbn.getLocation());
			     pstmt.setString(6, exhbn.getPeriod());
			     pstmt.setString(7, exhbn.getTime());
			     pstmt.setString(8, exhbn.getImage());
			     
			     pstmt.executeUpdate();
		  }catch(Exception e) {
			  System.out.println("에러:"+e.getMessage());
		  } finally {
			  try {
				    if(pstmt!=null) pstmt.close();
				    if(conn!=null)conn.close();
			  }catch(Exception e) {
				  throw new RuntimeException(e.getMessage());
			  }
		  }
	  } //
	
	/**
	 * 전시 수정 
	 */
	public void updateexhbn(ExhbnDTO exhbn) throws SQLException, ClassNotFoundException {
	    Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;

		String sql = "update exhbn set title=?,category=?,description=? ,price=? ,location=? ,period=?,time=?,image=? where e_id=?";
		
		try { 
			conn = DBConnectionOracle.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, exhbn.getTitle());
			pstmt.setString(2, exhbn.getCategory());
			pstmt.setString(3, exhbn.getDescription());
			pstmt.setString(4, exhbn.getPrice());
            pstmt.setString(5, exhbn.getLocation());
            pstmt.setString(6, exhbn.getPeriod());
            pstmt.setString(7, exhbn.getTime());
            pstmt.setString(8, exhbn.getImage());
            pstmt.setInt(9, exhbn.getE_id());
            //update처리
			pstmt.executeUpdate();
				

	 } finally {
		 DBUtil.close(rs);
		 DBUtil.close(pstmt);
		 DBUtil.close(conn);	
	  } 
	} //
	
	/**
	 * 전시 삭제 
	 */
	public void deleteexhbn(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String sql = "delete from exhbn where e_id=?";
		
		try { //삭제 처리
			conn = DBConnectionOracle.getConnection();    
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);			
	        pstmt.executeUpdate();
			
	}catch(Exception e){
		  System.out.println("에러:"+e);
	}finally {
		  try {
			    if(pstmt!=null) pstmt.close();
			    if(conn!=null)conn.close();
		  }catch(Exception e) {
			  throw new RuntimeException(e.getMessage());
		  }
	 } 	
		
	} // 
	
}
