package objectmethod.it.limited_drop_ecommerce.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
@Table (name = "tro_prod")
public class TrolleyProduct {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    String id;
    @ManyToOne
    @JoinColumn(name = "trolley_id")
    Trolley trolley;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
