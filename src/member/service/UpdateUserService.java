package member.service;

import jdbc.DBConnection;
import jdbc.DBUtil;
import member.dao.MemberDAO;
import member.model.JoinDTO;
import member.model.MemberDTO;
import member.service.exception.NotEqualsSessionID;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateUserService {

    private MemberDAO memberDAO = MemberDAO.getInstance();

    public void updateUser(JoinDTO updateUser, String sessionID) throws ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String email = updateUser.getEmail1() + "@" + updateUser.getEmail2();
            String phone = updateUser.getPhone1() + "-" + updateUser.getPhone2() + "-" + updateUser.getPhone3();

            MemberDTO member = memberDAO.selectedById(conn, updateUser.getId());
            if (member != null & member.getId().equals(sessionID)) {
                memberDAO.updateUser(conn, new MemberDTO(
                    updateUser.getId(),
                    updateUser.getPassword(),
                    updateUser.getName(),
                    email,
                    phone,
                    updateUser.getZipcode(),
                    updateUser.getRoadAddress(),
                    updateUser.getJibunAddress(),
                    updateUser.getDetailAddress(),
                    updateUser.getExtraAddress(),
                    null)
                );
            } else {
                DBUtil.rollback(conn);
                throw new NotEqualsSessionID();
            }

            conn.commit();
        } catch (SQLException e) {
            DBUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn);
        }
    }
}
