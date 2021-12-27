package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDTO;
import member.service.FindPasswordService;
import member.service.exception.NotExistException;
import mvc.command.CommandHandler;

public class FindPasswordHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/member/joinForm.jsp";
	
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1 + "@" + email2;
		
		FindPasswordService findPasswordService = new FindPasswordService();
		
		try {
			MemberDTO member = findPasswordService.findPassword(id, email);
			request.setAttribute("findPassword", member.getPassword());
			return "/member/findPasswordSuccess.jsp";
		} catch (NotExistException e) {
			request.setAttribute("msg", "존재하지 않는 계정입니다.");
			return FORM_VIEW;
		}		
	}
	
}
