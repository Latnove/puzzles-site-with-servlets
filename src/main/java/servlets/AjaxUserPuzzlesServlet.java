package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CollectPuzzleDto;
import dto.CreatedPuzzleDto;
import entity.User;
import service.PuzzleService;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ajax/user_puzzles")
public class AjaxUserPuzzlesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext servletContext = req.getServletContext();
        PuzzleService puzzleService = (PuzzleService) servletContext.getAttribute("puzzlesService");
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");


        int page = parseIntWithDefault(req.getParameter("page"), 0);
        int limit = parseIntWithDefault(req.getParameter("limit"), 3);
        String requestName = req.getParameter("requestName");
        Integer userId = user.getId();

        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (requestName.equals("collect_puzzles")) {
            List<CollectPuzzleDto> collectPuzzleDto = puzzleService.getCollectPuzzles(userId, page * limit, limit);
            objectMapper.writeValue(resp.getWriter(), collectPuzzleDto);
        } else if (requestName.equals("created_puzzles")) {
            List<CreatedPuzzleDto> createdPuzzleDto = puzzleService.getCreatedPuzzles(userId, page * limit, limit);
            objectMapper.writeValue(resp.getWriter(), createdPuzzleDto);
        }
    }

    private int parseIntWithDefault(String number, int defaultValue) {
        try {
            return number != null ? Integer.parseInt(number) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}