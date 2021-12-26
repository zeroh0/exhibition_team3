package member.service;

import jdbc.DBConnection;
import jdbc.DBUtil;
import member.dao.MemberDAO;
import member.model.JoinDTO;
import member.model.MemberDTO;
import member.service.exception.DuplicateIdException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class JoinService {

    MemberDAO memberDAO = MemberDAO.getInstance();

    /**
     * 회원가입
     * 입력 값을 받아서 아이디 중복 체크 후 insert 실행
     */
    public MemberDTO join(JoinDTO join) throws ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // 자동 commit 해제

            MemberDTO member = memberDAO.selectedById(conn, join.getId()); // 아이디 중복 체크
            if (member != null) { // 값이 존재하면 rollback
                DBUtil.rollback(conn);
                throw new DuplicateIdException();
            }

            String email = join.getEmail1() + "@" + join.getEmail2();
            String phone = join.getPhone1() + "-" + join.getPhone2() + "-" + join.getPhone3();
            /* 회원 가입일자 타임스템프 정보 생성 */
            Date currentDatetime = new Date(System.currentTimeMillis());
            java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());

            MemberDTO newMember = new MemberDTO( // 입력받은 값 가공 후 insert
                    join.getId(),
                    join.getPassword(),
                    join.getName(),
                    email,
                    phone,
                    join.getZipcode(),
                    join.getRoadAddress(),
                    join.getJibunAddress(),
                    join.getDetailAddress(),
                    join.getExtraAddress(),
                    timestamp
            );
            memberDAO.insert(conn, newMember);

            conn.commit(); // commit
            return newMember;
        } catch (SQLException e) {
            DBUtil.rollback(conn);
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn);
        }
    } //
}
