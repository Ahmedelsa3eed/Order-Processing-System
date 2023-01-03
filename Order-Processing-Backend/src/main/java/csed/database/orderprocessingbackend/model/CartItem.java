package csed.database.orderprocessingbackend.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItem {
    private Long ISBN;
    private String title;
    private Double price;
    private String publisherName;
    private Integer quantity;
}
