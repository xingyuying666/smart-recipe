package world.xyy.component;

import org.springframework.web.servlet.HandlerInterceptor;
import world.xyy.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Login interceptor
 *
 * @author xyy
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * Execute before the target mode is executed
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null) {
            //Not logged in. Return to the login page
            response.sendRedirect("/");
            return false;
        } else {
            //Logged in. Allow passage
            return true;
        }
    }
}
