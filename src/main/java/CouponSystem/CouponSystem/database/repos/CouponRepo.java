package CouponSystem.CouponSystem.database.repos;

import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface CouponRepo extends JpaRepository<Coupon,Integer> {
Boolean existsByCompanyIDAndTitle(int companyId,String title);
Optional<Coupon> findByCompanyIDAndTitle(int companyId, String title);
List<Coupon> findAllByCompanyIDAndCategory(int companyID, Category category);
List<Coupon> findAllByCompanyIDAndPriceLessThanEqual(int companyId, double maxPrice);
@Query("SELECT c FROM Coupon c where c.end_date <= NOW()")
List<Coupon> findExpiredCoupons();
}
