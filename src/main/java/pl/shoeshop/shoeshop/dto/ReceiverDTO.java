package pl.shoeshop.shoeshop.dto;

import lombok.Data;
import pl.shoeshop.shoeshop.entity.Address;

@Data
public class ReceiverDTO {

    private String name;
    private String surname;
    private String email;
    private Address address;
}