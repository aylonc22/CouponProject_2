package CouponSystem.CouponSystem.clr;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.beans.Company;
import CouponSystem.CouponSystem.database.beans.Coupon;
import CouponSystem.CouponSystem.database.services.CompanyService;
import CouponSystem.CouponSystem.database.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Order(3)
@Component
@RequiredArgsConstructor
public class CompanyTest implements CommandLineRunner {
    private final CompanyService companyService;
    private final CouponService couponService;
    @Override
    public void run(String... args) throws Exception {
        try{
            System.out.println("Testing Company");
            LocalDate date = LocalDate.now();
            Coupon coupon = Coupon.builder()
                    .couponID(0)
                    .companyID(1)
                    .category(Category.Food)
                    .title("yami yami")
                    .description("yami yami yami")
                    .start_date(Date.valueOf(date))
                    .end_date(Date.valueOf(date.plusDays(5)))
                    .amount(5)
                    .price(15)
                    .image("image")
                    .build();
            couponService.addCoupon(coupon);

            Company company = companyService.getOneCompany(1);
            company.getCoupons().forEach(coupon1 -> {
                if(coupon1.getTitle().equals("yami yami")){
                    coupon1.setDescription("yami yami yami yami");
                }
            });
            System.out.println("Updating coupon");
            companyService.updateCompany(company);
            System.out.println("Deleting coupon");
            couponService.deleteCoupon(company.getCoupons().get(0).getCouponID());
            System.out.println("Printing all company coupons");
            System.out.println(companyService.getAllCompanyCoupons(1));
            System.out.println("Printing all company coupons by category");
            System.out.println(companyService.getAllCompanyCouponsByCategory(Category.Food,1));
            System.out.println("Printing all company coupons by max price");
            System.out.println(companyService.getAllCompanyCouponsByMaxPrice(20,1));
            System.out.println("Printing company details");
            System.out.println(companyService.getOneCompany(1));
        }
        catch (CouponSystemException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
