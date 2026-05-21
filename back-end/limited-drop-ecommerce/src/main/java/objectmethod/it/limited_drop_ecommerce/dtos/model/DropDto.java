package objectmethod.it.limited_drop_ecommerce.dtos.model;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DropDto {
    String id;
    @NotNull(message = "Start date time is required")
    LocalDateTime startDateTime;
    @NotNull(message = "End date time is required")
    LocalDateTime endDateTime;
}
