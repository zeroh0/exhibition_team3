package exhbn.command;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import exhbn.dao.ExhbnDAO;
import exhbn.model.ExhbnDTO;
import mvc.command.CommandHandler;

public class ExhbnUpdateAction implements CommandHandler {
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		 ExhbnDAO dao = ExhbnDAO.getInstance();
		
		 String filename="";
	     String realFolder = "/Users/zeroh0/Desktop/upload";//웹 어플리케이션상의 절대 경로
	     int maxSize = 10 * 1024 * 1024;//5mb - 전송될 파일의 최대 크기
	     String encType = "utf-8";
	     
	     //MultipartRequest객체 생성
	     MultipartRequest multi 
	      = new MultipartRequest(request,
	    		                 realFolder,
	    		                 maxSize, 
	    		                 encType, 
	    		                 new DefaultFileRenamePolicy());
	     
		//request로 부터 파라미터 이름에 해당하는 값 얻기
	    int e_id = Integer.parseInt(request.getParameter("e_id"));
	    String title = multi.getParameter("title");
		String category = multi.getParameter("category");
		String description = multi.getParameter("description");
		String price = multi.getParameter("price");
		String location = multi.getParameter("location");
		String period = multi.getParameter("period");
		String time = multi.getParameter("time"); 
		
		String image = request.getParameter("image");
//		System.out.println(image);
		
		
		//전송된 파일정보 얻기
		Enumeration files = multi.getFileNames();
		String fname =(String)files.nextElement();
		String fileName = multi.getFilesystemName(fname);//전송되어서 서버로 넘어온파일명
		   
		
		//insertBoard()메소드에 넘길 객체 생성 후, 속성에 값 설정
		ExhbnDTO exhbn = new ExhbnDTO();
		exhbn.setE_id(e_id);
		exhbn.setTitle(title); 
		exhbn.setCategory(category);
		exhbn.setDescription(description);
		exhbn.setPrice(price);
		exhbn.setLocation(location);
		exhbn.setPeriod(period);
		exhbn.setTime(time);
		exhbn.setImage(fileName);
		
		//DAO에서 DB에 저장하기 위해 메소드 호출
		dao.updateexhbn(exhbn);
		
		return "/exhbn/exhbnList.jsp";
	}
}
