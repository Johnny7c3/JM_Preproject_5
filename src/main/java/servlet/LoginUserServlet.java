package servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class LoginUserServlet extends HttpServlet {
    private UserService serv;

    @Override
    public void init() throws ServletException {
        serv = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/UserLoginPage.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User userByEmail = serv.getUserByEmail(email);
        req.setAttribute("status", "Неправильные логин или пароль");
        if (userByEmail != null) {
            if (password.equals(userByEmail.getPassword())) {
                req.getSession().setAttribute("role", userByEmail.getRole());
                req.getSession().setAttribute("userId", userByEmail.getId());
                res.sendRedirect("/");
            } else {
                getServletContext().getRequestDispatcher("/UserLoginPage.jsp").forward(req, res);
            }
        } else {
            getServletContext().getRequestDispatcher("/UserLoginPage.jsp").forward(req, res);
        }
    }
}
