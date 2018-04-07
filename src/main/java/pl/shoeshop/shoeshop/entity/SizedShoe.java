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
    private Integer size;

    @Column
    private Integer quantity;

    @ManyToOne
    @JsonIgnore
    private ShoeVariant shoeVariant;
}