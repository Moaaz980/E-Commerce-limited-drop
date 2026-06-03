package objectmethod.it.limited_drop_ecommerce.repositories;

import objectmethod.it.limited_drop_ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository <Product, String> {
    Optional<Product> findByName(String productName);

    @Query(
        "SELECT EXISTS( " +
                "SELECT 1 " +
                "FROM Product pr " +
                "WHERE pr.id = :id " +
                "AND pr.totalAvailable > 0 " +
                "AND " +
                "EXISTS( " +
                "SELECT 1 " +
                "FROM Drop dr " +
                "WHERE dr.id = pr.drop.id " +
                "AND CURRENT_TIMESTAMP BETWEEN dr.startDateTime AND dr.endDateTime " +
                ")" +
                ")"
    )
    boolean isPossibileToPurchase(@Param("id") String id);
}
