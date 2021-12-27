package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.DBConnection;
import jdbc.DBUtil;
import member.dao.MemberDAO;
import member.model.MemberDTO;
import member.service.exception.NotExistException;

public class FindIdService {
	
	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	public MemberDTO findId(String name, String email) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			MemberDTO member = memberDAO.selectedByNameEmail(conn, name, email);
			if(member == null) {
				throw new NotExistException();
			}
			return member;
		} finally {
			DBUtil.close(conn);
		}
	} //
	
}
