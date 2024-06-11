package CouponSystem.CouponSystem.util;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.Exceptions.ErrMsg;
import CouponSystem.CouponSystem.beans.Credentials;
import CouponSystem.CouponSystem.beans.UserDetails;
import CouponSystem.CouponSystem.beans.UserType;
import io.jsonwebtoken.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Jwt {
    private final String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    private final String encodedSecretKey = "why+the+secret+key+needs+to+be+so+long+seriously";
    private final Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), signatureAlgorithm);
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userType", userDetails.getUserType());
        claims.put("userName", userDetails.getUserName());
        claims.put("userId", userDetails.getId());
        return createToken(claims, userDetails.getEmail());
    }
    public String generateToken(String token) {
        Map<String, Object> claims = new HashMap<>();
        Claims ourClaims = extractAllClaims(token);
        claims.put("userName", ourClaims.get("userName"));
        claims.put("userType", ourClaims.get("userType"));
        claims.put("userId",ourClaims.get("userId"));
        return createToken(claims, ourClaims.getSubject());
    }

    private String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .signWith(decodedSecretKey)
                .compact();
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException, SignatureException {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(encodedSecretKey)
                .build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String extractEmail(String token) throws ExpiredJwtException,SignatureException {
        return extractAllClaims(token).getSubject();
    }
    public int extractId(String token) throws ExpiredJwtException,SignatureException {
        Claims claims = extractAllClaims(token);
        return (Integer) claims.get("userId");
    }
    public java.util.Date extractExpirationDate(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        try {
            extractAllClaims(token);
            return false;
        } catch (ExpiredJwtException err) {
            return true;
        }
    }

    public String getUserType(String token) throws ExpiredJwtException,SignatureException{
        Claims claims = extractAllClaims(token);
        return (String) claims.get("userType");
    }

    public boolean validateToken(String token, Credentials userCredential) throws MalformedJwtException, SignatureException {
        final String userEmail = extractEmail(token);
        return (userEmail.equals(userCredential.getEmail()) && !isTokenExpired(token));
    }

    public boolean validateToken(String token) throws MalformedJwtException, SignatureException{
        final Claims claims = extractAllClaims(token);
        return true;
    }

    public boolean checkUser(String token, UserType userType) throws CouponSystemException {
        String newToken = token.replace("Bearer ", "");
        if (validateToken(newToken)) {
            if (!getUserType(newToken).equals(userType.toString())) {
                throw new CouponSystemException(ErrMsg.USER_NOT_ALLOWED);
            }
        }
        return true;
    }
    public HttpHeaders getHeaders(String jwt){
        HttpHeaders headers = new HttpHeaders();
        String userJWT = jwt.split(" ")[1];
        if (validateToken(userJWT)){
            headers.set("Authorization", "Bearer "+generateToken(userJWT));
        }
        return headers;
    }
}
