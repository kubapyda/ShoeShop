package pl.shoeshop.shoeshop.entity;

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

    @ManyToOne
    private Order order;

    @OneToOne
    private ShoeVariant shoeVariant;

    @Column
    private Integer quantity;
}
