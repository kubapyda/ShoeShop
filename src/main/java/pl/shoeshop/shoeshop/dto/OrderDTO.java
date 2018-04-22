package pl.shoeshop.shoeshop.dto;

import lombok.Data;
import pl.shoeshop.shoeshop.entity.Address;

import java.util.List;

@Data
public class OrderDTO {

    private ReceiverDTO receiver;
    private List<VariantDTO> variants;
}