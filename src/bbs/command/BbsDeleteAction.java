package bbs.command;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import bbs.dao.BbsDAO;
import mvc.command.CommandHandler;

public class BbsDeleteAction implements CommandHandler {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum=request.getParameter("pageNum");
		String items=request.getParameter("items");
		String text = request.getParameter("text");
		int num = Integer.parseInt(request.getParameter("num"));
		 
			//글 삭제 처리
			BbsDAO dao = BbsDAO.getInstance();
			dao.deleteBbs(num);
	
	   request.setAttribute("pageNum",request.getParameter("pageNum"));
	   request.setAttribute("items",request.getParameter("items"));
	   request.setAttribute("text",request.getParameter("text"));
	
		return "/BbsListAction.go";
	}
}
