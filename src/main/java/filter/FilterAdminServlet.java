package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class FilterAdminServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) req;
        HttpServletResponse resp = (HttpServletResponse) res;
        String role = (String) rq.getSession().getAttribute("role");
        if ("admin".equals(role)) {
            chain.doFilter(rq, resp);
        } else {
            resp.sendRedirect("/");
        }
    }
}
