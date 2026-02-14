package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PuzzleDto;
import service.PuzzleService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ajax/puzzles_status")
public class AjaxPuzzlesByStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        String limitStr = req.getParameter("limit");
        String[] status = req.getParameterValues("status");

        try {
            Integer limit = Integer.parseInt(limitStr);
            Integer offset = Integer.parseInt(pageStr) * limit;

            ServletContext servletContext = req.getServletContext();
            PuzzleService puzzleService = (PuzzleService) servletContext.getAttribute("puzzlesService");

            List<PuzzleDto> puzzleDto = puzzleService.getPuzzlesByStatus(status, offset, limit);

            ObjectMapper objectMapper = new ObjectMapper();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), puzzleDto);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при получении данных каталога");
        }
    }
}
