package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.model.CartItem;
import csed.database.orderprocessingbackend.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartAPI {
    private final CartService cartService;

    public CartAPI(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartItem>> getCartItems(@RequestParam("sessionId") String sessionId) {
        return new ResponseEntity<>(this.cartService.getAll(sessionId), HttpStatus.OK);
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<Boolean> updateCartItemQuantity(@RequestParam("sessionId") String sessionId,
                                                          @RequestParam("ISBN") Long ISBN,
                                                          @RequestParam("quantity") Integer newQuantity) {
        Boolean res = this.cartService.updateCartItemQuantity(sessionId, ISBN, newQuantity);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCartItem")
    public ResponseEntity<Boolean> deleteCartItem(@RequestParam("sessionId") String sessionId,
                                                  @RequestParam("ISBN") Long ISBN) {
        Boolean res = this.cartService.deleteCartItem(sessionId, ISBN);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
