package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.model.settings.ChangeNameRequest;
import csed.database.orderprocessingbackend.model.settings.ChangePasswordRequest;
import csed.database.orderprocessingbackend.model.settings.SettingsResponses;
import csed.database.orderprocessingbackend.service.SettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "settings")
public class SettingsAPI {

    private final SettingsService settingsService;

    public SettingsAPI(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @PutMapping(path = "changeName")
    public ResponseEntity<SettingsResponses> changeName(@RequestParam String sessionId, @RequestBody ChangeNameRequest request) {
        SettingsResponses settingsResponses = settingsService.changeName(sessionId, request);
        return new ResponseEntity<>(settingsResponses, httpStatus(settingsResponses));

    }

    @PutMapping(path = "changePassword")
    public ResponseEntity<SettingsResponses> changePassword(@RequestParam String sessionId, @RequestBody ChangePasswordRequest request) {
        SettingsResponses settingsResponses = settingsService.changePassword(sessionId, request);
        return new ResponseEntity<>(settingsResponses, httpStatus(settingsResponses));

    }

    @PutMapping(path = "changeUsername")
    public ResponseEntity<SettingsResponses> changeUserName(@RequestParam String sessionId, @RequestBody String username) {
        SettingsResponses settingsResponses = settingsService.changeUserName(sessionId, username);
        return new ResponseEntity<>(settingsResponses, httpStatus(settingsResponses));

    }

    @PutMapping(path = "changeEmail")
    public ResponseEntity<SettingsResponses> changeEmail(@RequestParam String sessionId, @RequestBody String email) {
        SettingsResponses settingsResponses = settingsService.changeEmail(sessionId, email);
        return new ResponseEntity<>(settingsResponses, httpStatus(settingsResponses));

    }

    @PutMapping(path = "changePhone")
    public ResponseEntity<SettingsResponses> changePhoneNumber(@RequestParam String sessionId, @RequestBody String phoneNumber) {
        SettingsResponses settingsResponses = settingsService.changePhoneNumber(sessionId, phoneNumber);
        return new ResponseEntity<>(settingsResponses, httpStatus(settingsResponses));

    }

    @PutMapping(path = "changeAddress")
    public ResponseEntity<SettingsResponses> changeAddress(@RequestParam String sessionId, @RequestBody String address) {
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
