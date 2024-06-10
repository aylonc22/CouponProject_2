package CouponSystem.CouponSystem.clr;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Company;
import CouponSystem.CouponSystem.database.beans.Customer;
import CouponSystem.CouponSystem.database.services.CompanyService;
import CouponSystem.CouponSystem.database.services.CouponService;
import CouponSystem.CouponSystem.database.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
//@Order(2)
@RequiredArgsConstructor
public class AdminTest implements CommandLineRunner {
    final CompanyService companyService;
    final CustomerService customerService;
    final CouponService couponService;
    @Override
    public void run(String... args) throws Exception {
        try{
            System.out.println("\nTesting Admin");
            System.out.println("\nTesting adding company");
            companyService.addCompany(Company.builder()
                            .id(0)
                            .name("AddedCompany")
                            .email("AddedCompany@gmail.com")
                            .password("123456")
                    .build());
            System.out.println("\nTesting updating company");
            companyService.updateCompany(Company.builder()
                    .id(3)
                    .name("UpdatedCompany")
                    .email("UpdatedCompany@gmail.com")
                    .password("123456")
                    .build());

            System.out.println("\nTesting deleting company");
            companyService.deleteCompany(2);

            System.out.println("\nTesting getting all companies");
            companyService.getAllCompanies().forEach(System.out::println);

            System.out.println("\nTesting getting specific company");
            System.out.println(companyService.getOneCompany(1));

            System.out.println("\nTesting adding customer");
            customerService.addCustomer(Customer.builder()
                    .customerID(0)
                    .first_name("Added")
                    .last_name("Customer")
                    .email("AddedCustomer@gmail.com")
                    .password("123456")
                    .build());

            System.out.println("\nTesting updating customer");
            customerService.updateCustomer(Customer.builder()
                    .customerID(3)
                    .first_name("Updated")
                    .last_name("Customer")
                    .email("UpdateCustomer@gmail.com")
                    .password("123456")
                    .build());

            System.out.println("\nTesting deleting customer");
            customerService.deleteCustomer(2);

            System.out.println("\nTesting getting all customers");
            customerService.getAllCustomers().forEach(System.out::println);

            System.out.println("\nTesting getting specific customer");
            System.out.println(customerService.getOneCustomer(1));
        }
        catch (CouponSystemException e){
            System.out.println(e.getMessage());
        }
    }
}
