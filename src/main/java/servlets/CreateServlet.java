package servlets;

import dto.CreatePuzzleDto;
import entity.Category;
import entity.Puzzle;
import entity.User;
import service.CategoryService;
import service.PuzzleService;
import service.ReviewService;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet(name = "Create", urlPatterns = "/create")
public class CreateServlet extends HttpServlet {
    PuzzleService puzzleService = null;
    CategoryService categoryService = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext servletContext = getServletContext();

        puzzleService = (PuzzleService) servletContext.getAttribute("puzzlesService");
        categoryService = (CategoryService) servletContext.getAttribute("categoryService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();

        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/templates/create.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String title = req.getParameter("title");
        Integer pieceCols = Integer.parseInt(req.getParameter("cols"));
        Integer pieceRows =  Integer.parseInt(req.getParameter("rows"));
        Integer userId = user.getId();
        List<Category> categories = categoryService.getAllCategoriesByNames(req.getParameterValues("categories"));
        Puzzle puzzle = puzzleService.createPuzzle(new CreatePuzzleDto(title, pieceCols, pieceRows, userId, categories), req.getPart("file"));

        if (puzzle == null) {
            req.setAttribute("errorMessage", "Не удалось создать пазл :(");
        } else {
            req.setAttribute("successMessage", "Вы успешно создали пазл, для просмотра статуса перейдите в раздел (Профиль -> Созданные)");
        }

        List<Category> allCategories = categoryService.getAllCategories();
        req.setAttribute("categories", allCategories);
        req.getRequestDispatcher("/templates/create.ftl").forward(req, resp);
    }
}