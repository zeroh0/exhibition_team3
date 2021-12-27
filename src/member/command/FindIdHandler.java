package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.a.NativeConstants.StringLengthDataType;

import member.model.MemberDTO;
import member.model.UserDTO;
import member.service.FindIdService;
import member.service.exception.NotExistException;
import mvc.command.CommandHandler;

public class FindIdHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/member/findIdForm.jsp";

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1+"@"+email2;
		
		FindIdService findIdService = new FindIdService();
		
		try {
			MemberDTO member = findIdService.findId(name, email);
			request.setAttribute("findId", member.getId());
			return "/member/findIdSuccess.jsp";
		} catch (NotExistException e) {
			request.setAttribute("msg", "존재하지 않는 계정입니다.");
			return FORM_VIEW;
		}
		
	}
	
}
