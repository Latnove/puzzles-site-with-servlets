package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ReviewDto;
import entity.User;
import service.ReviewService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ajax/reviews")
public class AjaxReviewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringPuzzleId = req.getParameter("puzzleId");
        String stringPage = req.getParameter("page");
        String stringLimit = req.getParameter("limit");

        try {
            Integer puzzleId = Integer.parseInt(stringPuzzleId);
            Integer page = Integer.parseInt(stringPage);
            Integer limit = Integer.parseInt(stringLimit);
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            Integer userId = null;

            if (user != null) {
                userId = user.getId();
            }

            ServletContext servletContext = getServletContext();
            ReviewService reviewService = (ReviewService) servletContext.getAttribute("reviewService");
            List<ReviewDto> reviewDto = reviewService.getReviewsByPuzzleIdIgnoredUserId(puzzleId, userId, page, limit);
            ObjectMapper objectMapper = new ObjectMapper();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), reviewDto);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
