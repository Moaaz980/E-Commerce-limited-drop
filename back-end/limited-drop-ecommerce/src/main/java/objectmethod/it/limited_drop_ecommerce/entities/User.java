package objectmethod.it.limited_drop_ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import objectmethod.it.limited_drop_ecommerce.enums.UserRole;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @NotNull
    @Column(nullable = false)
    String name;
    @NotNull
    @Column(nullable = false)
    String surname;
    @NotNull
    @Column(nullable = false , unique = true)
    String email;
    @NotNull
    @Column(nullable = false)
    String password;
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    UserRole role;
    @OneToMany(mappedBy = "user")
    List<Order> orders;
}
