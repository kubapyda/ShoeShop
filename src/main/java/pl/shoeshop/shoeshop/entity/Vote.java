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
@Table(name="vote")
public class Vote {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rate rate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Receiver receiver;

    @Column
    private Boolean isUseful = Boolean.TRUE;
}
