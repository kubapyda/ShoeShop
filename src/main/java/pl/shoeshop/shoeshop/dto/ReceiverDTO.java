package pl.shoeshop.shoeshop.dto;

import lombok.Data;
import pl.shoeshop.shoeshop.entity.Address;

import javax.validation.constraints.Email;

@Data
public class ReceiverDTO {

    private String name;
    private String surname;

    @Email
    private String email;
    private Address address;
}