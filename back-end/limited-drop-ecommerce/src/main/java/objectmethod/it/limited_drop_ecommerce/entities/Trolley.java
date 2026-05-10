package objectmethod.it.limited_drop_ecommerce.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
@Table (name = "trolley")
public class Trolley {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    String id;
    @Column(name = "total_items" , nullable = false)
    Integer totalItems;
    @OneToOne(mappedBy = "trolley")
    User user;
    @OneToMany(mappedBy = "trolley")
    List<Product> products;
}

