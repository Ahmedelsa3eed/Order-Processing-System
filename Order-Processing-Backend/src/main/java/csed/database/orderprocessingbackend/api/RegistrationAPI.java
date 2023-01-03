package csed.database.orderprocessingbackend.api;


import csed.database.orderprocessingbackend.model.registration.RegistrationRequest;
import csed.database.orderprocessingbackend.model.registration.RegistrationResponses;
import csed.database.orderprocessingbackend.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "registration")
public class RegistrationAPI {

    private RegistrationService registrationService;

    @PostMapping(path = "register")
    public ResponseEntity<RegistrationResponses> addUser(@RequestBody RegistrationRequest request) {
        System.out.println(request);
        RegistrationResponses registrationResponses = registrationService.registerUser(request);
        return new ResponseEntity<>(registrationResponses, httpStatus(registrationResponses));
    }

    private HttpStatus httpStatus(RegistrationResponses registrationResponses) {
        if (registrationResponses == RegistrationResponses.SUCCESSFUL_REGISTRATION) {
            return HttpStatus.OK;
        } else if (registrationResponses == RegistrationResponses.EMAIL_ALREADY_EXISTS
                || registrationResponses == RegistrationResponses.USER_NAME_ALREADY_EXISTS) {
            return HttpStatus.CONFLICT;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }


}