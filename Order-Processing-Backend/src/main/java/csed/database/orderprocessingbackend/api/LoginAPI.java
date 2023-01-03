package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.model.User;
import csed.database.orderprocessingbackend.service.ActiveUserService;
import csed.database.orderprocessingbackend.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.UUID;


@RestController
@RequestMapping("/logIn")
public class LoginAPI {

    private final LoginService loginService;

    public LoginAPI(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/logIn")
    public ResponseEntity<String> logIn(@RequestParam String email, @RequestParam String password)  {
        String res = loginService.logIn(email, password);
        System.out.println(res);
        if (res.equals("Email doesn't exist")){
                return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        if (res.equals("Login Successfully")){
            String sessionId = ActiveUserService.getInstance().getSessionID(email);
            return new ResponseEntity<>(sessionId, HttpStatus.OK);
        }
        if (res.equals("Wrong Password"))
            return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(res, HttpStatus.METHOD_FAILURE);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam String sessionID) {
        User user = loginService.getUser(sessionID);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam("sessionID") String sessionID) {
        loginService.logout(sessionID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
