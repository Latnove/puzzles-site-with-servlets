package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Error page", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");

        String pageTitle = "Произошла ошибка";
        String errorMessage = "Произошла непредвиденная ошибка.";

        if (statusCode != null) {
            switch (statusCode) {
                case 404:
                    pageTitle = "Страница не найдена";
                    errorMessage = "К сожалению, запрошенная страница не найдена.";
                    break;
                case 403:
                    pageTitle = "Доступ запрещен";
                    errorMessage = "У вас нет прав для доступа к этой странице.";
                    break;
                case 500:
                    pageTitle = "Внутренняя ошибка сервера";
                    errorMessage = "На сервере произошла внутренняя ошибка, попробуйте снова.";
                    if (throwable != null) {
                        throwable.printStackTrace();
                    }
                    break;
            }
        }

        req.setAttribute("pageTitle", pageTitle);
        req.setAttribute("statusCode", statusCode);
        req.setAttribute("errorMessage", errorMessage);

        req.getRequestDispatcher("/templates/error.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
