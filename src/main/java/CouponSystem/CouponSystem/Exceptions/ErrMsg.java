package CouponSystem.CouponSystem.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrMsg {
    USER_NOT_ADMIN("User is not admin!"),
    ADMIN_NOT_LOGGED_IN("Admin is not logged in!"),
    CLIENT_NOT_LOGGED_IN("Client is not logged in!"),
    CLIENT_NOT_ADMIN("Client is not registered as administrator!"),
    COMPANY_NOT_LOGGED_IN("Company is not logged in!"),
    COMPANY_NOT_FOUND("Company not found!"),
    CUSTOMER_NOT_FOUND("Customer not found!"),
    CUSTOMER_ALREADY_HAS_COUPON("Customer already have this coupon!"),
    COUPON_NOT_FOUND("Coupon not found!"),
    OUT_OF_STOCK("Coupon is out of stock!"),
    SQL_DUPLICATE("Unique key exception!"),
    COMPANY_ALREADY_EXISTS("A company with the same name or email already exists!"),
    CUSTOMER_ALREADY_EXISTS("A customer with the same email already exists"),
    COUPON_ALREADY_EXISTS("A coupon from this company has a same title"),
    USER_NOT_ALLOWED("User not allowed");
    private String msg;
}
