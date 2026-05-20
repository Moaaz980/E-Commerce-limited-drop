package objectmethod.it.limited_drop_ecommerce.dtos.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.enums.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentDto {
    String id;
    BigDecimal amountPaid;
    LocalDateTime dateTime;
    PaymentStatus state;
    OrderDto orderDto;
}
