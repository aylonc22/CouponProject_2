package CouponSystem.CouponSystem.controllers;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.beans.UserType;
import CouponSystem.CouponSystem.database.beans.Company;
import CouponSystem.CouponSystem.database.beans.Customer;
import CouponSystem.CouponSystem.database.servicesImp.CompanyServiceImp;
import CouponSystem.CouponSystem.database.servicesImp.CustomerServiceImp;
import CouponSystem.CouponSystem.util.Jwt;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final CompanyServiceImp companyServiceImp;
    private final CustomerServiceImp customerServiceImp;
    private final Jwt JWT;
    @PostMapping("/company")
    public ResponseEntity<?> addCompany(@RequestHeader("Authorization") String jwt, @Validated @RequestBody Company company) throws CouponSystemException,SignatureException, ExpiredJwtException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.ADMIN.toString())){
            companyServiceImp.addCompany(company);
            return new ResponseEntity<>(headers,HttpStatus.CREATED);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @PutMapping("/company/update")
    public ResponseEntity<?> updateCompany(@RequestHeader("Authorization") String jwt,@Validated @RequestBody  Company company) throws CouponSystemException,SignatureException ,ExpiredJwtException{
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.ADMIN.toString())){
            companyServiceImp.updateCompany(company);
            return new ResponseEntity<>(headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @DeleteMapping("/company/delete/{companyID}")
    public ResponseEntity<?> deleteCompany(@RequestHeader("Authorization") String jwt,@PathVariable int companyID) throws CouponSystemException ,SignatureException,ExpiredJwtException{
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.ADMIN.toString())){
            companyServiceImp.deleteCompany(companyID);
            return new ResponseEntity<>(headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @GetMapping("/company")
    public ResponseEntity<?> getAllCompanies(@RequestHeader("Authorization") String jwt) throws SignatureException,ExpiredJwtException{
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.ADMIN.toString())){
            return new ResponseEntity<>(companyServiceImp.getAllCompanies(),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @GetMapping("/company/{companyID}")
    public ResponseEntity<?> getOneCompany(@RequestHeader("Authorization") String jwt,@PathVariable  int companyID) throws CouponSystemException,SignatureException,ExpiredJwtException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.ADMIN.toString())){
            return new ResponseEntity<>(companyServiceImp.getOneCompany(companyID),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@RequestHeader("Authorization") String jwt,@Validated @RequestBody Customer customer) throws CouponSystemException,SignatureException,ExpiredJwtException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.ADMIN.toString())){
            customerServiceImp.addCustomer(customer);
            return new ResponseEntity<>(headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @PutMapping("/customer/update")
    public ResponseEntity<?> updateCustomer(@RequestHeader("Authorization") String jwt,@Validated @RequestBody  Customer customer) throws CouponSystemException,SignatureException,ExpiredJwtException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.ADMIN.toString())){
            customerServiceImp.updateCustomer(customer);
            return new ResponseEntity<>(headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @DeleteMapping("/customer/delete/{customerID}")
    public ResponseEntity<?> deleteCustomer(@RequestHeader("Authorization") String jwt,@PathVariable int customerID) throws CouponSystemException,SignatureException,ExpiredJwtException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.ADMIN.toString())){
            customerServiceImp.deleteCustomer(customerID);
            return new ResponseEntity<>(headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @GetMapping("/customer")
    public ResponseEntity<?> getAllCustomers(@RequestHeader("Authorization") String jwt) throws SignatureException,ExpiredJwtException{
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.ADMIN.toString())){
            return new ResponseEntity<>(customerServiceImp.getAllCustomers(),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }

    @GetMapping("/customer/{customerID}")
    public ResponseEntity<?> getOneCustomer(@RequestHeader("Authorization") String jwt,@PathVariable  int customerID) throws CouponSystemException,SignatureException,ExpiredJwtException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.ADMIN.toString())){
            return new ResponseEntity<>(customerServiceImp.getOneCustomer(customerID),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
}
