package CouponSystem.CouponSystem.database.beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int couponID;
    @Column(nullable = false)
    private int companyID;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Date start_date;
    @Column(nullable = false)
    private  Date end_date;
    @Column(nullable = false)
    @Min(0)
    private int amount;
    @Column(nullable = false)
    @Min(0)
    private double price;
    @Column(nullable = false)
    private String image;

}
