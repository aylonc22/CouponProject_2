package CouponSystem.CouponSystem.database.servicesImp;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.Exceptions.ErrMsg;
import CouponSystem.CouponSystem.database.beans.Coupon;
import CouponSystem.CouponSystem.database.beans.Customer;
import CouponSystem.CouponSystem.database.repos.CouponRepo;
import CouponSystem.CouponSystem.database.repos.CustomerRepo;
import CouponSystem.CouponSystem.database.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImp implements CouponService {
  final  CouponRepo couponRepo;
  final CustomerRepo customerRepo;
    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
      if(couponRepo.existsByCompanyIDAndTitle(coupon.getCompanyID(),coupon.getTitle())){
          throw new CouponSystemException(ErrMsg.COUPON_ALREADY_EXISTS);
      }
      couponRepo.save(coupon);
      System.out.println("A coupon was added\n" + coupon);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws CouponSystemException {
      if(couponRepo.findById(coupon.getCouponID()).isPresent())  {
        if(couponRepo.existsByCompanyIDAndTitle(coupon.getCompanyID(),coupon.getTitle())){
          throw new CouponSystemException(ErrMsg.COUPON_ALREADY_EXISTS);
        }
          couponRepo.saveAndFlush(coupon);
        System.out.println("Coupon with ID: " + coupon.getCouponID() + " is updated\n" + coupon);
      }
      throw new CouponSystemException(ErrMsg.COUPON_NOT_FOUND);
    }

    @Override
    public void deleteCoupon(int couponID) {
        couponRepo.deleteById(couponID);
      System.out.println("Coupon ID: " + couponID + " is deleted");
    }

    @Override
    public List<Coupon> getAllCoupons() {
      return couponRepo.findAll();
    }

    @Override
    public Coupon getOneCoupon(int couponID) throws CouponSystemException {
      return couponRepo.findById(couponID)
              .orElseThrow(()->new CouponSystemException(ErrMsg.COUPON_NOT_FOUND));
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) throws CouponSystemException {
      Coupon coupon = couponRepo.findById(couponID)
              .orElseThrow(()->new CouponSystemException(ErrMsg.COUPON_NOT_FOUND));
      if(coupon.getAmount() == 0){
        throw new CouponSystemException(ErrMsg.OUT_OF_STOCK);
      }
      Customer customer = customerRepo.findById(customerID)
              .orElseThrow(()->new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND));
      customer.getCoupons().add(coupon);
      customerRepo.saveAndFlush(customer);
      System.out.println("Customer ID: " + customerID + " bought coupon ID: " + couponID);
    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) throws CouponSystemException {
      Coupon coupon = couponRepo.findById(couponID)
              .orElseThrow(()->new CouponSystemException(ErrMsg.COUPON_NOT_FOUND));
      Customer customer = customerRepo.findById(customerID)
              .orElseThrow(()->new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND));
      //Retrieving to stock
      coupon.setAmount(coupon.getAmount()+1);
      couponRepo.saveAndFlush(coupon);
      //Canceling ownership of the coupon
      customer.setCoupons(customer.getCoupons().stream().filter(coupon1->coupon1.getCouponID()!=couponID).toList());
      customerRepo.saveAndFlush(customer);
      System.out.println("Customer ID: " + customerID + " removed coupon ID: " + couponID);
    }
}
