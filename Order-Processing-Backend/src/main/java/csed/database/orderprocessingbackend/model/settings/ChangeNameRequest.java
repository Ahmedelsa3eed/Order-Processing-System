package csed.database.orderprocessingbackend.model.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class ChangeNameRequest {
    private String firstName;
    private String lastName;


}
