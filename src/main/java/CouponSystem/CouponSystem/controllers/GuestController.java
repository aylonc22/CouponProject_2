package CouponSystem.CouponSystem.controllers;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.beans.UserType;
import CouponSystem.CouponSystem.database.servicesImp.CouponServiceImp;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/guest")
@RequiredArgsConstructor
@CrossOrigin
public class GuestController {
    private final CouponServiceImp couponServiceImp;
    @GetMapping("/coupons")
    public ResponseEntity<?> getAllCoupons()  {
            return new ResponseEntity<>(couponServiceImp.getAllCoupons(), HttpStatus.OK);
        }
    }
