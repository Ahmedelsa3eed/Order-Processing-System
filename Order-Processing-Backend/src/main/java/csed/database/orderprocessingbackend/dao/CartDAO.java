package csed.database.orderprocessingbackend.dao;

import csed.database.orderprocessingbackend.model.CartItem;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartDAO {
    private final DatabaseInstance DBInstance;

    public CartDAO() throws SQLException {
        this.DBInstance = DatabaseInstance.getInstance();
    }

    public List<CartItem> getAll(Long userId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        String getCartItemsQuery = "SELECT B.ISBN, B.title, B.price, P.name, C.quantity " +
                "FROM Cart AS C JOIN Books AS B ON C.ISBN = B.ISBN JOIN Publishers AS P " +
                "ON B.publisher_id = P.publisher_id " +
                "WHERE C.user_id = " + userId;
        ResultSet cartResult = this.DBInstance.executeQuery(getCartItemsQuery);
        while (cartResult.next()) {
            CartItem cartItem = new CartItem();
            cartItem.setISBN(cartResult.getLong("ISBN"));
            cartItem.setTitle(cartResult.getString("title"));
            cartItem.setPrice(cartResult.getDouble("price"));
            cartItem.setPublisherName(cartResult.getString("name"));
            cartItem.setQuantity(cartResult.getInt("quantity"));
            cartItems.add(cartItem);
        }
        return cartItems;
    }

    public void save(CartItem cartItem) {
        // TODO
    }

    public void updateQuantity(Long userId, Long ISBN, Integer quantity) throws SQLException {
        String updateQuantityQuery = "UPDATE Cart " +
                "SET quantity = " + quantity + " " +
                "WHERE user_id = " + userId + " AND ISBN = " + ISBN;
        this.DBInstance.executeUpdate(updateQuantityQuery);
    }

    public void delete(Long userId, Long ISBN) throws SQLException {
        String deleteCartItemQuery = "DELETE FROM Cart WHERE user_id = " + userId + " AND ISBN = " + ISBN;
        this.DBInstance.executeUpdate(deleteCartItemQuery);
    }
}
