package csed.database.orderprocessingbackend.service;

import csed.database.orderprocessingbackend.dao.CartDAO;
import csed.database.orderprocessingbackend.dao.DatabaseInstance;
import csed.database.orderprocessingbackend.model.cart.Cart;
import csed.database.orderprocessingbackend.model.cart.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
public class CartService {
    private final CartDAO cartDAO;
    private final ActiveUserService userService;
    private final DatabaseInstance DBInstance;

    @Autowired
    public CartService(CartDAO cartDAO) throws SQLException {
        this.cartDAO = cartDAO;
        this.userService = ActiveUserService.getInstance();
        this.DBInstance = DatabaseInstance.getInstance();
    }

    public List<CartItem> getAll(String sessionId) {
        Long userId = this.userService.getUserIdFromSessionId(sessionId);
        List<CartItem> items;
        try {
            items = this.cartDAO.getAll(userId);
        }
        catch (SQLException e) {
            e.printStackTrace();
            items = Collections.emptyList();
        }
        return items;
    }

    public Boolean deleteCartItem(String sessionId, Long ISBN) {
        Long userId = this.userService.getUserIdFromSessionId(sessionId);
        try {
            this.cartDAO.delete(userId, ISBN);
            this.DBInstance.commitTransaction();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            this.DBInstance.rollbackTransaction();
            return false;
        }
    }

    public Boolean updateCartItemQuantity(String sessionId, Long ISBN, Integer newQuantity) {
        Long userId = this.userService.getUserIdFromSessionId(sessionId);
        try {
            this.cartDAO.updateQuantity(userId, ISBN, newQuantity);
            this.DBInstance.commitTransaction();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            this.DBInstance.rollbackTransaction();
            return false;
        }
    }

    public Boolean addToCart(String sessionId, Long ISBN) {
        Long userId = this.userService.getUserIdFromSessionId(sessionId);
        try {
            Cart cart = new Cart(ISBN, userId, 1, false);
            this.cartDAO.save(cart);
            this.DBInstance.commitTransaction();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            this.DBInstance.rollbackTransaction();
            return false;
        }
    }

    public Boolean deleteAllCartItem(String sessionId) {
        Long userId = this.userService.getUserIdFromSessionId(sessionId);
        try {
            this.cartDAO.deleteAll(userId);
            this.DBInstance.commitTransaction();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            this.DBInstance.rollbackTransaction();
            return false;
        }
    }
}
