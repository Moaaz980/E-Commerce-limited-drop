package objectmethod.it.limited_drop_ecommerce.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.enums.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
@Table (name = "payment")
public class Payment {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    String id;
    @Column(name = "amount_paid" , nullable = false)
    BigDecimal amountPaid;
    @Column(name = "date_time" , nullable = false)
    LocalDateTime dateTime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    PaymentStatus state;
    @OneToOne
    @JoinColumn(name = "order_id")
    Order order;
}
