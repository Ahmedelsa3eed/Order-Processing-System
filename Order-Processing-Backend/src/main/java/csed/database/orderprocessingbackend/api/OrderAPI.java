package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.model.Order;
import csed.database.orderprocessingbackend.model.Requests.OrderRequest;
import csed.database.orderprocessingbackend.service.OrderService;
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
    public List<Order> addBook() {
        return orderService.GetAllOrder();
    }

    @PutMapping("/manager/orderBook")
    public ResponseEntity<?> addBook(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.addOrder(orderRequest));
    }

    @PostMapping("/manager/confirm")
    public ResponseEntity<?> confirmOrder(@RequestParam Integer orderId){
        return new ResponseEntity<>(orderService.confirmOrder(orderId));
    }

}
