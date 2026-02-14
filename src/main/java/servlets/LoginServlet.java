package servlets;

import dto.UserDto;
import entity.User;
import service.AuthorizationService;
import service.UserService;
import utils.PasswordUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name="Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        AuthorizationService authorizationService = (AuthorizationService) servletContext.getAttribute("authService");
        String username = req.getParameter("login");
        String password = req.getParameter("password");

        User user = authorizationService.authorize(username, password);

        if (user == null) {
            req.setAttribute("errorMessage", "Логин или пароль неверный");
            req.setAttribute("username", username);
            req.setAttribute("password", password);

            req.getRequestDispatcher("/templates/login.ftl").forward(req, resp);
            return;
        }

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", user);
        httpSession.setMaxInactiveInterval(60 * 60);
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
