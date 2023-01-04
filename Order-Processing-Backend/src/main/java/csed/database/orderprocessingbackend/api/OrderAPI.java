package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.model.Book;
import csed.database.orderprocessingbackend.model.Order;
import csed.database.orderprocessingbackend.model.Requests.OrderRequest;
import csed.database.orderprocessingbackend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderAPI {

    private final OrderService orderService;

    public OrderAPI(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/manager/GetAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> list = orderService.GetAllOrders();
        if (list == null){
            return new ResponseEntity<>(list, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/manager/orderBook")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.addOrder(orderRequest));
    }

    @DeleteMapping("/manager/confirm")
    public ResponseEntity<?> confirmOrder(@RequestParam Integer orderId){
        return new ResponseEntity<>(orderService.confirmOrder(orderId));
    }

}
