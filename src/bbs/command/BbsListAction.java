package bbs.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.dao.BbsDAO;
import bbs.model.BbsDTO;
import mvc.command.CommandHandler;

public class BbsListAction implements CommandHandler {
	
		static final int LISTCOUNT = 10;
		
		@Override
		public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
			BbsDAO dao = BbsDAO.getInstance();
			List<BbsDTO> bbsList = new ArrayList<BbsDTO>();
			
			int pageNum = 1;
			int limit = LISTCOUNT;
			
			if(request.getParameter("pageNum") != null)
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			
			String items =request.getParameter("items");
			String text = request.getParameter("text");
					
			bbsList = dao.getBbsList(pageNum, limit, items, text);
			System.out.println("bbsList건수:"+bbsList.size());
			int total_record = dao.getBbsCount(pageNum, limit, items, text);
			
			int firstNum = dao.getFirstNum();
			int lastNum = dao.getLastNum();
			
			int total_page;
			
			//페이징처리
			if(total_record % limit == 0) { 
				total_page = total_record/limit;
				Math.floor(total_page);
			}else {                    
				total_page = total_record/limit;
				Math.floor(total_page);
				total_page = total_page +1;
			}
			
			//전체 페이지
			int finalPage = (total_record + (LISTCOUNT - 1))/LISTCOUNT;
			
			//페이지 세그먼트 처리
			int startPage =((pageNum -1)/10)*10 +1;  
			int endPage =startPage + 10 -1;
			
			//페이지 보정
			if(endPage > finalPage) endPage = finalPage;
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("total_page", total_page);
			request.setAttribute("total_record", total_record);
			request.setAttribute("bbslist", bbsList);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("finalPage",finalPage);
			request.setAttribute("items", items);
			request.setAttribute("text", text);
			request.setAttribute("firstNum", firstNum);
			request.setAttribute("lastNum", lastNum);
			
			return "/bbs/list.jsp";
		}

}
