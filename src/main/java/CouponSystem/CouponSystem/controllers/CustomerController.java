package CouponSystem.CouponSystem.controllers;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.beans.Coupon;
import CouponSystem.CouponSystem.database.servicesImp.CouponServiceImp;
import CouponSystem.CouponSystem.database.servicesImp.CustomerServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerServiceImp customerServiceImp;
    private final CouponServiceImp couponServiceImp;
    @PostMapping("/buyCoupon/{couponID}/{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public void buyCoupon(@PathVariable int couponID,@PathVariable int customerID) throws CouponSystemException {
        couponServiceImp.addCouponPurchase(customerID,couponID);
    }
    @GetMapping("/coupons/{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCoupons(@PathVariable int customerID) throws CouponSystemException {
       return customerServiceImp.getAllCustomerCoupons(customerID);
    }
    @GetMapping("/couponsByCategory/{category}/{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCouponsByCategory(@PathVariable Category category,@PathVariable int customerID) throws CouponSystemException {
        return customerServiceImp.getAllCustomerCouponsByCategory(category,customerID);
    }
    @GetMapping("/couponsByMaxPrice/{price}/{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCouponsByMaxPrice(@PathVariable double price,@PathVariable int customerID) throws CouponSystemException {
        return customerServiceImp.getAllCustomerCouponsByMaxPrice(price,customerID);
    }
//    @GetMapping()
//    @ResponseStatus(HttpStatus.OK)
//    public String getDetails(){
//        return customerServiceImp.getOneCustomer();
//    }
}
