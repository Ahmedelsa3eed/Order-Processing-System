package csed.database.orderprocessingbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Book implements Serializable {
    private Long ISBN;
    private String title;
    private Long publisher_id;
    private Integer publication_year;
    private Integer price;
    private String category;
    private Integer quantity;
    private Integer threshold;

    @Override
    public String toString() {
        return ISBN +
                ", '" + title + '\'' +
                ", " + publisher_id +
                ", " + publication_year +
                ", " + price +
                ", '" + category + '\'' +
                ", " + quantity +
                ", " + threshold;
    }
}
