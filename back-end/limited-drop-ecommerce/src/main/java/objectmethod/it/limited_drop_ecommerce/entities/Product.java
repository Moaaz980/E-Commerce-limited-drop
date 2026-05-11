package objectmethod.it.limited_drop_ecommerce.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    String id;
    @NotNull
    @Column(nullable = false)
    String name;
    @NotNull
    @Column(nullable = false)
    String description;
    @NotNull
    @Column(nullable = false)
    Double price;
    @NotNull
    @Column(nullable = false)
    String image;
    @NotNull
    @Column(nullable = false , name = "total_available")
    Integer totalAvailable;
    @ManyToOne
    @JoinColumn(name = "drop_id" , referencedColumnName = "id")
    Drop drop;
    @OneToMany(mappedBy = "product")
    List<TrolleyProduct> tro_pro;
    @OneToMany(mappedBy = "product")
    List<Order> orderProducts;
}
