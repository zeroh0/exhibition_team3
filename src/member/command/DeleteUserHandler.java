package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDTO;
import member.service.DeleteUserService;
import member.service.exception.NotExistException;
import member.service.exception.NotMatchPasswordException;
import mvc.command.CommandHandler;

public class DeleteUserHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/member/deleteForm.jsp";

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");

		DeleteUserService deleteUserService = new DeleteUserService();
		
		try {
			deleteUserService.deleteUser(member.getId());
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return null;
		} catch (NotExistException e) {
			request.setAttribute("msg","존재하지 않는 아이디입니다.");
			return FORM_VIEW;
		}
	}
	
}
