package pl.shoeshop.shoeshop;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name="order")
public class Order {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column
    private Long id;

    @Column
    private Receiver receiver;

    @Column
    private OrderStatusType orderStatus;

    @Column
    private ZonedDateTime applicationDate;

    @Column
    private ZonedDateTime sendDate;

    @Column
    private ZonedDateTime deliveryDate;

    @Column
    private OrderedShoe orderedShoe;

    @OneToMany
    private List<OrderedShoe> orderedShoes;
}
