package objectmethod.it.limited_drop_ecommerce.dtos.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.Instant;



@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DropDto {
    String id;
    Instant startDateTime;
    Instant endDateTime;
}
