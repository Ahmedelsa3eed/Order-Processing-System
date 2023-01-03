package csed.database.orderprocessingbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Order implements Serializable {

    private Long orderId;
    private Long ISBN;
    private int quantity;

    @Override
    public String toString() {
        return orderId +
                "," + ISBN +
                "," + quantity;
    }
}
