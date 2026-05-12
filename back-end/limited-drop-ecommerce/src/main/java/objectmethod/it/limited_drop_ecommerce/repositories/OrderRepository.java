package objectmethod.it.limited_drop_ecommerce.repositories;

import objectmethod.it.limited_drop_ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository <Order, String> {
}
