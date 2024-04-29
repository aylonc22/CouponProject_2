package CouponSystem.CouponSystem.controllers;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Company;
import CouponSystem.CouponSystem.database.beans.Customer;
import CouponSystem.CouponSystem.database.servicesImp.CompanyServiceImp;
import CouponSystem.CouponSystem.database.servicesImp.CustomerServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CompanyServiceImp companyServiceImp;
    private final CustomerServiceImp customerServiceImp;
    @PostMapping("/company")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@Validated @RequestBody Company company) throws CouponSystemException {
        companyServiceImp.addCompany(company);
    }
    @PutMapping("/company/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateCompany(@Validated @RequestBody  Company company) throws CouponSystemException {
        companyServiceImp.updateCompany(company);
    }
    @DeleteMapping("/company/delete/{companyID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@PathVariable int companyID) throws CouponSystemException {
        companyServiceImp.deleteCompany(companyID);
    }
    @GetMapping("/company")
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getAllCompanies() {
        return companyServiceImp.getAllCompanies();
    }
    @GetMapping("/company/{companyID}")
    @ResponseStatus(HttpStatus.OK)
    public Company getOneCompany(@PathVariable  int companyID) throws CouponSystemException {
        return companyServiceImp.getOneCompany(companyID);
    }
    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@Validated @RequestBody Customer customer) throws CouponSystemException {
        customerServiceImp.addCustomer(customer);
    }
    @PutMapping("/customer/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@Validated @RequestBody  Customer customer) throws CouponSystemException {
        customerServiceImp.updateCustomer(customer);
    }
    @DeleteMapping("/customer/delete/{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable int customerID) throws CouponSystemException {
        customerServiceImp.deleteCustomer(customerID);
    }
    @GetMapping("/customer")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return customerServiceImp.getAllCustomers();
    }

    @GetMapping("/customer/{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getOneCustomer(@PathVariable  int customerID) throws CouponSystemException {
        return customerServiceImp.getOneCustomer(customerID);
    }
}
