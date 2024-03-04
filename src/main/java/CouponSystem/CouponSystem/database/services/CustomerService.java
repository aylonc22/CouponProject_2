package CouponSystem.CouponSystem.database.services;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Customer;
import java.util.List;

public interface CustomerService {
    boolean isCustomerExists(String email,String password) throws CouponSystemException;
    void addCustomer(Customer customer) throws CouponSystemException;
    void updateCustomer(Customer customer) throws CouponSystemException;
    void deleteCustomer(int customerID);
    List<Customer> getAllCustomers();
    Customer getOneCustomer(int customerID) throws CouponSystemException;
}
