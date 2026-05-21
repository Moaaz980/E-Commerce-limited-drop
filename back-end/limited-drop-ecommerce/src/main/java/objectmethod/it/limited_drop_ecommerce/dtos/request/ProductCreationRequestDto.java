package objectmethod.it.limited_drop_ecommerce.dtos.request;


import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.dtos.model.DropDto;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequestDto {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Product must have a valid name")
    @Size(min = 3 , max = 100 , message = "Name must have at least 3 charachters")
    String name;
    @NotNull(message = "Description is required")
    @NotBlank(message = "Descirption must have a valid description")
    @Size(min = 10 , max = 1000 , message = "Descritpion must have at least 10 charachters")
    String description;
    @NotNull(message = "Price is required")
    @DecimalMin("0.01")
    @Digits(integer = 5, fraction = 2)
    BigDecimal price;
    @NotNull(message = "Image is required")
    @NotBlank(message = "Image must have a valid URL")
    @Size(max = 500)
    @URL(message = "URL non valido")
    String image;
    @NotNull(message = "quantity is required")
    @Min(value = 1 , message = "Quantity must be at least one")
    Integer totalAvailable;
    @NotNull(message = "Drop cannot be null")
    String dropId;
}