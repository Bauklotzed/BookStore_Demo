package test.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Zed on 2017/12/4.
 */
public class AsyncDispatchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final AsyncContext asyncContext = request.startAsync();
        request.setAttribute("mainThread", Thread.currentThread().getName());
        asyncContext.setTimeout(5000);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException e){

                }
                request.setAttribute("workerThread", Thread.currentThread().getName());
                asyncContext.dispatch("/test/threadNames.jsp");
            }
        });

    }
}
