package pl.shoeshop.shoeshop;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name="ordered_shoe")
public class OrderedShoe {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column
    private Long id;

    @Column
    private Order order;

    @Column
    private ShoeVariant shoeVariant;

    @Column
    private Integer quantity;
}
