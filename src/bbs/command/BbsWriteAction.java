package bbs.command;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import bbs.dao.BbsDAO;
import bbs.model.BbsDTO;
import mvc.command.CommandHandler;

//신규글 등록 & 답변글 등록 둘 다 처리 
public class BbsWriteAction implements CommandHandler {
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 String pageNum=request.getParameter("pageNum");
		 String items=request.getParameter("items");
		 String text = request.getParameter("text");
	int ref=
	    request.getParameter("ref").equals("")?0:Integer.parseInt(request.getParameter("ref"));
	int re_step=
	request.getParameter("re_step").equals("")?0:Integer.parseInt(request.getParameter("re_step"));
	int re_level=
request.getParameter("re_level").equals("")?0:Integer.parseInt(request.getParameter("re_level"));
		
		String writer =request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String reg_date =request.getParameter("reg_date");
		String password =request.getParameter("password");
		String ip = request.getRemoteAddr();
		
		BbsDTO bbs = new BbsDTO();
		bbs.setWriter(writer);
		bbs.setSubject(subject);
		bbs.setContent(content);
		bbs.setPassword(password);
		bbs.setIp(ip);
		//원글의 글 그룹, 스텝,레벨 세팅
		bbs.setRef(ref);
		bbs.setRe_step(re_step);
		bbs.setRe_level(re_level);
		
		//글 등록 처리
		BbsDAO dao = BbsDAO.getInstance();
		
		System.out.println("ref:"+ref+",re_step:"+re_step+",re_level:"+re_level);
		
		dao.insertBbs(bbs);
		
		//글 등록 후 리스트로 이동처리
		return "/BbsListAction.go";
	}

}