package exhbn.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exhbn.dao.ExhbnDAO;
import exhbn.model.ExhbnDTO;
import mvc.command.CommandHandler;

public class ExhbnListAction implements CommandHandler {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//DB엑세스 객체 생성
		ExhbnDAO dao = ExhbnDAO.getInstance();
		List<ExhbnDTO> exhList = new ArrayList<ExhbnDTO>();
		exhList = dao.getExhList();
		request.setAttribute("exhList", exhList);
		return "/exhbn/exhbnList.jsp";
	}

}
