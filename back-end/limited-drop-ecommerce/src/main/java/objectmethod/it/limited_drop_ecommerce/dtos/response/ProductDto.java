package objectmethod.it.limited_drop_ecommerce.dtos.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.dtos.model.DropDto;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    String id;
    String name;
    String description;
    BigDecimal price;
    String image;
    Integer totalAvailable;
    DropDto drop;
}
