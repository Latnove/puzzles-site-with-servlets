package listener;

import com.cloudinary.Cloudinary;
import com.cloudinary.Configuration;
import com.zaxxer.hikari.HikariDataSource;
import freemarker.core.HTMLOutputFormat;
import freemarker.template.TemplateExceptionHandler;
import service.*;
import utils.CloudinaryUtil;
import utils.HikariDatabaseUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        try {
            HikariDataSource ds = HikariDatabaseUtil.create(System.getenv("PUZZLE_DB_URL"),
                    System.getenv("PUZZLE_DB_USERNAME"),
                    System.getenv("PUZZLE_DB_PASSWORD"));
            Cloudinary cloudinary = CloudinaryUtil.getCloudinary(System.getenv("PUZZLE_CLOUDINARY_NAME"), System.getenv("PUZZLE_CLOUDINARY_KEY"), System.getenv("PUZZLE_CLOUDINARY_SECRET"));

            UserService userService = new UserService(ds, cloudinary);
            AuthorizationService authorizationService = new AuthorizationService(userService);
            CategoryService categoryService = new CategoryService(ds);
            PuzzleService puzzleService = new PuzzleService(ds, cloudinary, categoryService, userService);
            ReviewService reviewService = new ReviewService(ds);

            servletContext.setAttribute("dataSource", ds);
            servletContext.setAttribute("authService", authorizationService);
            servletContext.setAttribute("userService", userService);
            servletContext.setAttribute("puzzlesService", puzzleService);
            servletContext.setAttribute("categoryService", categoryService);
            servletContext.setAttribute("reviewService", reviewService);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize HikariCP", e); // Прекратить загрузку приложения
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HikariDatabaseUtil.close();
    }
}
