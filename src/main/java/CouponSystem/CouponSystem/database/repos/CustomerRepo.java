package CouponSystem.CouponSystem.database.repos;

import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.beans.Coupon;
import CouponSystem.CouponSystem.database.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Customer findByEmailAndPassword(String email, String password);
    Boolean existsByEmail(String email);
    @Query("SELECT coupon FROM Customer c JOIN c.coupons coupon WHERE c.customerID = :customerId AND coupon.category = :category")
    List<Coupon> findCouponsByCustomerIDAndCouponsCategory(int customerId, Category category);
    @Query("SELECT coupon FROM Customer c JOIN c.coupons coupon WHERE c.customerID = :customerId AND coupon.price <= :price")
    List<Coupon> findCouponsByCustomerIDAndCouponsPriceLessThanEqual(int customerId, Double price);
    @Query("SELECT c.customerID FROM Customer c JOIN c.coupons co WHERE co.companyID = :companyId")
    List<Integer> findCustomerIdsByCompanyCoupons(@Param("companyId") int companyId);
}
