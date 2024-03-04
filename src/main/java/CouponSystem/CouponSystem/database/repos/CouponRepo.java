package CouponSystem.CouponSystem.database.repos;

import CouponSystem.CouponSystem.database.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon,Integer> {
}
