package exhbn.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exhbn.dao.ExhbnDAO;
import mvc.command.CommandHandler;

public class ExhbnDeleteAction implements CommandHandler {
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		ExhbnDAO dao = ExhbnDAO.getInstance();
		
		int e_id = Integer.parseInt(request.getParameter("e_id"));
	
		dao.deleteexhbn(e_id);
		
		return "/exhbnListAction.do";
	}
}
