package objectmethod.it.limited_drop_ecommerce.dtos.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.enums.OrderState;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    String id;
    LocalDateTime date;
    BigDecimal totalPrice;
    OrderState state;
}
