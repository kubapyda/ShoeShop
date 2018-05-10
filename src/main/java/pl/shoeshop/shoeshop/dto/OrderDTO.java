package pl.shoeshop.shoeshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    private ReceiverDTO receiver;
    private List<VariantDTO> variants;
}