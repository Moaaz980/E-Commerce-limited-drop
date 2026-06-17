package objectmethod.it.limited_drop_ecommerce.dtos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.dtos.response.TrolleyDto;
import objectmethod.it.limited_drop_ecommerce.enums.UserRole;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    String id;
    String name;
    String surname;
    String email;
    String password;
    UserRole role;
    List<OrderDto> orders;
    String trolleyId;
}
