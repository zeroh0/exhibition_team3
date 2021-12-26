package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
    public String action(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
