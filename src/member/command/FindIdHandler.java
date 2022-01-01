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
	
	private static final String FORM_VIEW = "./member/findIdForm.jsp?error=";
	private final String exist = "0";
	private final String notExist = "1";


	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1+"@"+email2;
		
		FindIdService findIdService = new FindIdService();
		
		try {
			MemberDTO member = findIdService.findId(name, email);
			request.getSession().setAttribute("findId", member.getId()); // session으로 하면 안되는 거 같지만? 일단 작동만 하도록 세션으로 설정해놨다..
			response.sendRedirect(FORM_VIEW + exist); // sendRedirect가 아닌 다른 방식으로 하면 레이아웃, 디자인이 적용이 안된다... 우엑  🤯 (비밀번호 찾기도 마찬가지)
			return null;
		} catch (NotExistException e) {
			response.sendRedirect(FORM_VIEW + notExist);
			return null;
		}
		
	}
	
}
