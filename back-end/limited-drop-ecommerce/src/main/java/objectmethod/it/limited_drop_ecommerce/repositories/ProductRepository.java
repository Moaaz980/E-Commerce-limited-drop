package objectmethod.it.limited_drop_ecommerce.repositories;

import objectmethod.it.limited_drop_ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, String> {
}
