package CouponSystem.CouponSystem.database.repos;

import CouponSystem.CouponSystem.database.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface CouponRepo extends JpaRepository<Coupon,Integer> {
Boolean existsByCompanyIDAndTitle(int companyId,String title);
}
