package objectmethod.it.limited_drop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    UserRole role;
    String sub;
    @OneToMany(mappedBy = "user")
    List<Order> orders;
    @OneToOne(mappedBy = "user")
    Trolley trolley;
}
