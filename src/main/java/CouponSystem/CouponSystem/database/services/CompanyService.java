package CouponSystem.CouponSystem.database.services;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.beans.Company;
import java.util.List;

public interface CompanyService {
    boolean isCompanyExists(String email, String password) throws CouponSystemException;
    void addCompany(Company company) throws CouponSystemException;
    void updateCompany(Company company) throws CouponSystemException;
    void deleteCompany(int companyID) throws CouponSystemException;
    List<Company> getAllCompanies();
    Company getOneCompany(int companyID) throws CouponSystemException;

}
