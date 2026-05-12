package objectmethod.it.limited_drop_ecommerce.repositories;


import objectmethod.it.limited_drop_ecommerce.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository <Payment, String> {
}
