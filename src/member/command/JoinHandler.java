package member.command;

import member.model.JoinDTO;
import member.model.MemberDTO;
import member.service.exception.DuplicateIdException;
import member.service.JoinService;
import mvc.command.CommandHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 회원가입
 */

public class JoinHandler implements CommandHandler {

    private static final String FORM_VIEW = "/member/joinForm.jsp";

    @Override
    public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JoinDTO join = new JoinDTO();
        join.setId(request.getParameter("id"));
        join.setPassword(request.getParameter("password"));
        join.setPassword_chk(request.getParameter("password_chk"));
        join.setName(request.getParameter("name"));
        join.setEmail1(request.getParameter("email1"));
        join.setEmail2(request.getParameter("email2"));
        join.setPhone1(request.getParameter("phone1"));
        join.setPhone2(request.getParameter("phone2"));
        join.setPhone3(request.getParameter("phone3"));
        join.setZipcode(request.getParameter("zipcode"));
        join.setRoadAddress(request.getParameter("roadAddress"));
        join.setJibunAddress(request.getParameter("jibunAddress"));
        join.setDetailAddress(request.getParameter("detailAddress"));
        join.setExtraAddress(request.getParameter("extraAddress"));

        JoinService joinService = new JoinService();
        try {
            MemberDTO member = joinService.join(join); //
            request.getSession().setAttribute("member", member); // 로그인 세션 아이디 set
            return "/index.jsp";
        } catch (DuplicateIdException e) {
        	request.setAttribute("msg", "중복된 아이디가 존재합니다.");
            return FORM_VIEW;
        }
    }

}
