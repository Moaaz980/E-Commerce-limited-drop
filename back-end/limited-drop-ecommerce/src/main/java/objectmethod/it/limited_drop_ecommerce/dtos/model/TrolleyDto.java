package objectmethod.it.limited_drop_ecommerce.dtos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrolleyDto {
    String id;
    Integer totalItems;
    UserDto userDto;
}
