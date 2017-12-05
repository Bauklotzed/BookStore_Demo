package web.client;

import domain.Book;
import domain.Cart;
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
public class BuyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookid = request.getParameter("bookid");
        BusinessService service = new BusinessServiceImpl();
        Book book = service.findBook(bookid);
        System.out.println("ok");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        service.buyBook(cart, book);
        request.getSession().setAttribute("cart", cart);
        request.getRequestDispatcher("/client/listcart.jsp").forward(request, response);
    }
}
