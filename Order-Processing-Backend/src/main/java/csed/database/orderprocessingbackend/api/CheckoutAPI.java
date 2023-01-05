package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.model.CheckoutResponses;
import csed.database.orderprocessingbackend.model.CreditCardDetails;
import csed.database.orderprocessingbackend.service.CheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/checkout")
public class CheckoutAPI {

    private final CheckoutService checkoutService;

    public CheckoutAPI(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }
    @PutMapping(path = "/cart")
    public ResponseEntity<CheckoutResponses> checkoutCart(@RequestParam("sessionId") String sessionId, @RequestBody CreditCardDetails creditCardDetails) {
        CheckoutResponses checkoutResponses = checkoutService.checkoutCart(sessionId, creditCardDetails);
        return new ResponseEntity<>(checkoutResponses, httpStatus(checkoutResponses));

    }

    private HttpStatus httpStatus(CheckoutResponses checkoutResponses) {
        if (checkoutResponses == CheckoutResponses.SUCCESS) {
            return HttpStatus.OK;
        } else if (checkoutResponses == CheckoutResponses.INVALID_SESSION_ID
                || checkoutResponses == CheckoutResponses.INVALID_CREDIT_CARD_DETAILS) {
            return HttpStatus.CONFLICT;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }


}
