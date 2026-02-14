package servlets;

import entity.User;
import service.AuthorizationService;
import service.UserService;
import utils.PasswordUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name="SignUp", urlPatterns = "/sign_up")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/sign_up.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServletContext servletContext = req.getServletContext();
        AuthorizationService authorizationService = (AuthorizationService) servletContext.getAttribute("authService");

        String username = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        List<String> errors = authorizationService.validateAndRegister(username, password, email);

        if (errors.isEmpty()) {
            User user = authorizationService.authorize(username, password);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);
            httpSession.setMaxInactiveInterval(60 * 60);
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(cookie);

            resp.sendRedirect(req.getContextPath() + "/index");
        } else {
            req.setAttribute("errorMessages", errors.getFirst());
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            req.getRequestDispatcher("/templates/sign_up.ftl").forward(req, resp);
        }
    }
}
