package CouponSystem.CouponSystem.database.job;

import CouponSystem.CouponSystem.Exceptions.CouponSystemException;
import CouponSystem.CouponSystem.database.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class DailyJob {
    private final CouponService couponService;
    @Scheduled(cron = "0 0 0 * * *")
    public void job(){

        try {
            couponService.deleteExpiredCoupons();
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }


    }
}
