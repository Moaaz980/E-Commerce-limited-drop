package objectmethod.it.limited_drop_ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
@Table (name = "drops")
public class Drop {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    String id;
    @NotNull
    @Column(nullable = false , name = "start_date_time")
    LocalDateTime startDateTime;
    @NotNull
    @Column(nullable = false , name = "end_date_time")
    LocalDateTime endDateTime;
}
