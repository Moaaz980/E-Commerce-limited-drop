package objectmethod.it.limited_drop_ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.enums.OrderState;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @NotNull
    @Column(nullable = false)
    LocalDateTime date;
    @NotNull
    @Column(nullable = false , name = "total_price")
    Double totalPrice;
    @NotNull
    @Column(nullable = false)
    OrderState state;
    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    User user;
    @OneToOne
    @JoinColumn(name = "payment_id")
    Payment payment;
}
