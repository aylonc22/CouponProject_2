package CouponSystem.CouponSystem.database.servicesImp;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.Exceptions.ErrMsg;
import CouponSystem.CouponSystem.beans.Credentials;
import CouponSystem.CouponSystem.beans.UserDetails;
import CouponSystem.CouponSystem.beans.UserType;
import CouponSystem.CouponSystem.database.beans.Company;
import CouponSystem.CouponSystem.database.beans.Customer;
import CouponSystem.CouponSystem.database.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final CompanyServiceImp companyServiceImp;
    private final CustomerServiceImp customerServiceImp;
    @Override
    public void register(UserDetails user) throws CouponSystemException {
        switch (user.getUserType()){
            case COMPANY:
                companyServiceImp.addCompany(Company.builder()
                                .id(0)
                                .name(user.getUserName())
                                .email(user.getEmail())
                                .password(user.getPassword())
                        .build());
                break;
            case CUSTOMER:
                customerServiceImp.addCustomer(Customer.builder()
                                .customerID(0)
                                .first_name(user.getUserName().split("_")[0])
                                .last_name(user.getUserName().split("_")[1])
                                .email(user.getEmail())
                                .password(user.getPassword())
                        .build());
                break;
        }
    }

    @Override
    public UserDetails login(Credentials credentials) throws CouponSystemException {

       switch (credentials.getUserType()){
           case CUSTOMER:
               if(credentials.getEmail().equals("admin@admin.com") && credentials.getPassword().equals("admin")){
                   throw new CouponSystemException(ErrMsg.USER_NOT_ADMIN);
               }
               int customerId =  customerServiceImp.isCustomerExists(credentials.getEmail(), credentials.getPassword());
                   Customer customer = customerServiceImp.getOneCustomer(customerId);
                   return UserDetails.builder()
                           .userName(customer.getFirst_name() + '_' + customer.getLast_name())
                           .email(customer.getEmail())
                           .userType(UserType.CUSTOMER)
                           .id(customerId)
                           .build();
           case ADMIN:
               if(!credentials.getEmail().equals("admin@admin.com") && !credentials.getPassword().equals("admin")){
                   throw new CouponSystemException(ErrMsg.USER_NOT_ADMIN);
               }
               return UserDetails.builder()
                       .userName("admin_admin")
                       .email("admin@admin.com")
                       .userType(UserType.ADMIN)
                       .id(1)
                       .build();
           case COMPANY:
               int companyId =  companyServiceImp.isCompanyExists(credentials.getEmail(), credentials.getPassword());
               Company company = companyServiceImp.getOneCompany(companyId);
               return UserDetails.builder()
                       .userName(company.getName())
                       .email(company.getEmail())
                       .userType(UserType.COMPANY)
                       .id(companyId)
                       .build();
       }
return null;
    }
}
