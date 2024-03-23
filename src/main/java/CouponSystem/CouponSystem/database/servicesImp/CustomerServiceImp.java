package CouponSystem.CouponSystem.database.servicesImp;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.Exceptions.ErrMsg;
import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.beans.Coupon;
import CouponSystem.CouponSystem.database.beans.Customer;
import CouponSystem.CouponSystem.database.repos.CouponRepo;
import CouponSystem.CouponSystem.database.repos.CustomerRepo;
import CouponSystem.CouponSystem.database.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {

   private final CustomerRepo customerRepo;
   private final CouponRepo couponRepo;
    @Override
    public boolean isCustomerExists(String email, String password) throws CouponSystemException {
        if(customerRepo.existsByEmailAndPassword(email, password))
            return  true;
        throw new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND);

    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        if(customerRepo.existsByEmail(customer.getEmail())) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_ALREADY_EXISTS);
        }
        customerRepo.save(customer);
        System.out.println("Customer added\n" + customer);
    }

    @Override
    public void updateCustomer(Customer customer) throws CouponSystemException {
        if(customerRepo.existsById(customer.getCustomerID())) {
            customerRepo.saveAndFlush(customer);
            System.out.println("Customer updated\n" + customer);
        }
        else {
            throw new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND);
        }
    }

    @Override
    public void deleteCustomer(int customerID) throws CouponSystemException {
         customerRepo.findById(customerID)
                 .orElseThrow(()->new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND));

        customerRepo.deleteById(customerID);
        System.out.println("Customer ID: " + customerID + " is deleted");
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerID) throws CouponSystemException {
        return customerRepo.findById(customerID)
                .orElseThrow(()->new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND));
    }

    @Override
    public List<Coupon> getAllCustomerCoupons(int customerID) throws CouponSystemException {
        return getOneCustomer(customerID).getCoupons();
    }

    @Override
    public List<Coupon> getAllCustomerCouponsByCategory(Category category, int customerID) {
        return customerRepo.findCouponsByCustomerIDAndCouponsCategory(customerID,category);
    }

    @Override
    public List<Coupon> getAllCustomerCouponsByMaxPrice(double price, int customerID) {
        return customerRepo.findCouponsByCustomerIDAndCouponsPriceLessThanEqual(customerID,price);
    }
}
