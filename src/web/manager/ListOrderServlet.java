package web.manager;

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
 * Created by Zed on 2017/11/30.
 */
public class ListOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        BusinessService service = new BusinessServiceImpl();
        List<Order> orders = service.listOrder(state);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/manager/listorder.jsp").forward(request,response);
    }
}
