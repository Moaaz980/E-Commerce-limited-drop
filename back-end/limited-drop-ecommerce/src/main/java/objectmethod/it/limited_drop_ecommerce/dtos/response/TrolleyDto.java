package objectmethod.it.limited_drop_ecommerce.dtos.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.dtos.model.UserDto;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrolleyDto {
    String id;
    Integer totalItems;
    String userId;
}
