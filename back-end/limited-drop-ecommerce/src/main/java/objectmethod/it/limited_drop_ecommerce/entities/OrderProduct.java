package objectmethod.it.limited_drop_ecommerce.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
@Table (name = "ord_prod")
public class OrderProduct {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    String id;
    Integer quantity;
    BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
