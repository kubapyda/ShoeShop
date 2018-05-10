package pl.shoeshop.shoeshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.shoeshop.shoeshop.type.ColorType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name="shoe_variant")
public class ShoeVariant {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private ColorType shankColor;

    @Column
    @Enumerated(EnumType.STRING)
    private ColorType soleColor;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private Shoe shoe;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shoeVariant")
    @Fetch(FetchMode.SUBSELECT)
    @NotEmpty
    private List<SizedShoe> sizedShoes;
}
