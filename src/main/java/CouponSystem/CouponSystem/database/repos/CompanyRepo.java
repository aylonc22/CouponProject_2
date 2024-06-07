package CouponSystem.CouponSystem.database.repos;

import CouponSystem.CouponSystem.database.beans.Company;
import CouponSystem.CouponSystem.database.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepo extends JpaRepository<Company,Integer> {
    Company findByEmailAndPassword(String name,String email);
    Boolean existsByNameOrEmail(String name, String email);
}
