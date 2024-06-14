package CouponSystem.CouponSystem.controllers;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.beans.UserType;
import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.beans.Coupon;
import CouponSystem.CouponSystem.database.servicesImp.CouponServiceImp;
import CouponSystem.CouponSystem.database.servicesImp.CustomerServiceImp;
import CouponSystem.CouponSystem.util.Jwt;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin
public class CustomerController {
    private final CustomerServiceImp customerServiceImp;
    private final CouponServiceImp couponServiceImp;
    private final Jwt JWT;
    @PostMapping("/buyCoupon/{couponID}/{customerID}")
    public ResponseEntity<?> buyCoupon(@RequestHeader("Authorization") String jwt,@PathVariable int couponID, @PathVariable int customerID) throws CouponSystemException, SignatureException, ExpiredJwtException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.CUSTOMER.toString())){
            couponServiceImp.addCouponPurchase(customerID,couponID);
            return new ResponseEntity<>(headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @GetMapping("/coupons/{customerID}")
    public ResponseEntity<?> getAllCustomerCoupons(@RequestHeader("Authorization") String jwt,@PathVariable int customerID) throws CouponSystemException, SignatureException,ExpiredJwtException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.CUSTOMER.toString())){
            return new ResponseEntity<>(customerServiceImp.getAllCustomerCoupons(customerID),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @GetMapping("/coupons")
    public ResponseEntity<?> getAllCoupons(@RequestHeader("Authorization") String jwt) throws CouponSystemException, SignatureException,ExpiredJwtException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.CUSTOMER.toString())){
            return new ResponseEntity<>(couponServiceImp.getAllCoupons(),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @GetMapping("/couponsByCategory/{category}/{customerID}")
    public ResponseEntity<?> getAllCouponsByCategory(@RequestHeader("Authorization") String jwt,@PathVariable Category category,@PathVariable int customerID) throws  SignatureException ,ExpiredJwtException{
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.CUSTOMER.toString())){
            return new ResponseEntity<>(customerServiceImp.getAllCustomerCouponsByCategory(category,customerID),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @GetMapping("/couponsByMaxPrice/{price}/{customerID}")
    public ResponseEntity<?> getAllCouponsByMaxPrice(@RequestHeader("Authorization") String jwt,@PathVariable double price,@PathVariable int customerID) throws  SignatureException ,ExpiredJwtException{
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.CUSTOMER.toString())){
            return new ResponseEntity<>(customerServiceImp.getAllCustomerCouponsByMaxPrice(price,customerID),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getDetails(@RequestHeader("Authorization") String jwt) throws SignatureException, CouponSystemException ,ExpiredJwtException{
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.CUSTOMER.toString())){
            return new ResponseEntity<>(customerServiceImp.getOneCustomer(JWT.extractId(userJwt)),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
}
