package pl.shoeshop.shoeshop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import pl.shoeshop.shoeshop.type.OrderStatusType;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_order")
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderedShoe> orderedShoes;
}
