package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.DBConnection;
import jdbc.DBUtil;
import member.dao.MemberDAO;
import member.model.MemberDTO;
import member.service.exception.NotExistException;

public class FindPasswordService {
	
	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	public MemberDTO findPassword(String id, String email) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			MemberDTO member = memberDAO.selectedById(conn, id);
			if(member == null || !member.getEmail().equals(email)) {
				throw new NotExistException();
			}
			return member;
		} finally {
			DBUtil.close(conn);
		}
	} //
	
}
