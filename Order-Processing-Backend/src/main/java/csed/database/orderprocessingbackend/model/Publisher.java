package csed.database.orderprocessingbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publisher implements Serializable {
    private Long publisher_id;
    private String name;
    private String address;
    private String phone_number;

    @Override
    public String toString(){
        return "( " + "'"+name+"'" + ", " + "'"+address + "'" + ", " + "'"+phone_number +"'" +")";
    }

}
