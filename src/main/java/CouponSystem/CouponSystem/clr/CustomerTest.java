package CouponSystem.CouponSystem.clr;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.services.CouponService;
import CouponSystem.CouponSystem.database.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Order(4)
//@Component
@RequiredArgsConstructor
public class CustomerTest implements CommandLineRunner {
    private final CouponService couponService;
    private final CustomerService customerService;
    @Override
    public void run(String... args) throws Exception {
        try{
            System.out.println("Testing Customer");
            System.out.println("buying coupon");
            couponService.addCouponPurchase(1,4);
            System.out.println("Printing all customer coupons");
            System.out.println(customerService.getAllCustomerCoupons(1));
            System.out.println("Printing all customer coupons by category");
            System.out.println(customerService.getAllCustomerCouponsByCategory(Category.Food,1));
            System.out.println("Printing all customer coupons by max price");
            System.out.println(customerService.getAllCustomerCouponsByMaxPrice(20,1));
            System.out.println("Printing customer details");
            System.out.println(customerService.getOneCustomer(1));
        }
        catch (CouponSystemException e){
            System.out.println(e.getMessage());
        }
    }
}
