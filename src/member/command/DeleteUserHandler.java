package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDTO;
import member.service.DeleteUserService;
import member.service.exception.NotExistException;
import member.service.exception.NotMatchPasswordException;
import mvc.command.CommandHandler;

public class DeleteUserHandler implements CommandHandler {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String password = request.getParameter("password");
		MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");

		DeleteUserService deleteUserService = new DeleteUserService();
		
		try {
			System.out.println(password);
			System.out.println(member.getId());
			deleteUserService.deleteUser(member.getId(), password);
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return null;
		} catch (NotExistException | NotMatchPasswordException e) {
			request.setAttribute("msg","정확한 비밀번호를 입력해주세요.");
			return "deleteForm.jsp";
		}
	}
	
}
