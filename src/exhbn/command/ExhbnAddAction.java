package exhbn.command;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import exhbn.dao.ExhbnDAO;
import exhbn.model.ExhbnDTO;
import mvc.command.CommandHandler;

public class ExhbnAddAction implements CommandHandler {
	
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		 ExhbnDAO dao = ExhbnDAO.getInstance();
		
		 String filename = "";
	     String realFolder = "/Users/zeroh0/Desktop/upload"; //경로 
	     int maxSize = 10 * 1024 * 1024;
	     String encType = "utf-8";
	     
	     //MultipartRequest객체 생성
	     MultipartRequest multi 
	      = new MultipartRequest(request,
	    		                 realFolder,
	    		                 maxSize, 
	    		                 encType, 
	    		                 new DefaultFileRenamePolicy());
	     
		//request로 부터 파라미터 이름에 해당하는 값 얻기
		String title = multi.getParameter("title");
		String category = multi.getParameter("category");
		String description = multi.getParameter("description");
		String price = multi.getParameter("price");
		String location = multi.getParameter("location");
		String period = multi.getParameter("period");
		String time = multi.getParameter("time"); 
		
		String image = request.getParameter("image");
		System.out.println("image:" + image);
		
		
		//전송된 파일정보 얻기
		Enumeration files = multi.getFileNames();
		String fname =(String)files.nextElement();
		String fileName = multi.getFilesystemName(fname);//전송되어서 서버로 넘어온파일명
		   
		ExhbnDTO exhbn = new ExhbnDTO();
		exhbn.setTitle(title); 
		exhbn.setCategory(category);
		exhbn.setDescription(description);
		exhbn.setPrice(price);
		exhbn.setLocation(location);
		exhbn.setPeriod(period);
		exhbn.setTime(time);
		exhbn.setImage(fileName);
		
		dao.insertexhbn(exhbn);
		
		return "/exhbn/exhbnList.jsp";
	}

}
