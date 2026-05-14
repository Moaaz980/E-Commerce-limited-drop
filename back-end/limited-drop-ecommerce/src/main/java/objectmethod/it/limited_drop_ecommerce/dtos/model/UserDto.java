package objectmethod.it.limited_drop_ecommerce.dtos.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.enums.UserRole;
import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    String id;
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name must not be empty or contain only spaces")
    String name;
    @NotNull(message = "Surname is required")
    @NotBlank(message = "Surname must not be empty or contain only spaces")
    String surname;
    @NotNull(message = "Email is required")
    @Email(message = "Email format is invalid")
    String email;
    @Pattern (
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#&%$£_-])[a-zA-Z0-9@#&%$£_-]{8,12}$" ,
        message = "Use 8–12 characters with upper/lowercase letters, a number and a symbol"
    )
    String password;
    @NotNull(message = "User role is required")
    UserRole role;
    List<OrderDto> orders;
    TrolleyDto trolley;
}
