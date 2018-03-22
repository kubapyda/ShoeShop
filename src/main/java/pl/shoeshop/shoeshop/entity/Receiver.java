package pl.shoeshop.shoeshop.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name="receiver")
public class Receiver {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Embedded
    private Address address;

    @OneToMany
    private List<Order> orders;
}
