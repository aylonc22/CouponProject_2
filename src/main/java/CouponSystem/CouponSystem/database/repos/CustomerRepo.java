package CouponSystem.CouponSystem.database.repos;

import CouponSystem.CouponSystem.database.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Boolean existsByEmailAndPassword(String name,String email);
    Boolean existsByEmail(String email);
}
