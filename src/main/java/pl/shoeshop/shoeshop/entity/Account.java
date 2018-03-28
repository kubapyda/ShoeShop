package pl.shoeshop.shoeshop.entity;


import lombok.Data;
import pl.shoeshop.shoeshop.type.OrderStatusType;
import pl.shoeshop.shoeshop.type.RoleType;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private RoleType role;

}

