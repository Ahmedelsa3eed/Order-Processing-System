package csed.database.orderprocessingbackend.service;

import csed.database.orderprocessingbackend.dao.DatabaseInstance;
import csed.database.orderprocessingbackend.model.CheckoutResponses;
import csed.database.orderprocessingbackend.model.CreditCardDetails;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CheckoutService {

    private final CartService cartService;
    private final DatabaseInstance instance;

    private final ActiveUserService activeUserService = ActiveUserService.getInstance();

    public CheckoutService(CartService cartService) throws SQLException {
        this.cartService = cartService;
        this.instance = DatabaseInstance.getInstance();
    }

    public CheckoutResponses checkoutCart(String sessionId, CreditCardDetails creditCardDetails) {
        Long userId = this.activeUserService.getUserIdFromSessionId(sessionId);
        if (userId == null) {
            return CheckoutResponses.INVALID_SESSION_ID;
        }
        if (validateCreditCardDetails(creditCardDetails) == CheckoutResponses.INVALID_CREDIT_CARD_DETAILS) {
            return CheckoutResponses.INVALID_CREDIT_CARD_DETAILS;
        }
        System.out.println(userId);
        AtomicBoolean success = new AtomicBoolean(true);
        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
        cartService.getAll(sessionId).forEach(cartItem -> {
            // save to check out table
            System.out.println(cartItem.toString());
            String query = "INSERT INTO checkout (ISBN, user_id, quantity, order_date) VALUES ("
                    + cartItem.getISBN() + ", "
                    + userId + ", "
                    + cartItem.getQuantity() + ", "
                    + "DATE " + "'" + sqlDate +"'" + ");";

            try {
                this.instance.executeUpdate(query);
                // delete from cart
                cartService.deleteCartItem(sessionId, cartItem.getISBN());
            } catch (SQLException e) {
                success.set(false);
                e.printStackTrace();
            }

        });

        if (success.get()) {
            return CheckoutResponses.SUCCESS;
        } else {
            return CheckoutResponses.FAILURE;
        }


    }

    private CheckoutResponses validateCreditCardDetails(CreditCardDetails creditCardDetails) {
        return CheckoutResponses.VALID_CREDIT_CARD_DETAILS;
    }


}
