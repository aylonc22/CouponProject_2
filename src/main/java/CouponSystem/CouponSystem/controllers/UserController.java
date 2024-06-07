package CouponSystem.CouponSystem.controllers;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.beans.Credentials;
import CouponSystem.CouponSystem.beans.UserDetails;
import CouponSystem.CouponSystem.database.servicesImp.UserServiceImp;
import CouponSystem.CouponSystem.util.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImp userServiceImp;
    private final Jwt jwt;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserDetails user) throws CouponSystemException {
        userServiceImp.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials user) throws CouponSystemException{
        HttpHeaders headers = new HttpHeaders();
        UserDetails userDetails = userServiceImp.login(user);
        headers.set("Authorization","Bearer "+jwt.generateToken(userDetails));
        return new ResponseEntity<>(userDetails.getId(),headers,HttpStatus.OK);
    }
}
