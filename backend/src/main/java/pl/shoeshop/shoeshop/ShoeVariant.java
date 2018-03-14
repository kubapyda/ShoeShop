package pl.shoeshop.shoeshop;

import lombok.Data;

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
    private ColorType shankColor;

    @Column
    private ColorType soleColor;

    @Column
    private Integer size;

    @Column
    private Integer quantity;

    @ManyToOne
    private Shoe shoe;
}
