package servlets;

import dto.UserDto;
import entity.User;
import service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/change_avatar")
@MultipartConfig
public class ChangeAvatarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServletContext servletContext = req.getServletContext();
        UserService userService = (UserService) servletContext.getAttribute("userService");
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        String newImageUrl = userService.changeAvatar(user.getUsername(), req.getPart("image"));

        if (newImageUrl == null) {
            UserDto userDto =  new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getImage(), user.getRole(), user.getCreatedAt());
            req.setAttribute("user", userDto);
            req.setAttribute("imageError", "Попробуйте чуть позже");

            req.getRequestDispatcher("/templates/settings.ftl").forward(req, resp);
        } else {
            user.setImage(newImageUrl);
            UserDto userDto =  new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getImage(), user.getRole(), user.getCreatedAt());
            req.setAttribute("user", userDto);
            resp.sendRedirect(req.getContextPath() + "/settings");
        }
    }
}
