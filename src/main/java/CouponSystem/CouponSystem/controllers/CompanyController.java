package CouponSystem.CouponSystem.controllers;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.beans.UserType;
import CouponSystem.CouponSystem.database.beans.Category;
import CouponSystem.CouponSystem.database.beans.Coupon;
import CouponSystem.CouponSystem.database.servicesImp.CompanyServiceImp;
import CouponSystem.CouponSystem.database.servicesImp.CouponServiceImp;
import CouponSystem.CouponSystem.util.Jwt;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@CrossOrigin

public class CompanyController {
    private final CompanyServiceImp companyServiceImp;
    private final CouponServiceImp couponServiceImp;
    private final Jwt JWT;
    @PostMapping("/coupon")
    public ResponseEntity<?> addCoupon(@RequestHeader("Authorization") String jwt,@Validated @RequestBody Coupon coupon) throws CouponSystemException, SignatureException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.COMPANY.toString())){
            couponServiceImp.addCoupon(coupon);
            return new ResponseEntity<>(headers,HttpStatus.CREATED);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @PutMapping("/coupon")
    public ResponseEntity<?> updateCoupon(@RequestHeader("Authorization") String jwt,@Validated @RequestBody Coupon coupon) throws CouponSystemException, SignatureException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.COMPANY.toString())){
            couponServiceImp.updateCoupon(coupon);
            return new ResponseEntity<>(headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @DeleteMapping("/coupon/{couponID}")
    public ResponseEntity<?> deleteCoupon(@RequestHeader("Authorization") String jwt,@PathVariable int couponID) throws CouponSystemException, SignatureException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.COMPANY.toString())){
            couponServiceImp.deleteCoupon(couponID);
            return new ResponseEntity<>(headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }
    @GetMapping("/allCoupons/{companyID}")
    public ResponseEntity<?> getAllCompanyCoupons(@RequestHeader("Authorization") String jwt,@PathVariable int companyID) throws CouponSystemException, SignatureException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.COMPANY.toString())){
            return new ResponseEntity<>(companyServiceImp.getAllCompanyCoupons(companyID),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }

    @GetMapping("/couponsByCategory/{companyID}")
    public ResponseEntity<?> getAllCompanyCouponsByCategory(@RequestHeader("Authorization") String jwt,@PathVariable Category category,@PathVariable int companyID) throws SignatureException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.COMPANY.toString())){
            return new ResponseEntity<>(companyServiceImp.getAllCompanyCouponsByCategory(category, companyID),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }

    @GetMapping("/couponsByMaxPrice/{companyID}")
    public ResponseEntity<?> getAllCompanyCouponsByMaxPrice(@RequestHeader("Authorization") String jwt,@PathVariable double price,@PathVariable int companyID) throws SignatureException{
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.COMPANY.toString())){
            return new ResponseEntity<>(companyServiceImp.getAllCompanyCouponsByMaxPrice(price, companyID),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> companyDetails(@RequestHeader("Authorization") String jwt) throws CouponSystemException,SignatureException {
        String userJwt = jwt.split(" ")[1];
        HttpHeaders headers = JWT.getHeaders(jwt);
        if(JWT.getUserType(userJwt).equals(UserType.COMPANY.toString())){
            return new ResponseEntity<>(companyServiceImp.getOneCompany(JWT.extractId(userJwt)),headers,HttpStatus.OK);
        }
        return  new ResponseEntity<>(headers,HttpStatus.FORBIDDEN);

    }
}
