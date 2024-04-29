package CouponSystem.CouponSystem.controllers;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.beans.Company;
import CouponSystem.CouponSystem.database.beans.Coupon;
import CouponSystem.CouponSystem.database.services.CompanyService;
import CouponSystem.CouponSystem.database.services.CouponService;
import CouponSystem.CouponSystem.database.servicesImp.CompanyServiceImp;
import CouponSystem.CouponSystem.database.servicesImp.CouponServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/company")
@RequiredArgsConstructor

public class CompanyController {
    private final CompanyServiceImp companyServiceImp;
    private final CouponServiceImp couponServiceImp;
    @PostMapping("/coupon")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@Validated @RequestBody Coupon coupon) throws CouponSystemException {
        couponServiceImp.addCoupon(coupon);
    }
    @PutMapping("/coupon")
    @ResponseStatus(HttpStatus.OK)
    public void updateCoupon(@Validated @RequestBody Coupon coupon) throws CouponSystemException {
        couponServiceImp.updateCoupon(coupon);
    }
    @DeleteMapping("/coupon/{couponID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCoupon(@PathVariable int couponID) throws CouponSystemException {
        couponServiceImp.deleteCoupon(couponID);
    }
    @GetMapping("/allCoupons/{companyID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCompanyCoupons(@PathVariable int companyID) throws CouponSystemException {
        return companyServiceImp.getAllCompanyCoupons(companyID);
    }

    @GetMapping("/couponsByCategory/{companyID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCompanyCouponsByCategory(@PathVariable Category category,@PathVariable int companyID) {
        return companyServiceImp.getAllCompanyCouponsByCategory(category, companyID);
    }

    @GetMapping("/couponsByMaxPrice/{companyID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Coupon> getAllCompanyCouponsByMaxPrice(@PathVariable double price,@PathVariable int companyID) {
        return companyServiceImp.getAllCompanyCouponsByMaxPrice(price, companyID);
    }

//    @GetMapping("")
//    @ResponseStatus(HttpStatus.OK)
//    public String companyDetails(){
//        return companyServiceImp.getOneCompany();
//    }
}
