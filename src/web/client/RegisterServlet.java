package web.client;

import domain.User;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Zed on 2017/11/29.
 */
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String cellphone = request.getParameter("cellphone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            User user = new User();
            user.setAddress(address);
            user.setCellphone(cellphone);
            user.setEmail(email);
            user.setId(WebUtils.makeID());
            user.setPassword(password);
            user.setPhone(phone);
            user.setUsername(username);

            BusinessService service = new BusinessServiceImpl();
            service.registerUser(user);
            request.setAttribute("message", "注册成功");
            request.getRequestDispatcher("/message.jsp").forward(request,response);//这里要跳转到首页，并且显示欢迎您，，，待修改

        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("message", "注册失败");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
