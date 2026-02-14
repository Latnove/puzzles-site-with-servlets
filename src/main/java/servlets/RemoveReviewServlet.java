package servlets;

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

@WebServlet(name = "Remove review", urlPatterns = "/remove_review/*")
public class RemoveReviewServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String reviewIdStr = pathInfo.substring(1);

        try {
            Integer reviewId = Integer.parseInt(reviewIdStr);

            ServletContext servletContext = getServletContext();
            HttpSession httpSession = req.getSession();
            User user = (User) httpSession.getAttribute("user");


            ReviewService reviewService = (ReviewService) servletContext.getAttribute("reviewService");

            ReviewDto reviewDto = reviewService.getReviewById(reviewId);

            if (reviewDto == null || user == null) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            if (reviewDto.getUserId() == user.getId()) {
                reviewService.removeReviewById(reviewDto.getId());
            }

            resp.sendRedirect(req.getContextPath() + "/puzzle/" + reviewDto.getPuzzleId());
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
