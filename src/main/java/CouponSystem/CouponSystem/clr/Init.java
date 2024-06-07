package CouponSystem.CouponSystem.clr;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.beans.Company;
import CouponSystem.CouponSystem.database.beans.Coupon;
import CouponSystem.CouponSystem.database.beans.Customer;
import CouponSystem.CouponSystem.database.services.CompanyService;
import CouponSystem.CouponSystem.database.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class Init implements CommandLineRunner {

    final CompanyService companyService;
    final CustomerService customerService;
    @Override
    public void run(String... args) throws Exception {
        try{
            customerService.addCustomer(Customer.builder()
                    .customerID(0)
                    .email("admin@admin.com")
                    .first_name("admin")
                    .last_name("admin")
                    .password("admin")
                    .build());
            LocalDate date = LocalDate.now();
            Coupon coupon1 = Coupon.builder()
                    .couponID(0)
                    .companyID(1)
                    .category(Category.Electricity)
                    .title("test")
                    .description("test coupon")
                    .start_date(Date.valueOf(date))
                    .end_date(Date.valueOf(date.plusDays(5)))
                    .amount(5)
                    .price(15)
                    .image("image")
                    .build();
            Coupon coupon2 = Coupon.builder()
                    .couponID(0)
                    .companyID(2)
                    .category(Category.Food)
                    .title("test")
                    .description("test coupon")
                    .start_date(Date.valueOf(date))
                    .end_date(Date.valueOf(date.plusDays(5)))
                    .amount(5)
                    .price(15)
                    .image("image")
                    .build();
            Coupon coupon3 = Coupon.builder()
                    .couponID(0)
                    .companyID(1)
                    .category(Category.Electricity)
                    .title("expired")
                    .description("expired test coupon")
                    .start_date(Date.valueOf(date))
                    .end_date(Date.valueOf(date.minusDays(5)))
                    .amount(5)
                    .price(15)
                    .image("image")
                    .build();
            companyService.addCompany(Company.builder()
                    .id(0)
                    .email("HardCodedCompany@gmail.com")
                    .name("HardCodedName")
                    .password("123456")
                    .coupon(coupon1)
                    .coupon(coupon3)
                    .build());

            companyService.addCompany(Company.builder()
                    .id(0)
                    .email("HardCodedCompanyToDelete@gmail.com")
                    .name("HardCodedNameToDelete")
                    .password("123456")
                    .coupon(coupon2)
                    .build());


            customerService.addCustomer(Customer.builder()
                            .customerID(0)
                            .email("HardCodedCustomer@gmail.com")
                            .first_name("Hard")
                            .last_name("Coded")
                            .password("123456")
                            .coupon(coupon1)
                            .coupon(coupon3)
                            .build());

            customerService.addCustomer(Customer.builder()
                    .customerID(0)
                    .email("HardCodedCustomerToDelete@gmail.com")
                    .first_name("Hard")
                    .last_name("Coded")
                    .password("123456")
                    .coupons(List.of(coupon1,coupon2))
                    .build());


        }
        catch (CouponSystemException e){
            System.out.println(e.getMessage());
        }
    }
}
