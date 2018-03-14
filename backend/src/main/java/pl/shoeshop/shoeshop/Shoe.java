package pl.shoeshop.shoeshop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="shoe")
public class Shoe {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column
    private Long id;

    @Column
    private BrandType brandType;

    @Column
    private String model;

    @Column
    private BigDecimal price;

    @Column
    private GenderType gender;

    @Column
    private ShoeType shoeType;

    @Column
    private String description;

    @OneToMany
    private List<ShoeVariant> variants;

}
