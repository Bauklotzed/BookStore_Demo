package web.client;

import domain.Order;
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
public class ClientListOrderDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderid = request.getParameter("orderid");
        BusinessService service = new BusinessServiceImpl();
        Order order = service.findOrder(orderid);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/client/clientorderdetail.jsp").forward(request,response);
    }
}
