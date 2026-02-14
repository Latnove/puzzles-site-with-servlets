package servlets;

import dto.UserDto;
import entity.Category;
import entity.User;
import service.CategoryService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="Index", urlPatterns = {"","/index"})
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        CategoryService categoryService = (CategoryService) servletContext.getAttribute("categoryService");;
        List<Category> categories = categoryService.getAllCategories();

        if (req.getSession().getAttribute("user") != null) {
            User user = (User) req.getSession().getAttribute("user");

            UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getImage(), user.getRole(), user.getCreatedAt());
            req.setAttribute("user", userDto);
        }

        req.setAttribute("categories", categories);

        req.getRequestDispatcher("/templates/index.ftl").forward(req, resp);
    }
}
