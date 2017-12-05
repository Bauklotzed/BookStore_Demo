package web.client;

import domain.Order;
import service.BusinessService;
import service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Zed on 2017/11/28.
 */
public class ClientListOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        BusinessService service = new BusinessServiceImpl();
        List<Order> orders = service.clientListOrder(userid);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/client/clientlistorder.jsp").forward(request, response);
    }
}
