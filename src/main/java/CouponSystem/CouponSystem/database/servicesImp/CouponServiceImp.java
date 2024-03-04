package CouponSystem.CouponSystem.database.servicesImp;

import CouponSystem.CouponSystem.database.beans.Coupon;
import CouponSystem.CouponSystem.database.repos.CouponRepo;
import CouponSystem.CouponSystem.database.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CouponServiceImp implements CouponService {
   @Autowired
    CouponRepo couponRepo;
    @Override
    public void addCoupon(Coupon coupon) {

    }

    @Override
    public void updateCoupon(Coupon coupon) {

    }

    @Override
    public void deleteCoupon(int couponID) {

    }

    @Override
    public ArrayList<Coupon> getAllCoupons() {
        return null;
    }

    @Override
    public Coupon getOneCoupon(int couponID) {
        return null;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) {

    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) {

    }
}
