package servlets;

import service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ajax/sign_up")
public class AjaxSignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext servletContext = req.getServletContext();
        UserService userService = (UserService) servletContext.getAttribute("userService");

        String username = req.getParameter("login");
        String email = req.getParameter("email");

        resp.setContentType("text/plain");

        if (username != null && userService.getByUsername(username) != null) {
            resp.getWriter().write("Такой логин уже существует");
            return;
        }

        if (email != null && userService.getByEmail(email) != null) {
            resp.getWriter().write("Эта почта уже используется");
            return;
        }

        resp.getWriter().write("");
    }
}
