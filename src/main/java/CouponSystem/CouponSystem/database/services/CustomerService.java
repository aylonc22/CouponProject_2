package CouponSystem.CouponSystem.database.services;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.beans.Coupon;
import CouponSystem.CouponSystem.database.beans.Customer;
import java.util.List;

public interface CustomerService {
    int isCustomerExists(String email, String password) throws CouponSystemException;
    void addCustomer(Customer customer) throws CouponSystemException;
    void updateCustomer(Customer customer) throws CouponSystemException;
    void deleteCustomer(int customerID) throws CouponSystemException;
    List<Customer> getAllCustomers();
    Customer getOneCustomer(int customerID) throws CouponSystemException;
    List<Coupon> getAllCustomerCoupons(int customerID) throws CouponSystemException;
    List<Coupon> getAllCustomerCouponsByCategory(Category category, int customerID);
    List<Coupon> getAllCustomerCouponsByMaxPrice(double price,int customerID);
}
