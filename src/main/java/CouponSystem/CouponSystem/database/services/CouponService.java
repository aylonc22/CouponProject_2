package CouponSystem.CouponSystem.database.services;

import CouponSystem.CouponSystem.database.beans.Coupon;

import java.util.ArrayList;

public interface CouponService {
    void addCoupon(Coupon coupon);
    void updateCoupon(Coupon coupon);
    void deleteCoupon(int couponID);
    ArrayList<Coupon> getAllCoupons();
    Coupon getOneCoupon(int couponID);
    void addCouponPurchase(int customerID,int couponID);
    void deleteCouponPurchase(int customerID,int couponID);
}
