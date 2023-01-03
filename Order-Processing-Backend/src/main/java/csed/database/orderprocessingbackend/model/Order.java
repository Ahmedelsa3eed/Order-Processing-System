package csed.database.orderprocessingbackend.model;

import java.io.Serializable;

public class Order implements Serializable {

    private Long orderId;
    private Long BookISBN;
    private int qantity;

    @Override
    public String toString() {
        return orderId +
                "," + BookISBN +
                "," + qantity;
    }
}
