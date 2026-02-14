package servlets;

import dto.PuzzleDto;
import dto.ReviewDto;
import dto.UserDto;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Puzzle", urlPatterns = "/puzzle/*")
public class PuzzleServlet extends HttpServlet {
    PuzzleService puzzleService = null;
    ReviewService reviewService = null;
    UserService userService = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext servletContext = getServletContext();

        puzzleService = (PuzzleService) servletContext.getAttribute("puzzlesService");
        reviewService = (ReviewService) servletContext.getAttribute("reviewService");
        userService = (UserService) servletContext.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String puzzleIdStr = pathInfo.substring(1);

        try {
            Integer puzzleId = Integer.parseInt(puzzleIdStr);
            HttpSession httpSession = req.getSession();
            User user = (User) httpSession.getAttribute("user");

            PuzzleDto puzzle = puzzleService.getPuzzleById(puzzleId);

            if (puzzle == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            if (puzzle.getStatus().equals("pending") || puzzle.getStatus().equals("rejected")) {
                if ((user == null) || (!puzzle.getUserId().equals(user.getId()) && !user.getRole().equals("admin"))) {
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            }

            UserDto creatorUser = userService.getById(puzzle.getUserId());
            ReviewDto reviewDto = null;
            if (user != null) {
                reviewDto = reviewService.getPuzzleReviewByUserId(puzzleId, user.getId());
                req.setAttribute("userRole", user.getRole());
            }
            List<ReviewDto> reviews = reviewService.getAllReviewsByPuzzleId(puzzleId);

            req.setAttribute("creatorUser", creatorUser);
            req.setAttribute("puzzle", puzzle);
            req.setAttribute("review", reviewDto);
            req.setAttribute("reviewsLength", reviews.size());
            req.setAttribute("allStatuses", new ArrayList<>(List.of("active", "pending", "rejected")));

            req.getRequestDispatcher("/templates/puzzle.ftl").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        HttpSession httpSession = req.getSession(false);
        User user = (httpSession != null) ? (User) httpSession.getAttribute("user") : null;

        String ratingStr = req.getParameter("rating");
        String reviewText = req.getParameter("text");

        try {
            Integer puzzleId = Integer.parseInt(pathInfo.substring(1));

            String errorMessage = null;
            if (user == null) {
                errorMessage = "Вам необходимо авторизоваться для отправки отзыва";
            } else if (ratingStr == null || ratingStr.isBlank()) {
                errorMessage = "Рейтинг не может быть пустым";
            } else if (!puzzleService.isCollectPuzzleByUserId(puzzleId, user.getId())) {
                errorMessage = "Вы не можете оставить отзыв, пока не соберете пазл";
            } else if (!puzzleService.getPuzzleById(puzzleId).getStatus().equals("active")) {
                errorMessage = "Вы не можете оставить отзыв, пока статус у пазла не active";
            }

            if (errorMessage != null) {
                req.setAttribute("errorMessage", errorMessage);
                Integer rating = Integer.parseInt(ratingStr);
                req.setAttribute("rating", rating);
                req.setAttribute("text", reviewText);
                doGet(req, resp);
                return;
            }

            Integer rating = Integer.parseInt(ratingStr);
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setPuzzleId(puzzleId);
            reviewDto.setUserId(user.getId());
            reviewDto.setRating(rating);
            reviewDto.setReviewText(reviewText);
            reviewService.saveReview(reviewDto);

            resp.sendRedirect(req.getContextPath() + "/puzzle/" + puzzleId);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "Ошибка отправки, некорректный формат данных.");
            req.setAttribute("rating", ratingStr);
            req.setAttribute("text", reviewText);
            doGet(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Произошла непредвиденная ошибка.");
            req.setAttribute("text", reviewText);
            doGet(req, resp);
        }
    }
}
