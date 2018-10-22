package pl.shoeshop.shoeshop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Long id;

    @Column(name = "rate_comment")
    private String comment;

    @Column
    private Integer rate;

    @ManyToOne
    private Shoe shoe;

    @ManyToOne
    private Order order;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Vote> votes;
}
