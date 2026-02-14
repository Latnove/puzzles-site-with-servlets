package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CatalogPuzzleDto;
import service.PuzzleService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "Catalog", urlPatterns = "/catalog")
public class AjaxCatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pieceStartStr = req.getParameter("pieceStart");
        String pieceEndStr = req.getParameter("pieceEnd");
        String minRatingStr = req.getParameter("minRating");
        String[] categories = req.getParameterValues("categories");

        String offsetStr = req.getParameter("offset");
        String limitStr = req.getParameter("limit");

        Integer pieceStart = parseIntSafe(pieceStartStr);
        Integer pieceEnd = parseIntSafe(pieceEndStr);
        Integer minRating = parseIntSafe(minRatingStr);
        Integer offset = parseIntSafe(offsetStr, 0);
        Integer limit = parseIntSafe(limitStr, 6);

        try {
            ServletContext servletContext = req.getServletContext();
            PuzzleService puzzleService = (PuzzleService) servletContext.getAttribute("puzzlesService");

            List<CatalogPuzzleDto> catalogPuzzleDto = puzzleService.getCatalogPuzzles(
                    pieceStart, pieceEnd, minRating, categories, offset * limit, limit
            );

            ObjectMapper objectMapper = new ObjectMapper();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), catalogPuzzleDto);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при получении данных каталога");
        }
    }

    private Integer parseIntSafe(String str, Integer defaultValue) {
        Integer result = parseIntSafe(str);
        return result != null ? result : defaultValue;
    }

    private Integer parseIntSafe(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
