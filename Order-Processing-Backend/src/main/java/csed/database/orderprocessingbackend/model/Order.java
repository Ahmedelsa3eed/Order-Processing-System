package csed.database.orderprocessingbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Order implements Serializable {

    private Long order_id;
    private Long book_ISBN;
    private int quantity;

    @Override
    public String toString() {
        return order_id +
                "," + book_ISBN +
                "," + quantity;
    }
}
