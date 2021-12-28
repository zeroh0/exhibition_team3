package member.command;

import member.model.MemberDTO;
import member.service.exception.NotExistException;
import member.service.LoginService;
import member.service.exception.NotMatchPasswordException;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 로그인
 */

public class LoginHandler implements CommandHandler {

    private static final String FORM_VIEW = "./member/loginForm.jsp?error=1";

    @Override
    public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        LoginService loginService = new LoginService();
        try {
            MemberDTO member = loginService.login(id, password);
            request.getSession().setAttribute("member", member);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return null;
        } catch (NotExistException e) {
        	response.sendRedirect(FORM_VIEW);
        } catch (NotMatchPasswordException e) {
        	response.sendRedirect(FORM_VIEW);
        }
        return null;
    }

}
