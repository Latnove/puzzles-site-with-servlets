package servlets;

import dto.UserDto;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="Settings", urlPatterns = "/settings")
public class SettingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getImage(), user.getRole(), user.getCreatedAt());
        req.setAttribute("user", userDto);
        req.setAttribute("isSettings", true);
        req.getRequestDispatcher("/templates/settings.ftl").forward(req, resp);
    }
}
