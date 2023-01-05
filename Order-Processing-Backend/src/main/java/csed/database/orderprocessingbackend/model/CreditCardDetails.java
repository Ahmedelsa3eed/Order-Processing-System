package csed.database.orderprocessingbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreditCardDetails {
    private String cardNumber;
    private String expiryDate;

}
