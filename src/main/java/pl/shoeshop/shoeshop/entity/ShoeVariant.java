package pl.shoeshop.shoeshop.entity;

import lombok.Data;
import pl.shoeshop.shoeshop.type.ColorType;

import javax.persistence.*;

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

    @Column
    private Integer size;

    @Column
    private Integer quantity;

    @ManyToOne
    private Shoe shoe;
}
