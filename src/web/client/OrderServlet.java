package web.client;

import domain.Cart;
import domain.User;
import service.BusinessService;
import service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Zed on 2017/11/28.
 */
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            User user = (User)request.getSession().getAttribute("user");
            if(user == null){
                request.setAttribute("message", "对不起，请先登录");
                request.getRequestDispatcher("/message.jsp").forward(request,response);
            }

            Cart cart = (Cart) request.getSession().getAttribute("cart");
            BusinessService service = new BusinessServiceImpl();
            service.createOrder(cart, user);
            request.setAttribute("message", "订单已生成");
            request.getSession().removeAttribute("cart");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("message", "订单生成失败!");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }

    }
}
