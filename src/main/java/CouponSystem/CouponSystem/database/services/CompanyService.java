package CouponSystem.CouponSystem.database.services;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.beans.Company;
import CouponSystem.CouponSystem.database.beans.Coupon;

import java.util.List;

public interface CompanyService {
    int isCompanyExists(String email, String password) throws CouponSystemException;
    void addCompany(Company company) throws CouponSystemException;
    void updateCompany(Company company) throws CouponSystemException;
    void deleteCompany(int companyID) throws CouponSystemException;
    List<Company> getAllCompanies();
    Company getOneCompany(int companyID) throws CouponSystemException;
    List<Coupon> getAllCompanyCoupons(int companyID) throws CouponSystemException;
    List<Coupon> getAllCompanyCouponsByCategory(Category category,int companyID);
    List<Coupon> getAllCompanyCouponsByMaxPrice(double price,int companyID);

}
