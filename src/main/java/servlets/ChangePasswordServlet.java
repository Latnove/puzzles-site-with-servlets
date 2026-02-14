package servlets;

import dto.UserDto;
import entity.User;
import service.UserService;
import utils.PasswordUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet( urlPatterns = "/change_password")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServletContext servletContext = req.getServletContext();
        UserService userService = (UserService) servletContext.getAttribute("userService");
        String newPassword = req.getParameter("password");
        String oldPassword = req.getParameter("oldPassword");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        List<String> errors = userService.changePassword(user.getUsername(), oldPassword,newPassword);

        if (errors.isEmpty()) {
            user.setPasswordHash(PasswordUtil.encrypt(newPassword));

            UserDto userDto =  new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getImage(), user.getRole(), user.getCreatedAt());
            req.setAttribute("user", userDto);
            resp.sendRedirect(req.getContextPath() + "/settings");
        } else {
            UserDto userDto =  new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getImage(), user.getRole(), user.getCreatedAt());

            req.setAttribute("user", userDto);
            req.setAttribute("oldPassword", oldPassword);
            req.setAttribute("passwordError", errors.getFirst());
            req.getRequestDispatcher("/templates/settings.ftl").forward(req, resp);
        }
    }
}
