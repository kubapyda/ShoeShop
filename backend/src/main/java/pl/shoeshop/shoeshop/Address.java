package pl.shoeshop.shoeshop;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

    @Column
    private String postalCode;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private Integer flatNumber;

    @Column
    private Integer localeNumber;
}
