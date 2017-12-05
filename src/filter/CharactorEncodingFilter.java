package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Zed on 2017/11/30.
 */
public class CharactorEncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //chain.doFilter(req, resp);
        chain.doFilter(new MyRequest(request), resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

class MyRequest extends HttpServletRequestWrapper{

    private HttpServletRequest request;

    public MyRequest(HttpServletRequest request){
        super(request);
        this.request = request;

    }

    @Override
    //http://blog.csdn.net/yangaming/article/details/6913067
    public String getParameter(String name) {
        String value = request.getParameter(name);
        if(value == null){
            return null;
        }
        if(!this.request.getMethod().equalsIgnoreCase("get")){
            return value;
        }
        try{
            value = new String(value.getBytes("ISO8859-1"), "UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return value;
    }
}
