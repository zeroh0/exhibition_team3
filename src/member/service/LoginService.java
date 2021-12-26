package member.service;

import jdbc.DBConnection;
import jdbc.DBUtil;
import member.dao.MemberDAO;
import member.model.MemberDTO;
import member.service.exception.NotExistException;
import member.service.exception.NotMatchPasswordException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 로그인
 * 아아디, 비밀번호 체크 후 해당 유저의 아이디, 비밀번호를 리턴
 */

public class LoginService {

    private MemberDAO memberDAO = MemberDAO.getInstance();

    public MemberDTO login(String id, String password) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            MemberDTO member = memberDAO.selectedById(conn, id);
            if (member == null) {
                throw new NotExistException();
            }
            if (!member.matchPassword(password)) {
                throw new NotMatchPasswordException();
            }
            return member;
        } finally {
            DBUtil.close(conn);
        }
    } //

}
