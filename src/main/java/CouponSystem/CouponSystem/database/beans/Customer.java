package CouponSystem.CouponSystem.database.beans;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;


@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;
    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany()
    @JoinTable(
            name = "customers_coupons",
            joinColumns = @JoinColumn(name = "customerID"),
            inverseJoinColumns = @JoinColumn(name = "couponID")
    )
    @Singular
    private List<Coupon> coupons;
}
