package servlets;

import entity.Puzzle;
import entity.User;
import service.PuzzleService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (name = "Remove puzzle", urlPatterns = "/remove_puzzle")
public class RemovePuzzleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String puzzleIdStr = req.getParameter("puzzleId");

        try {
            Integer puzzleId = Integer.parseInt(puzzleIdStr);
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            ServletContext servletContext = req.getServletContext();
            PuzzleService puzzleService = (PuzzleService) servletContext.getAttribute("puzzlesService");

            if (puzzleService.getPuzzleById(puzzleId) == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            } else if (user == null || !user.getRole().equals("admin")) {
                resp.sendRedirect(req.getContextPath() + "/puzzle/" + puzzleId);
            } else {
                puzzleService.removePuzzleById(puzzleId);
                resp.sendRedirect(req.getContextPath() + "/panel");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
