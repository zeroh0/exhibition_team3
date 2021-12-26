package member.command;

import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 로그아웃
 */

public class LogoutHandler implements CommandHandler {
    @Override
    public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "/index.jsp";
    }
}
