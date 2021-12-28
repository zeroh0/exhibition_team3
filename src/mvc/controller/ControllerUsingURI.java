package mvc.controller;

import mvc.command.CommandHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ControllerUsingURI extends HttpServlet {

    private Map<String, CommandHandler> commandMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        String configFile = getInitParameter("configFile");
        Properties prop = new Properties();
        String configFilePath = getServletContext().getRealPath(configFile);
        try (FileReader fis = new FileReader(configFilePath)) {
            prop.load(fis);
            Iterator keyItor = prop.keySet().iterator();
            while (keyItor.hasNext()) {
                String command = (String) keyItor.next();
                String className = prop.getProperty(command);
                Class<?> action = Class.forName(className);
                CommandHandler actionCommand = (CommandHandler) action.newInstance();
                commandMap.put(command, actionCommand);
            }
        } catch (Exception e) {
            System.out.println("에러Controller: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        action(request, response);
    }

    private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURL = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        String queryString = request.getQueryString() == null ? "" : request.getQueryString();//get방식일때 쿼리스트링 얻기

        CommandHandler commandAction = commandMap.get(command);
        String viewPage=null;
        try {
            viewPage = commandAction.action(request, response);//다형성을 이용한 메소드 실행
        }catch(Exception e) {
            throw new ServletException(e);
        }
        if(viewPage != null) {
            //forward처리
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
