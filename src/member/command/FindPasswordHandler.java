package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDTO;
import member.service.FindPasswordService;
import member.service.exception.NotExistException;
import mvc.command.CommandHandler;

public class FindPasswordHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "./member/findPasswordForm.jsp?error=";
	private final String exist = "0";
	private final String notExist = "1";
	
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1 + "@" + email2;
		
		FindPasswordService findPasswordService = new FindPasswordService();
		
		try {
			MemberDTO member = findPasswordService.findPassword(id, email);
			request.getSession().setAttribute("findId", member.getPassword());
			response.sendRedirect(FORM_VIEW + exist);
			return null;
		} catch (NotExistException e) {
			response.sendRedirect(FORM_VIEW + notExist);
			return null;
		}		
	}
	
}
