package csed.database.orderprocessingbackend.api;

import com.mysql.cj.protocol.Resultset;
import csed.database.orderprocessingbackend.model.Book;
import csed.database.orderprocessingbackend.model.Order;
import csed.database.orderprocessingbackend.service.BookService;
import csed.database.orderprocessingbackend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("/books")
public class OrderAPI {

    private final OrderService orderService;

    public OrderAPI(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/manager/GetAllOrders")
    public ResultSet addBook() {
        return orderService.GetAllOrder();
    }

    @PostMapping("/manager/orderBook")
    public ResponseEntity<?> addBook(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.addOrder(order));
    }

    @PutMapping("/manager/deleteOrder")
    public ResponseEntity<?> confirmOrder(@RequestParam Integer orderId) throws SQLException {
        return new ResponseEntity<>(orderService.confirmOrder(orderId));
    }

}
