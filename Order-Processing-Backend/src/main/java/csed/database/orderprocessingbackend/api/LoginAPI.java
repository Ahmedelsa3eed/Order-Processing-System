package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RestController
@RequestMapping("/logIn")
public class LoginAPI {

    private final LoginService loginService;

    public LoginAPI(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/logIn")
    public ResponseEntity<String> logIn(@RequestParam String email, @RequestParam String password)  {
        try {
            String res = loginService.logIn(email, password);
            if (res.equals("Email doesn't exist")){
                return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
            }
            if (res.equals("Login Successfully")){
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
            return new ResponseEntity<>(res, HttpStatus.FAILED_DEPENDENCY);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error", HttpStatus.EXPECTATION_FAILED);
        }
    }

}
