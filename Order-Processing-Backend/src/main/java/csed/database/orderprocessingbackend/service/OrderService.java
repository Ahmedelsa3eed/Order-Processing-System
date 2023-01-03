package csed.database.orderprocessingbackend.service;

import csed.database.orderprocessingbackend.dao.DatabaseInstance;
import csed.database.orderprocessingbackend.model.Book;
import csed.database.orderprocessingbackend.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class OrderService {

    private final DatabaseInstance instance;

    public OrderService() throws SQLException {
        this.instance = DatabaseInstance.getInstance();
    }

    public ResultSet GetAllOrder() {
        String query = "SELECT * FROM orders";
        ResultSet orders = null;

        try{
            orders = this.instance.executeQuery(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return orders;
    }

    public HttpStatus addOrder(Order order) {
        String query = "INSERT INTO orders VALUES("+order.toString()+")";

        try{
            this.instance.executeUpdate(query);
            return HttpStatus.OK;
        }catch(Exception e){
            e.printStackTrace();
        }

        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus confirmOrder(int order_id){
        try{
            String DeleteQ = "DELETE FROM orders WHERE order_id = " + order_id;
            instance.executeUpdate(DeleteQ);
            return HttpStatus.OK;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return HttpStatus.BAD_REQUEST;
    }

}
