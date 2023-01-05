package csed.database.orderprocessingbackend.model.cart;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    private Long ISBN;
    private Long userId;
    private Integer quantity;
    private Boolean confirmed;
}
