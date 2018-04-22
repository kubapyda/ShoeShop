package pl.shoeshop.shoeshop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.shoeshop.shoeshop.type.BrandType;
import pl.shoeshop.shoeshop.type.GenderType;
import pl.shoeshop.shoeshop.type.ShoeType;

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
    @Enumerated(EnumType.STRING)
    private BrandType brand;

    @Column
    private String model;

    @Column(precision = 6, scale = 2)
    private BigDecimal price;

    @Column
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column
    @Enumerated(EnumType.STRING)
    private ShoeType shoeType;

    @Column
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shoe")
    @Fetch(FetchMode.SUBSELECT)
    private List<ShoeVariant> variants;
}
