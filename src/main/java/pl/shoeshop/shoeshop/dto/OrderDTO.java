package pl.shoeshop.shoeshop.dto;

import lombok.Data;
import pl.shoeshop.shoeshop.entity.Address;

import java.util.List;

@Data
public class OrderDTO {

    private String name;
    private String surname;
    private String email;
    private Address address;
    private List<VariantDTO> variants;
}
