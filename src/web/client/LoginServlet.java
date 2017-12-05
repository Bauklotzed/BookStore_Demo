package web.client;

import domain.User;
import service.BusinessService;
import service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Zed on 2017/11/27.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        BusinessService service = new BusinessServiceImpl();
        User user = service.userLogin(username, password);
        if(user == null){
            request.setAttribute("message", "登陆失败!");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher("/client/head.jsp").forward(request, response);
    }
}
