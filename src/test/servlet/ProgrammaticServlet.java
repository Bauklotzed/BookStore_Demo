package test.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Zed on 2017/12/4.
 */
public class ProgrammaticServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.authenticate(response)){
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("Welcome");
        }else{
            System.out.println("User not authenticated");
        }

    }
}
