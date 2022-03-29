package levi9.UserService.filter;

import levi9.UserService.config.HttpSessionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class SimpleAuthFilter {
    private final HttpSessionConfig httpSessionConfig;

    @Autowired
    public SimpleAuthFilter(HttpSessionConfig httpSessionConfig) {
        this.httpSessionConfig = httpSessionConfig;
    }

    public static boolean isTokenValidInCurrentSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String token = request.getHeader("Authorization");
        if (session == null || !session.getAttribute("token").toString().equals(token)) {
            return false;
        }
        return true;
    }

    public boolean isTokenValidInAnySession(String token) {
        for (HttpSession httpSession :
                httpSessionConfig.getActiveSessions()) {
            if (httpSession.getAttribute("token").toString().equals(token))
                return true;
        }
        return false;
    }
}
