package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/user/*")
public class FilterUser implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest rqst = (HttpServletRequest) req;
        HttpServletResponse resp = (HttpServletResponse) res;
        String role = (String) rqst.getSession().getAttribute("role");
        if (role.equals("user") | role.equals("admin")) {
            chain.doFilter(rqst, resp);
        } else {
            resp.sendRedirect("/");
        }
    }
}
