package csed.database.orderprocessingbackend.model.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;

}
