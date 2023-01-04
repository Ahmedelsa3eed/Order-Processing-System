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
public class Author {
    private Long author_id;
    private String first_name;
    private String last_name;

    private String address;
    private String phone_number;

    @Override
    public String toString(){
        return "( " + "'"+first_name+"'"+", "+ "'"+last_name+"'" + ", " + "'"+address + "'" + ", " + "'"+phone_number +"'" +")";
    }
}
