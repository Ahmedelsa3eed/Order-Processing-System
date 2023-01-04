package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.model.registration.RegistrationResponses;
import csed.database.orderprocessingbackend.model.settings.ChangeNameRequest;
import csed.database.orderprocessingbackend.model.settings.ChangePasswordRequest;
import csed.database.orderprocessingbackend.model.settings.SettingsResponses;
import csed.database.orderprocessingbackend.service.SettingsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "settings")
public class SettingsAPI {

    private final SettingsService settingsService;

    // change name
    @PutMapping(path = "changeName")
    public ResponseEntity<SettingsResponses> changeName(@RequestParam String sessionId, @RequestBody ChangeNameRequest request) {
        System.out.println(request);
        SettingsResponses settingsResponses = settingsService.changeName(sessionId, request);
        return new ResponseEntity<>(settingsResponses, httpStatus(settingsResponses));

    }

    // change password
    @PutMapping(path = "changePassword")
    public ResponseEntity<SettingsResponses> changePassword(@RequestParam String sessionId, @RequestBody ChangePasswordRequest request) {
        System.out.println(request);
        SettingsResponses settingsResponses = settingsService.changePassword(sessionId, request);
        return new ResponseEntity<>(settingsResponses, httpStatus(settingsResponses));

    }

    // change username
    @PutMapping(path = "changeUsername")
    public ResponseEntity<SettingsResponses> changeUserName(@RequestParam String sessionId, @RequestBody String username) {
        System.out.println(username);
        SettingsResponses settingsResponses = settingsService.changeUserName(sessionId, username);
        return new ResponseEntity<>(settingsResponses, httpStatus(settingsResponses));

    }

    // change email
    @PutMapping(path = "changeEmail")
    public ResponseEntity<SettingsResponses> changeEmail(@RequestParam String sessionId, @RequestBody String email) {
        System.out.println(email);
        SettingsResponses settingsResponses = settingsService.changeEmail(sessionId, email);
        return new ResponseEntity<>(settingsResponses, httpStatus(settingsResponses));

    }

    // change phone number
    @PutMapping(path = "changePhone")
    public ResponseEntity<SettingsResponses> changePhoneNumber(@RequestParam String sessionId, @RequestBody String phoneNumber) {
        System.out.println(phoneNumber);
        SettingsResponses settingsResponses = settingsService.changePhoneNumber(sessionId, phoneNumber);
        return new ResponseEntity<>(settingsResponses, httpStatus(settingsResponses));

    }

    // change address
    @PutMapping(path = "changeAddress")
    public ResponseEntity<SettingsResponses> changeAddress(@RequestParam String sessionId, @RequestBody String address) {
        System.out.println(address);
        SettingsResponses settingsResponses = settingsService.changeAddress(sessionId, address);
        return new ResponseEntity<>(settingsResponses, httpStatus(settingsResponses));

    }








    private HttpStatus httpStatus(SettingsResponses settingsResponses) {
        if (settingsResponses == SettingsResponses.SUCCESSFUL) {
            return HttpStatus.OK;
        } else if (settingsResponses == SettingsResponses.FAILED
                || settingsResponses == SettingsResponses.EMAIL_ALREADY_TAKEN
                || settingsResponses == SettingsResponses.USERNAME_ALREADY_TAKEN) {
            return HttpStatus.CONFLICT;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
