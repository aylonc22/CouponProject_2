package CouponSystem.CouponSystem.Exceptions;

public class CouponSystemException extends Exception {
    public CouponSystemException(ErrMsg msg) {
        super(msg.getMsg());
    }
}
