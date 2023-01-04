package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.model.User;
import csed.database.orderprocessingbackend.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountAPI {
    private final AccountService accountService;

    public AccountAPI(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchAccounts(@RequestParam("searchString") String searchString) {
        List<User> response = accountService.searchAccounts(searchString);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/changeUserType")
    @ResponseBody
    public ResponseEntity<Boolean> changeUserType(@RequestParam("userId") Long userId,
                                              @RequestParam("type") String type) {
        return new ResponseEntity<>(accountService.changeUserType(userId, type), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAccount(@RequestParam("userId") Long userId) {
        return new ResponseEntity<>(accountService.deleteAccountById(userId), HttpStatus.OK);
    }

}