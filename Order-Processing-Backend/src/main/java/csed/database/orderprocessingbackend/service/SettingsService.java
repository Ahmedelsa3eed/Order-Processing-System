package csed.database.orderprocessingbackend.service;

import csed.database.orderprocessingbackend.model.User;
import csed.database.orderprocessingbackend.model.settings.ChangeNameRequest;
import csed.database.orderprocessingbackend.model.settings.ChangePasswordRequest;
import csed.database.orderprocessingbackend.model.settings.SettingsResponses;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingsService {

    private final UserService userService;
    private final ActiveUserService activeUserService = ActiveUserService.getInstance();

    public SettingsService(UserService userService) {
        this.userService = userService;
    }


    // change name
    public SettingsResponses changeName(String sessionId, ChangeNameRequest request) {
        Long userId = activeUserService.getUserIdFromSessionId(sessionId);
        if (userId == null) {
            return SettingsResponses.INVALID_SESSION_ID;
        }
        Optional<User> user = userService.getUserById(userId);
        if(user.isPresent()) {
            userService.updateUserFirstName(request.getFirstName(), userId);
            userService.updateUserLastName(request.getLastName(), userId);
            return SettingsResponses.SUCCESSFUL;
        } else {
            return SettingsResponses.FAILED;
        }
    }


    public SettingsResponses changePassword(String sessionId, ChangePasswordRequest request) {
        Long userId = activeUserService.getUserIdFromSessionId(sessionId);
        if (userId == null) {
            return SettingsResponses.INVALID_SESSION_ID;
        }
        Optional<User> user = userService.getUserById(userId);
        if(user.isPresent()) {
            if (user.get().getPassword().equals(request.getCurrentPassword())) {
                userService.updateUserPassword(request.getNewPassword(), userId);
                return SettingsResponses.SUCCESSFUL;
            } else {
                return SettingsResponses.FAILED;
            }
        } else {
            return SettingsResponses.FAILED;
        }
    }

    public SettingsResponses changePhoneNumber(String sessionId, String phoneNumber) {
        Long userId = activeUserService.getUserIdFromSessionId(sessionId);
        if (userId == null) {
            return SettingsResponses.INVALID_SESSION_ID;
        }
        Optional<User> user = userService.getUserById(userId);
        if(user.isPresent()) {
            userService.updateUserPhoneNumber(phoneNumber, userId);
            return SettingsResponses.SUCCESSFUL;
        } else {
            return SettingsResponses.FAILED;
        }
    }

    public SettingsResponses changeEmail(String sessionId, String email) {
        Long userId = activeUserService.getUserIdFromSessionId(sessionId);
        if (userId == null) {
            return SettingsResponses.INVALID_SESSION_ID;
        }
        Optional<User> user = userService.getUserById(userId);
        if(user.isPresent()) {
            // check if email is already taken
            if (userService.getUserByEmail(email).isPresent()) {
                return SettingsResponses.EMAIL_ALREADY_TAKEN;
            }
            userService.updateUserEmail(email, userId);
            return SettingsResponses.SUCCESSFUL;
        } else {
            return SettingsResponses.FAILED;
        }
    }

    public SettingsResponses changeAddress(String sessionId, String address) {
        Long userId = activeUserService.getUserIdFromSessionId(sessionId);
        if (userId == null) {
            return SettingsResponses.INVALID_SESSION_ID;
        }
        Optional<User> user = userService.getUserById(userId);
        if(user.isPresent()) {
            userService.updateUserAddress(address, userId);
            return SettingsResponses.SUCCESSFUL;
        } else {
            return SettingsResponses.FAILED;
        }
    }

    public SettingsResponses changeUserName(String sessionId, String username) {
        Long userId = activeUserService.getUserIdFromSessionId(sessionId);
        if (userId == null) {
            return SettingsResponses.INVALID_SESSION_ID;
        }
        Optional<User> user = userService.getUserById(userId);
        if(user.isPresent()) {
            // check if username already taken
            if (userService.getUserByUsername(username).isPresent()) {
                return SettingsResponses.USERNAME_ALREADY_TAKEN;
            }
            userService.updateUsername(username, userId);
            return SettingsResponses.SUCCESSFUL;
        } else {
            return SettingsResponses.FAILED;
        }
    }
}
