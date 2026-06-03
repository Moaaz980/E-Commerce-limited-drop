package objectmethod.it.limited_drop_ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.enums.OrderState;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @NotNull
    @Column(nullable = false)
    LocalDateTime date;
    @NotNull
    @Column(nullable = false , name = "total_price")
    BigDecimal totalPrice;
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    OrderState state;
    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    User user;
    @OneToMany(mappedBy = "order")
    List<OrderProduct> orderProducts;
    @OneToOne(mappedBy = "order")
    Payment payment;
}
