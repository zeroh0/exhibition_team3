package member.command;

import member.model.JoinDTO;
import member.model.MemberDTO;
import member.service.UpdateUserService;
import member.service.exception.NotEqualsSessionID;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserHandler implements CommandHandler {
	
    @Override
    public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JoinDTO updateUserDTO = new JoinDTO();
        updateUserDTO.setId(request.getParameter("id"));
        updateUserDTO.setPassword(request.getParameter("password"));
        updateUserDTO.setEmail1(request.getParameter("email1"));
        updateUserDTO.setEmail2(request.getParameter("email2"));
        updateUserDTO.setPhone1(request.getParameter("phone1"));
        updateUserDTO.setPhone2(request.getParameter("phone2"));
        updateUserDTO.setPhone3(request.getParameter("phone3"));
        updateUserDTO.setZipcode(request.getParameter("zipcode"));
        updateUserDTO.setRoadAddress(request.getParameter("roadAddress"));
        updateUserDTO.setJibunAddress(request.getParameter("jibunAddress"));
        updateUserDTO.setDetailAddress(request.getParameter("detailAddress"));
        updateUserDTO.setExtraAddress(request.getParameter("extraAddress"));
        MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");

        UpdateUserService updateUserService = new UpdateUserService();
        try {
            updateUserService.updateUser(updateUserDTO, member.getId());
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return null;
        } catch (NotEqualsSessionID e) {
        	request.setAttribute("msg", "현재 접속한 아이디와 다른 아이디입니다.");
            return "/index.jsp";
        }

    }
}
