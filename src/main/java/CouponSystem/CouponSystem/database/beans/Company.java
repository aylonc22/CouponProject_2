package CouponSystem.CouponSystem.database.beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "companyID",cascade = CascadeType.ALL,orphanRemoval = true)
    @Singular
    private List<Coupon> coupons;
}
