package pl.shoeshop.shoeshop.entity;

import lombok.Data;
import pl.shoeshop.shoeshop.type.OrderStatusType;

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

    @ManyToOne
    private Receiver receiver;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatusType orderStatus;

    @Column
    private ZonedDateTime applicationDate;

    @Column
    private ZonedDateTime sendDate;

    @Column
    private ZonedDateTime deliveryDate;

    @OneToMany
    private List<OrderedShoe> orderedShoes;
}
