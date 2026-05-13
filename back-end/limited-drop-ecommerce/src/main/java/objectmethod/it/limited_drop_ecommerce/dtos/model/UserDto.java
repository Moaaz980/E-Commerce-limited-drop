package objectmethod.it.limited_drop_ecommerce.dtos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.enums.UserRole;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    String id;
    String name;
    String surname;
    String email;
    String password;
    UserRole role;
}
