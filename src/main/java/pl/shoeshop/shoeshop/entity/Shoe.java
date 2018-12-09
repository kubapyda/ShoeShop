package pl.shoeshop.shoeshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @Enumerated(EnumType.STRING)
    private BrandType brand;

    @Column
    @NotBlank
    private String model;

    @Column(precision = 6, scale = 2)
    @Min(0)
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
    @NotEmpty
    private List<ShoeVariant> variants;

    @JsonIgnore
    @OneToMany(mappedBy = "shoe")
    private List<Rate> rates;
}
