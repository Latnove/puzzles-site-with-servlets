package servlets;

import dto.PuzzleDto;
import entity.User;
import service.PuzzleService;
import service.ReviewService;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Play puzzle", urlPatterns = "/play/*")
public class PlayServlet extends HttpServlet {
    PuzzleService puzzleService = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext servletContext = getServletContext();

        puzzleService = (PuzzleService) servletContext.getAttribute("puzzlesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        User user = (User) req.getSession().getAttribute("user");

        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            Integer puzzleId = Integer.parseInt(pathInfo.substring(1));

            PuzzleDto puzzleDto = puzzleService.getPuzzleById(puzzleId);

            if (puzzleDto == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            } else if (!puzzleDto.getStatus().equals("active") && (user == null || !user.getRole().equals("admin"))) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            } else {
                req.setAttribute("puzzle", puzzleDto);
                req.getRequestDispatcher("/templates/play.ftl").forward(req, resp);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            User user = (User) req.getSession().getAttribute("user");
            Integer puzzleId = Integer.parseInt(pathInfo.substring(1));

            PuzzleDto puzzleDto = puzzleService.getPuzzleById(puzzleId);

            if (puzzleDto == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            } else {
                if (puzzleDto.getStatus().equals("active")) {
                    if (user != null) {
                        puzzleService.addCompletionPuzzleByUserId(puzzleId, user.getId());
                    } else {
                        puzzleService.addCompletionPuzzleByUserId(puzzleId, null);
                    }
                }

                resp.sendRedirect(req.getContextPath() + "/puzzle/" + puzzleDto.getId());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
