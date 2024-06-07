package CouponSystem.CouponSystem.database.services;
import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.beans.Credentials;
import CouponSystem.CouponSystem.beans.UserDetails;

public interface UserService {
 void register(UserDetails user) throws CouponSystemException;
 UserDetails login(Credentials credentials) throws CouponSystemException;
}
