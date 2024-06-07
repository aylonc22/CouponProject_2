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
public class UserDetails {
    //Company = name
    //Customer = first_name + _ + last_name
    private String userName;
    private String email;
    private String password;
    private int id;
    private UserType userType;
}
