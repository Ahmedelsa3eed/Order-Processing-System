package csed.database.orderprocessingbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class User implements Serializable {
    private String user_id;
    private String user_name;
    private String email;
    private String password;
    private String address;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String type;

}
