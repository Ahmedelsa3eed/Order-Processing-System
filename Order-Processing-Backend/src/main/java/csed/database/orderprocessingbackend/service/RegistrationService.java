package csed.database.orderprocessingbackend.service;



import csed.database.orderprocessingbackend.model.registration.RegistrationRequest;
import csed.database.orderprocessingbackend.model.registration.RegistrationResponses;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {
    private final UserService userService;

    public RegistrationResponses registerUser(RegistrationRequest request) {
        if(userService.getUserByUserName(request.getUserName()).isPresent()) return RegistrationResponses.USER_NAME_ALREADY_EXISTS;
        if(userService.getUserByEmail(request.getEmail()).isPresent()) return RegistrationResponses.EMAIL_ALREADY_EXISTS;
        userService.addUser(request);
        return RegistrationResponses.SUCCESSFUL_REGISTRATION;
    }

}
