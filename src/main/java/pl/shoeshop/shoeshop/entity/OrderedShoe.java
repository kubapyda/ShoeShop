package pl.shoeshop.shoeshop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private SizedShoe sizedShoe;

    @Column
    private Integer quantity;
}