package csed.database.orderprocessingbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Sale {
    private Long id;
    private String name;
    private String category;
    private Integer quantity;
    private Integer price;

    @Override
    public String toString() {
        return id +
                ", '" + name + '\'' +
                ", " + category +
                ", " + quantity +
                ", " + price ;
    }

//    public Integer getQuantity(){
//        return this.quantity;
//    }
//    public Integer getPrice(){
//        return this.price;
//    }
//    public void setQuantity(Integer quantity){
//        this.quantity = quantity;
//    }
//    public void  setPrice(Integer price){
//        this.price = price;
//    }
}
