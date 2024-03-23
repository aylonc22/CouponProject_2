package CouponSystem.CouponSystem.database.services;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Coupon;

import java.util.List;

public interface CouponService {
    void addCoupon(Coupon coupon) throws CouponSystemException;
    void updateCoupon(Coupon coupon) throws CouponSystemException;
    void deleteCoupon(int couponID) throws CouponSystemException;
    List<Coupon> getAllCoupons();
    Coupon getOneCoupon(int couponID) throws CouponSystemException;
    void addCouponPurchase(int customerID,int couponID) throws CouponSystemException;
    void deleteCouponPurchase(int customerID,int couponID) throws CouponSystemException;
}
