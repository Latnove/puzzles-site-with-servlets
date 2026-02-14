package servlets;

import entity.User;
import service.PuzzleService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ajax/update_puzzle_status")
public class AjaxUpdatePuzzleStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (user == null || !user.getRole().equals("admin")) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("{\"error\": \"Access denied\"}");
            return;
        }

        String status = req.getParameter("status");
        String puzzleIdStr = req.getParameter("puzzleId");

        if (status == null || puzzleIdStr == null || !List.of("active", "pending", "rejected").contains(status)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid parameters\"}");
            return;
        }

        try {
            ServletContext servletContext = req.getServletContext();
            PuzzleService puzzleService = (PuzzleService) servletContext.getAttribute("puzzlesService");

            Integer puzzleId = Integer.parseInt(puzzleIdStr);
            puzzleService.updateStatus(puzzleId, status);

            resp.setStatus(HttpServletResponse.SC_OK);

            String jsonResponse = String.format("{\"success\": true, \"newStatus\": \"%s\"}", status);
            resp.getWriter().write(jsonResponse);

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid puzzle ID format\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"An internal error occurred\"}");
        }
    }
}
