package member.dao;

import jdbc.DBUtil;
import member.model.MemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jdt.internal.compiler.ast.TrueLiteral;

public class MemberDAO {
    private static MemberDAO instance ;
    private MemberDAO() {}
    public static MemberDAO getInstance() {
        if(instance == null) instance = new MemberDAO();
        return instance;
    }

    /**
     * 아이디 존재 확인 (아이디 중복체크)
     */
    public MemberDTO selectedById(Connection conn, String id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from member where id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            MemberDTO member = null;
            if (rs.next()) {
                member = new MemberDTO(
                    rs.getString("id"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("zipcode"),
                    rs.getString("roadAddress"),
                    rs.getString("jibunAddress"),
                    rs.getString("detailAddress"),
                    rs.getString("extraAddress"),
                    rs.getTimestamp("register_day")
                );
            }
            return member;
        } finally {
            DBUtil.close(pstmt);
            DBUtil.close(rs);
        }
    } //

    /**
     * JoinService의 join 메소드를 통해서 실행
     * insert into member values (?, ?, ?, ?, ?, ?, ?, ?);
     */
    public void insert(Connection conn, MemberDTO member) throws SQLException {
        try (PreparedStatement pstmt =
                conn.prepareStatement("insert into member values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getEmail());
            pstmt.setString(5, member.getPhone());
            pstmt.setString(6, member.getZipcode());
            pstmt.setString(7, member.getRoadAddress());
            pstmt.setString(8, member.getJibunAddress());
            pstmt.setString(9, member.getDetailAddress());
            pstmt.setString(10, member.getExtraAddress());
            pstmt.setTimestamp(11, member.getRegister_day());
            pstmt.executeUpdate();
        }
    } //

    /**
     * 회원정보 수정 
     * update  
     */
    public void updateUser(Connection conn, MemberDTO member) throws SQLException {
        PreparedStatement pstmt = null;
        String sql = "update member " +
                "set password = ?, email = ?, phone = ?, zipcode = ?, roadAddress = ?, jibunAddress = ?, detailAddress = ?, extraAddress = ? " +
                "where id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getPassword());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getPhone());
            pstmt.setString(4, member.getZipcode());
            pstmt.setString(5, member.getRoadAddress());
            pstmt.setString(6, member.getJibunAddress());
            pstmt.setString(7, member.getDetailAddress());
            pstmt.setString(8, member.getExtraAddress());
            pstmt.setString(9, member.getId());
            pstmt.executeUpdate();
        } finally {
            DBUtil.close(pstmt);
        }
    }
    
    /**
     * 회원 탈퇴
     * delete  
     */
    public void deleteUser(Connection conn, MemberDTO member) throws SQLException {
    	PreparedStatement pstmt = null;
    	String sql = "delete from member where id = ?";
    	try {
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, member.getId());
    		pstmt.executeUpdate();
    	} finally {
			DBUtil.close(pstmt);
		}
    }
    
    /**
     * 아이디 찾기
     */
    public MemberDTO selectedByNameEmail(Connection conn, String name, String email) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from member where name = ? and email = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            rs = pstmt.executeQuery();
            MemberDTO member = null;
            if (rs.next()) {
                member = new MemberDTO(
                    rs.getString("id"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("zipcode"),
                    rs.getString("roadAddress"),
                    rs.getString("jibunAddress"),
                    rs.getString("detailAddress"),
                    rs.getString("extraAddress"),
                    rs.getTimestamp("register_day")
                );
            }
            return member;
        } finally {
            DBUtil.close(pstmt);
            DBUtil.close(rs);
        }
    } //
    
}
