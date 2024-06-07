package CouponSystem.CouponSystem.beans;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Credentials {
    private String email;
    private String password;
    private UserType userType;

}
