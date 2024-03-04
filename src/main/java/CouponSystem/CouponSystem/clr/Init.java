package CouponSystem.CouponSystem.clr;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Company;
import CouponSystem.CouponSystem.database.beans.Customer;
import CouponSystem.CouponSystem.database.services.CompanyService;
import CouponSystem.CouponSystem.database.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class Init implements CommandLineRunner {

    final CompanyService companyService;
    final CustomerService customerService;
    @Override
    public void run(String... args) throws Exception {
        try{
            companyService.addCompany(Company.builder()
                    .id(0)
                    .email("HardCodedCompany@gmail.com")
                    .name("HardCodedName")
                    .password("123456")
                    .build());

            customerService.addCustomer(Customer.builder()
                            .id(0)
                            .email("HardCodedCustomer@gmail.com")
                            .first_name("Hard")
                            .last_name("Coded")
                            .password("123456")
                            .build());
        }
        catch (CouponSystemException e){
            System.out.println(e.getMessage());
        }
    }
}
