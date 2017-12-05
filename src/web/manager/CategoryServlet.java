package web.manager;

import com.sun.deploy.net.HttpRequest;
import domain.Category;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Zed on 2017/11/29.
 */
public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if(method.equals("add")){
            add(request,response);
        }else if(method.equals("listall")){
            listAll(request,response);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            Category category = new Category();
            category.setName(name);
            category.setDescription(description);
            category.setId(WebUtils.makeID());

            BusinessService service = new BusinessServiceImpl();
            service.addCategory(category);
            request.setAttribute("message", "添加成功");
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("message", "添加失败");
        }

        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BusinessService service = new BusinessServiceImpl();
        List<Category> categoryList = service.getAllCategory();
        request.setAttribute("categories", categoryList);
        request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
    }

}
