package filter;

import entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebFilter(urlPatterns = "/*")
public class AuthorizationFilter extends HttpFilter {
    private static final Map<String, List<String>> accessRules = Map.of(
            "/add", List.of("user"),
            "/panel", List.of("admin"),
            "/profile", List.of("admin", "user"),
            "/logout", List.of("admin", "user"),
            "/create", List.of("admin", "user"),
            "/change_avatar", List.of("admin", "user"),
            "/change_password", List.of("admin", "user"),
            "/remove_review", List.of("admin", "user"),
            "/settings", List.of("admin", "user")
    );

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String path = req.getRequestURI().substring(req.getContextPath().length());

        boolean needsProtection = false;
        for (String restrictedUrl : accessRules.keySet()) {
            if (path.startsWith(restrictedUrl)) {
                needsProtection = true;
                break;
            }
        }

        if (!needsProtection) {
            chain.doFilter(req, res);
            return;
        }

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/index");
            return;
        }

        for (var entry : accessRules.entrySet()) {
            String restrictedUrl = entry.getKey();
            List<String> allowedRoles = entry.getValue();

            if (path.startsWith(restrictedUrl)) {
                if (allowedRoles.contains(user.getRole())) {
                    chain.doFilter(req, res);
                } else {
                    res.sendRedirect(req.getContextPath() + "/index");
                }
                return;
            }
        }

        res.sendRedirect(req.getContextPath() + "/index");
    }
}