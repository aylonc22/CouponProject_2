package CouponSystem.CouponSystem.database.servicesImp;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.Exceptions.ErrMsg;
import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.beans.Company;
import CouponSystem.CouponSystem.database.beans.Coupon;
import CouponSystem.CouponSystem.database.beans.Customer;
import CouponSystem.CouponSystem.database.repos.CompanyRepo;
import CouponSystem.CouponSystem.database.repos.CouponRepo;
import CouponSystem.CouponSystem.database.repos.CustomerRepo;
import CouponSystem.CouponSystem.database.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImp implements CompanyService {
    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;
    private final CustomerRepo customerRepo;

    /**
     * @param email    of company
     * @param password of company
     * @return company id if not throwing exception before
     * @throws CouponSystemException custom exception
     */
    @Override
    public int isCompanyExists(String email, String password) throws CouponSystemException {
        Company company = companyRepo.findByEmailAndPassword(email,password);
            if(company==null){
                throw new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND);
            }
            return  company.getId();
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if(companyRepo.existsByNameOrEmail(company.getName(), company.getEmail())) {
            throw new CouponSystemException(ErrMsg.COMPANY_ALREADY_EXISTS);
        }

        companyRepo.save(company);
        System.out.println("Company added\n" + company);
    }

    @Override
    public void updateCompany(Company company) throws CouponSystemException {
        if(companyRepo.existsById(company.getId())) {
            if(company.getPassword() == null) {
                companyRepo.findById(company.getId()).ifPresent(tempComp -> {
                    tempComp.setEmail(company.getEmail());
                    tempComp.setName(company.getName());
                    companyRepo.saveAndFlush(tempComp);
                    System.out.println("Company updated\n" + tempComp);
                });
            }
            else {
                companyRepo.saveAndFlush(company);
                System.out.println("Company updated\n" + company);
            }

        }
        else {
            throw new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND);
        }
    }

    @Override
    public void deleteCompany(int companyID) throws CouponSystemException {
      Company company = companyRepo.findById(companyID)
                .orElseThrow(()->new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND));
        List<Integer> customerIds = customerRepo.findCustomerIdsByCompanyCoupons(companyID);

        for (Integer customerId : customerIds) {
            Customer customer = customerRepo.findById(customerId)
                    .orElseThrow(() -> new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND));

            customer.getCoupons().removeIf(coupon -> coupon.getCompanyID() == companyID);
            customerRepo.saveAndFlush(customer);
        }
        couponRepo.deleteAll(company.getCoupons());
        companyRepo.deleteById(companyID);
        System.out.println("Company ID: " + companyID + " deleted");
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getOneCompany(int companyID) throws CouponSystemException {
        return companyRepo.findById(companyID)
                .orElseThrow(()->new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND));
    }

    @Override
    public List<Coupon> getAllCompanyCoupons(int companyID) throws CouponSystemException {
        return getOneCompany(companyID).getCoupons();
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByCategory(Category category, int companyID) {
        return couponRepo.findAllByCompanyIDAndCategory(companyID,category);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByMaxPrice(double price, int companyID) {
        return couponRepo.findAllByCompanyIDAndPriceLessThanEqual(companyID,price);
    }
}
