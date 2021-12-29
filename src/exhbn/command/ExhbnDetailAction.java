package exhbn.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exhbn.dao.ExhbnDAO;
import exhbn.model.ExhbnDTO;
import mvc.command.CommandHandler;

public class ExhbnDetailAction implements CommandHandler {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//DB저장 객체 생성 
		ExhbnDAO dao = ExhbnDAO.getInstance();
		//전시 id받아오기
		int e_id = Integer.parseInt(request.getParameter("e_id"));
		
		
		ExhbnDTO dto = new ExhbnDTO();
		//아이디를 넘겨서 DB로부터 정보 얻어오기 
		dto=dao.detailView(e_id);
		
		request.setAttribute("dto", dto);
		
		List<ExhbnDTO> dtoList = new ArrayList<ExhbnDTO>();
		dtoList=dao.otherExh(e_id);
		request.setAttribute("dtoList", dtoList);
		
		return "/exhbn/exhbnDetail.jsp";
	}

}