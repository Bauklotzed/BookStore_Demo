package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Zed on 2017/12/1.
 */
@WebFilter(filterName = "DownloadFilter")
public class DownloadFilter implements Filter {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Properties downloadLog;
    File logFile;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("Download");
    }

}
