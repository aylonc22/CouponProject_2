package CouponSystem.CouponSystem.database.repos;

import CouponSystem.CouponSystem.database.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Boolean existsByEmailAndPassword(String name,String email);
    Boolean existsByEmail(String email);
    @Query("SELECT c.customerID FROM Customer c JOIN c.coupons co WHERE co.companyID = :companyId")
    List<Integer> findCustomerIdsByCompanyCoupons(@Param("companyId") int companyId);
}
