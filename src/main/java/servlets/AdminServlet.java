package servlets;

import dto.PuzzleCountsDto;
import entity.User;
import service.PuzzleService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Admin panel", urlPatterns = "/panel")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();

        PuzzleService puzzleService = (PuzzleService) servletContext.getAttribute("puzzlesService");

        PuzzleCountsDto puzzleCountsDto = puzzleService.getPuzzleCounts();

        req.setAttribute("puzzleCounts", puzzleCountsDto);
        req.getRequestDispatcher("/templates/admin.ftl").forward(req, resp);
    }
}
