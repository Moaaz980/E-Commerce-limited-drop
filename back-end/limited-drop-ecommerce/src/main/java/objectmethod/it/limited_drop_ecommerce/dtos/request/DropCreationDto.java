package objectmethod.it.limited_drop_ecommerce.dtos.request;


import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.Instant;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DropCreationDto {
    @NotNull(message = "Start date time is required")
    Instant startDateTime;
    @NotNull(message = "End date time is required")
    Instant endDateTime;
}
