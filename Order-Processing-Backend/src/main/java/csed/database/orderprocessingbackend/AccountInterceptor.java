package csed.database.orderprocessingbackend;


import csed.database.orderprocessingbackend.service.ActiveUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AccountInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(AccountInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String sessionId = request.getParameter("sessionId");
        String[] user = ActiveUserService.getInstance().checkLogin(sessionId);
        logger.info(user[1]);
        if(user[1] == null) {
            logger.info("User is not logged in");
            response.setStatus(401);
            return false;
        }
        if(!user[1].equals("manager")) {
            logger.info("User is not manager");
            response.setStatus(401);
            return false;
        }
        return true;
    }

}

