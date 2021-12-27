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

    private static final String FORM_VIEW = "loginForm.jsp";

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
        	request.setAttribute("msg", "존재하지 않는 아이디입니다.");
            return FORM_VIEW;
        } catch (NotMatchPasswordException e) {
        	request.setAttribute("msg", "잘못된 비밀번호입니다.");
            return FORM_VIEW;
        }
    }

}
