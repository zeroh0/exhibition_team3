package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.DBConnection;
import jdbc.DBUtil;
import member.dao.MemberDAO;
import member.model.MemberDTO;
import member.service.exception.NotExistException;
import member.service.exception.NotMatchPasswordException;

public class DeleteUserService {
	MemberDAO memberDAO = MemberDAO.getInstance();
	
	public void deleteUser(String sessionID) throws ClassNotFoundException {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			MemberDTO member = memberDAO.selectedById(conn, sessionID);
			if (member == null) {
				DBUtil.rollback(conn);
				throw new NotExistException();
			}
			
			memberDAO.deleteUser(conn, member);
			
			conn.commit();
		} catch (SQLException e) {
			DBUtil.rollback(conn);
            throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn);
		}
	}
}
