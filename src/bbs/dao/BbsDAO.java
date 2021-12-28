package bbs.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bbs.model.BbsDTO;
import jdbc.DBConnectionOracle;
import jdbc.DBUtil;

public class BbsDAO {
	
	private static BbsDAO instance;
	private BbsDAO() {}
	public static BbsDAO getInstance() {
		if(instance==null) instance = new BbsDAO();
		return instance;
	}
	
	/**
	 * 글 조회수 증가 처리
	 */
	public void updateBbsReadcount(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update bbs set readCount = readCount+1 where num = ?";
		try {
			conn = DBConnectionOracle.getConnection();	
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("에러: " + e);
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	} //
	
	/**
	 * 전체 게시글 조회
	 */
	public List<BbsDTO> getBbsList(int pageNum, int limit, String items, String text) {
		 List<BbsDTO> bbslist = new ArrayList<>();
		  Connection conn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		 
		  String sql="";
		  
			  if((items==null && text==null)||( items.length()==0 || text.length()==0)) {//검색 조건이 파라미터로 넘어오지 않은 경우
				  sql = "select * "
						    + " from "
							+ "(select rownum rn, a.* from "
							+ " (select * "
							+ "    from bbs "
							+ "    order by ref desc, re_step asc)a ) "
							+ "where rn between ? and ? ";	
					
				}else { //검색 조건이 파라미터로 넘어온 경우 
					sql = "select * from "
						+ " (select rownum rn, a.* from "
						+ "  (select * "	
						+ "     from bbs "
						+ "    where "+items+" like '%'||?||'%' " //|| : 
						+ "    order by ref desc, re_step asc) a) "
						+ " where rn between ? and ?";
				 }
				System.out.println("sql:"+sql);
				
				//페이지 번호로 해당 페이지의 시작 글번호와 끝 글번호 구하기
				int start = (pageNum-1)*limit; //예)3페이지 -> (3-1)*10=20, 1페이지 ->0
				int index = start +1; //예)index=21, 1
				int end = index +9; //예)21+9=30, 1+9=10
				
				System.out.println("index:"+index);
				System.out.println("end:"+end);
				
				try {
					//db커넥션설정
					conn = DBConnectionOracle.getConnection();
					if((items==null && text==null)||( items.length()==0 || text.length()==0)) {
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, index);
						pstmt.setInt(2, end);
					}else {
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, text);
						pstmt.setInt(2, index);
						pstmt.setInt(3, end);
					}
					rs = pstmt.executeQuery();
					while(rs.next()) {
						BbsDTO bbs = new BbsDTO();
						bbs.setNum(rs.getInt(2));
						bbs.setWriter(rs.getString(3));
						bbs.setSubject(rs.getString(4));
						bbs.setContent(rs.getString(5));
						bbs.setReadCount(rs.getInt(6));
						bbs.setPassword(rs.getString(7));
						bbs.setReg_date(rs.getString(8));
						bbs.setIp(rs.getString(9));
						bbs.setRef(rs.getInt(10));
						bbs.setRe_step(rs.getInt(11));
						bbs.setRe_level(rs.getInt(12));
						
						//리스트에 추가
						bbslist.add(bbs);
					}
		  }catch(Exception e) {
			  System.out.println("에러:"+e);
		  }finally {
			  try {
				    if(rs!=null) rs.close(); if(pstmt!=null) pstmt.close();
				    if(conn!=null)conn.close();
			  }catch(Exception e) {
				  throw new RuntimeException(e.getMessage());
			  }
		  }
		return bbslist;   
	  } //
	
	/**
	 * 게시글 등록
	 */
	public void insertBbs(BbsDTO bbs) {
		 Connection conn=null;
		 PreparedStatement pstmt=null;
		 String sql="";
		 String updateSql="";
		 if(bbs.getRef()==0) {
			 //원글 신규입력
		   sql ="insert into bbs(writer,subject,content, password,ip,ref,re_step,re_level) "//ref쪽 sequence만들어서 처리
				    +" values (?,?,?,?,?,ref(시퀀스),?,?)";
		 }else {
			 //원글중에 댓글이 있으면, 신규 댓글 입력 전에, 
			 //등록하려는 댓글과 같은 ref 그룹의 기존 댓글의 스텝을 1씩 증가 처리 
			 updateSql="update bbs set re_setp=re_step+1 where ref=? and re_step > ? ";
			 
			//원글에 대한 댓글 입력
		   sql ="insert into bbs(writer,subject,content, password,ip,ref,re_step,re_level) "
					    +" values (?,?,?,?,?,?,?,?)";
		 }
		 
		 try { //신규글 등록 처리
			   conn = DBConnectionOracle.getConnection();
			 if(bbs.getRef()==0) {
			   pstmt =conn.prepareStatement(sql);
			   pstmt.setString(1, bbs.getWriter());
			   pstmt.setString(2, bbs.getSubject());
			   pstmt.setString(3, bbs.getContent());
			   pstmt.setString(4, bbs.getPassword());
			   pstmt.setString(5, bbs.getIp());
			   //신규 등록시 글번호=ref, re_step=0, re_level=0
			   pstmt.setInt(6, 0);//신규등록시 re_step=0,
			   pstmt.setInt(7, 0);//신규등록시 re_level=0
			  
			   pstmt.executeUpdate();
			}else {
			   //기존 댓글 update처리
			  pstmt = conn.prepareStatement(updateSql);	
			  pstmt.setInt(1, bbs.getRef());
			  pstmt.setInt(2, bbs.getRe_step());
			  
			  //update처리
			  pstmt.executeUpdate();
			  
			  //댓글 입력 처리
			  pstmt = conn.prepareStatement(sql);
			  
			  pstmt.setString(1, bbs.getWriter());
			  pstmt.setString(2, bbs.getSubject());
			  pstmt.setString(3, bbs.getContent());
			  pstmt.setString(4, bbs.getPassword());
			  pstmt.setString(5, bbs.getIp());
			   //신규 등록시 글번호=ref, re_step=0, re_level=0
			  pstmt.setInt(6, bbs.getRef());
			  pstmt.setInt(7, bbs.getRe_step()+1);//댓글등록시 re_step=re_step+1,
			  pstmt.setInt(8, bbs.getRe_level()+1);//댓글등록시 re_level=re_level+1
			  
			  pstmt.executeUpdate();
			}
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
	 }
	
	/**
	 * 게시글 수정
	 */
	public void updateBbs(BbsDTO bbs) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		String sql = "update bbs set writer = ?, subject = ?, content = ?, ip = ? where num = ?";
	
		try {
			conn=DBConnectionOracle.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, bbs.getWriter());
			pstmt.setString(2, bbs.getSubject());
			pstmt.setString(3, bbs.getContent());
			pstmt.setString(4, bbs.getIp());
			pstmt.setInt(5, bbs.getNum());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("에러:"+e);
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	} //
	
	/**
	 * Bbs 삭제 처리
	 */
	public void deleteBbs(int num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		//댓글이 존재하는 글은 삭제하지 않음
		String replyCountSql = "select count(*) from bbs where ref=? and re_step>0";
		String sql = "delete from bbs where num = ?";
		
		try {
			conn=DBConnectionOracle.getConnection();
			
			pstmt=conn.prepareStatement(replyCountSql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			int replyCount=0;
			if(rs.next()) replyCount=rs.getInt(1); //댓글갯수 가져오기
			
			if(replyCount==0) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
			}
		}catch(Exception e) {
			System.out.println("에러:"+e);
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	} //
	
	/**
	 * 첫번째 글 가져오기
	 */
	public int getFirstNum() {
		 int minNum =0;
		 Connection conn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		 
		  String sql="select nvl(min(num),0) from bbs";	
		try {
			conn=DBConnectionOracle.getConnection();
		    pstmt=conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 if(rs.next()) minNum = rs.getInt(1);
		   }catch(Exception e) {
			  System.out.println("에러:"+e);
		  }finally {
			  try {
				    if(rs!=null) rs.close(); if(pstmt!=null) pstmt.close();
				    if(conn!=null)conn.close();
			  }catch(Exception e) {
				  throw new RuntimeException(e.getMessage());
			  }
		  } 
		return minNum;
	} //
	
	/**
	 * 마지막 글 가져오기
	 */
	public int getLastNum() {
		 int maxNum =0;
		 Connection conn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		  
		  String sql="select nvl(max(num),0) from bbs ";	
		try {
			 conn = DBConnectionOracle.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 if(rs.next()) maxNum = rs.getInt(1);
		   }catch(Exception e) {
			  System.out.println("에러:"+e);
		  }finally {
			  try {
				    if(rs!=null) rs.close(); if(pstmt!=null) pstmt.close();
				    if(conn!=null)conn.close();
			  }catch(Exception e) {
				  throw new RuntimeException(e.getMessage());
			  }
		  } 
		return maxNum;
	 } //
	
	/**
	 * 게시글 검색 
	 */
	public int getBbsCount(int pageNum, int limit, String items, String text){
		 int count =0;
		 Connection conn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		 
		  String sql="";
		  
			  if((items==null && text==null)||( items.length()==0 || text.length()==0)) {//검색 조건이 파라미터로 넘어오지 않은 경우
					sql = "select count(*) from bbs";	
				}else { //검색 조건이 파라미터로 넘어온 경우 
					sql = "select count(*) "
						+ "  from bbs "
						+ " where "+items+" like '%'||?||'%' " ;
				 }
				System.out.println("sql:"+sql);
				
				//페이지 번호로 해당 페이지의 시작 글번호와 끝 글번호 구하기
				int start = (pageNum-1)*limit; //예)3페이지 -> (3-1)*10=20, 1페이지 ->0
				int index = start +1; //예)index=21, 1
				int end = index +9; //예)21+9=30, 1+9=10
				
				try {
					//1.OracleDB 연결객체 생성
					conn = DBConnectionOracle.getConnection();
					if((items==null && text==null)||( items.length()==0 || text.length()==0)) {
						pstmt = conn.prepareStatement(sql);
					}else {
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, text);
					}
					rs = pstmt.executeQuery();
					if(rs.next()) {
						count = rs.getInt(1);
					}
		  }catch(Exception e) {
			  System.out.println("에러:"+e);
		  }finally {
			  try {
				    if(rs!=null) rs.close(); if(pstmt!=null) pstmt.close();
				    if(conn!=null)conn.close();
			  }catch(Exception e) {
				  throw new RuntimeException(e.getMessage());
			  }
		  } 
		 return count;
	 }
	
	/**
	 * 글번호에 해당하는 게시글 정보 얻기 
	 */
	 public BbsDTO getBbsByNum(int num,int pageNum) {
	  BbsDTO bbs =null;
	  Connection conn=null;
	  PreparedStatement pstmt=null;
	  ResultSet rs=null;
	 
	  String sql="select * from bbs where num=?";
	 
	  System.out.println("sql:"+sql);
	  
	  //조회수 증가 처리
	   // updateBbsReadcount(num);
	   
			try {
				//1.OracleDB 연결객체 생성
				conn = DBConnectionOracle.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
				bbs = new BbsDTO();
				bbs.setNum(rs.getInt(1));
				bbs.setWriter(rs.getString(2));
				bbs.setSubject(rs.getString(3));
				bbs.setContent(rs.getString(4));
				bbs.setReadCount(rs.getInt(5));
				bbs.setPassword(rs.getString(6));
				bbs.setReg_date(rs.getString(7));
				bbs.setIp(rs.getString(8));
				bbs.setRef(rs.getInt(9));
				bbs.setRe_step(rs.getInt(10));
				bbs.setRe_level(rs.getInt(11));				
				}
	  }catch(Exception e) {
		  System.out.println("에러:"+e);
	  }finally {
		  try {
			    if(rs!=null) rs.close(); if(pstmt!=null) pstmt.close();
			    if(conn!=null)conn.close();
		  }catch(Exception e) {
			  throw new RuntimeException(e.getMessage());
		  }
	  } 
	  return bbs;
	 }
	
}
