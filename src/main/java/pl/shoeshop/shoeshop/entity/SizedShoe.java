package pl.shoeshop.shoeshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.shoeshop.shoeshop.type.BrandType;
import pl.shoeshop.shoeshop.type.GenderType;
import pl.shoeshop.shoeshop.type.ShoeType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sized_shoe")
public class SizedShoe {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column
    private Long id;

    @Column
    @Min(0)
    private Integer size;

    @Column
    @Min(1)
    private Integer quantity;

    @ManyToOne
    @JsonIgnore
    private ShoeVariant shoeVariant;

    @OneToMany(mappedBy = "sizedShoe")
    @JsonIgnore
    private List<OrderedShoe> orderedShoes;
}
