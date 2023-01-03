package csed.database.orderprocessingbackend.service;

import csed.database.orderprocessingbackend.dao.DatabaseInstance;
import csed.database.orderprocessingbackend.model.Order;
import csed.database.orderprocessingbackend.model.Requests.OrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final DatabaseInstance instance;

    public OrderService() throws SQLException {
        this.instance = DatabaseInstance.getInstance();
    }

    public List<Order> GetAllOrder() {
        String query = "SELECT * FROM orders";

        try {
            List<Order> list = new ArrayList<>();
            ResultSet rs = this.instance.executeQuery(query);
            if (!rs.next()) {
                return list;
            }
            do {
                Order order = new Order();
                order.setOrderId(rs.getLong("order_id"));
                order.setISBN(rs.getLong("ISBN"));
                order.setQuantity(rs.getInt("quantity"));
                list.add(order);
            }
            while (rs.next());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HttpStatus addOrder(OrderRequest orderRequest) {
        String query = "INSERT INTO orders (ISBN, quantity) VALUES " + orderRequest.toString();
        try {
            this.instance.executeUpdate(query);
            return HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus confirmOrder(int order_id) {
        try {
            String DeleteQ = "DELETE FROM orders WHERE order_id = " + order_id;
            instance.executeUpdate(DeleteQ);
            return HttpStatus.OK;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return HttpStatus.BAD_REQUEST;
    }

}
